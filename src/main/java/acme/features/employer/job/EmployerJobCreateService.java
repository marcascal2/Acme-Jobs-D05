
package acme.features.employer.job;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.jobs.JobStatus;
import acme.entities.roles.Employer;
import acme.entities.spam_words.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerJobCreateService implements AbstractCreateService<Employer, Job> {

	//Internal state
	@Autowired
	EmployerJobRepository repository;


	//AbstractCreateService<Employer, Job>
	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		Employer employer;
		Principal principal;

		employer = this.repository.findEmployerById(request.getPrincipal().getActiveRoleId());
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();

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

		request.unbind(entity, model, "reference", "title", "status", "deadline");
		request.unbind(entity, model, "salary", "moreInfo", "description");
	}

	@Override
	public Job instantiate(final Request<Job> request) {
		Job result;
		Employer employer;
		employer = this.repository.findEmployerById(request.getPrincipal().getActiveRoleId());
		result = new Job();
		result.setEmployer(employer);
		result.setApplication(null);
		return result;

	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//CANNOT REPEAT THE JOB REFERENCE

		String newReference = entity.getReference();
		if (newReference != null && newReference != "") {
			Job repeatedJob = this.repository.findOneByReference(newReference);
			boolean referenceIsRepeated = repeatedJob != null;
			errors.state(request, !referenceIsRepeated, "reference", "employer.job.form.error.reference");
		}

		//CHECK THAT IS NOT FINAL MODE

		JobStatus status = entity.getStatus();
		if (status != null) {
			boolean isFinalMode = status.equals(JobStatus.PUBLISHED);
			errors.state(request, !isFinalMode, "status", "employer.duty.form.error.percentage");
		}
	}

	@Override
	public void create(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

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
