
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
import acme.entities.roles.Employer;
import acme.entities.spam_words.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
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
		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;
		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = job.isFinalMode() || !job.isFinalMode() && employer.getUserAccount().getId() == principal.getAccountId();
		return result;
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

		request.unbind(entity, model, "reference", "status", "title", "deadline", "salary", "description", "moreInfo", "finalMode");
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

		Job job;
		job = this.repository.findOneById(entity.getId());

		errors.state(request, !job.isFinalMode() || !entity.isFinalMode(), "finalMode", "employer.job.form.error.finalMode");

		Collection<Duty> duties = this.repository.findManyByDescriptorId(entity.getDescriptor().getId());
		Double sum = 0.0;

		for (Duty duty : duties) {
			if (duty.getPercentageTimeForWeek() != null) {
				sum = sum + duty.getPercentageTimeForWeek();
				System.out.println("sum bucle " + sum);
			}
		}

		errors.state(request, !(entity.isFinalMode() && sum != 100.0), "percentageTimeForWeek", "employer.duty.form.error.percentage");

		String descriptor = entity.getDescriptor().getTitle();
		String title = entity.getTitle();
		String reference = entity.getReference();
		String description = entity.getDescription();
		String moreInfo = entity.getMoreInfo();
		Collection<SpamWord> spamWords;

		spamWords = this.repository.findManyAllSpamWord();

		errors.state(request, !this.is_spam(reference, spamWords), "reference", "employer.job.form.error.spam");
		errors.state(request, !this.is_spam(title, spamWords), "title", "employer.job.form.error.spam");
		errors.state(request, !this.is_spam(description, spamWords), "description", "employer.job.form.error.spam");
		errors.state(request, !this.is_spam(moreInfo, spamWords), "moreInfo", "employer.job.form.error.spam");
		errors.state(request, !this.is_spam(descriptor, spamWords), "descriptor", "employer.job.form.error.spam");
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;
		Descriptor oldDescriptor = entity.getDescriptor();
		if (oldDescriptor.getTitle().equals(this.repository.findOneById(entity.getId()).getDescriptor().getTitle())) {
			this.repository.save(entity);
		} else {
			if (oldDescriptor != null) {
				oldDescriptor.setJob(null);
				this.repository.save(oldDescriptor);
			}
			String title = request.getModel().getString("descriptor");
			if (title == "" || title == null) {
				entity.setDescriptor(null);
			} else {
				Descriptor newDescriptor = new Descriptor();
				newDescriptor.setTitle(title);
				newDescriptor.setJob(entity);
				this.repository.save(newDescriptor);

				entity.setDescriptor(newDescriptor);
			}
			this.repository.save(entity);
		}

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