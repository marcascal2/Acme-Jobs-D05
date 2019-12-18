
package acme.features.employer.job;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.jobs.JobStatus;
import acme.entities.roles.Employer;
import acme.entities.spam_words.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	//Internal state
	@Autowired
	EmployerJobRepository repository;


	//AbstractUpdateService<Employer, Job>
	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		int jobId = request.getModel().getInteger("id");
		int employerId = request.getPrincipal().getActiveRoleId();

		Job job = this.repository.findOneById(jobId);
		Employer employer = this.repository.findEmployerById(employerId);

		boolean isAuthorised = job.getEmployer().equals(employer);

		return isAuthorised;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "descriptor");

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "status", "title", "deadline", "salary", "description", "moreInfo");
		Descriptor descriptor = entity.getDescriptor();
		if (descriptor != null) {
			String descriptorTitle = descriptor.getTitle();
			model.setAttribute("descriptor", descriptorTitle);

			model.setAttribute("descriptorId", descriptor.getId());

			Collection<Duty> duties = descriptor.getDuty();
			model.setAttribute("duties", duties);

		}
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;
		Job result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		JobStatus status = entity.getStatus();

		if (status != null && status.equals(JobStatus.PUBLISHED)) {
			Descriptor descriptor = entity.getDescriptor();
			boolean hasDescriptor = descriptor != null;
			errors.state(request, hasDescriptor, "descriptor", "employer.job.form.error.descriptor");
		}

		String newReference = entity.getReference();
		if (newReference != null && newReference != "") {
			Job repeatedJob = this.repository.findOneByReference(newReference);
			boolean referenceIsRepeated = repeatedJob != null;
			errors.state(request, referenceIsRepeated, "reference", "employer.job.form.error.reference");
		}

		String title = entity.getTitle();
		String description = entity.getDescription();
		String moreInfo = entity.getMoreInfo();

		Collection<SpamWord> spamWords = this.repository.findManyAllSpamWord();

		errors.state(request, !(status.equals(JobStatus.PUBLISHED) && this.is_spam(title, spamWords)), "title", "employer.job.form.error.spam");
		errors.state(request, !(status.equals(JobStatus.PUBLISHED) && this.is_spam(description, spamWords)), "description", "employer.job.form.error.spam");
		errors.state(request, !(status.equals(JobStatus.PUBLISHED) && this.is_spam(moreInfo, spamWords)), "moreInfo", "employer.job.form.error.spam");
		if (entity.getDescriptor() != null) {
			String descriptor = entity.getDescriptor().getTitle();
			errors.state(request, !this.is_spam(descriptor, spamWords), "descriptor", "employer.job.form.error.spam");
		}

		if (entity.getDescriptor() != null) {
			Collection<Duty> duties = this.repository.findManyByDescriptorId(entity.getDescriptor().getId());
			Double sum = 0.0;

			for (Duty duty : duties) {
				if (duty.getPercentageTimeForWeek() != null) {
					sum = sum + duty.getPercentageTimeForWeek();
				}
			}

			errors.state(request, !(entity.getStatus().equals(JobStatus.PUBLISHED) && sum != 100.0), "percentageTimeForWeek", "employer.duty.form.error.percentage");
		}
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		Descriptor oldDescriptor = entity.getDescriptor();
		String newTitle = request.getModel().getString("descriptor");

		if (oldDescriptor == null) {
			if (newTitle == null || newTitle == "") {
				entity.setDescriptor(null);
			} else {
				Descriptor newDescriptor = new Descriptor();
				newDescriptor.setTitle(newTitle);
				newDescriptor.setJob(entity);
				this.repository.save(newDescriptor);
				entity.setDescriptor(newDescriptor);
			}

		} else {
			if (newTitle == "" || newTitle == null) {
				entity.setDescriptor(null);
				this.repository.deleteAll(oldDescriptor.getDuty());
				this.repository.delete(oldDescriptor);
			} else {
				Descriptor newDescriptor = new Descriptor();
				newDescriptor.setTitle(newTitle);

				if (!entity.getDescriptor().getTitle().equals(newTitle)) {
					newDescriptor.setJob(entity);
					this.repository.save(newDescriptor);
					this.repository.deleteAll(oldDescriptor.getDuty());
					this.repository.delete(oldDescriptor);
					entity.setDescriptor(newDescriptor);
				}
			}
		}
		this.repository.save(entity);
	}

	private boolean is_spam(final String text, final Collection<SpamWord> spamWords) {
		List<String> list = Arrays.asList(text.split(" "));

		for (SpamWord spamWord : spamWords) {
			double spanishFrequency = (double) Collections.frequency(list, spamWord.getSpanishTranslation()) / list.size() * 100;
			if (spanishFrequency > spamWord.getSpamThreshold()) {
				return true;
			}
			double englishFrequency = (double) Collections.frequency(list, spamWord.getEnglishTranslation()) / list.size() * 100;
			if (englishFrequency > spamWord.getSpamThreshold()) {
				return true;
			}
		}

		return false;
	}
}
