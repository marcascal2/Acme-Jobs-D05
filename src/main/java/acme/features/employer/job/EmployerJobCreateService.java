
package acme.features.employer.job;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.jobs.Job;
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

		request.unbind(entity, model, "reference", "title", "status", "deadline");
		request.unbind(entity, model, "salary", "moreInfo", "description", "finalMode");
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

		String descriptor = request.getModel().getString("descriptor");
		String title = request.getModel().getString("title");
		String reference = request.getModel().getString("reference");
		String description = request.getModel().getString("description");
		String moreInfo = request.getModel().getString("moreInfo");

		Collection<SpamWord> spamWords = this.repository.findManyAllSpamWord();

		boolean hasDescriptor = !descriptor.isEmpty();
		boolean isFinalMode = request.getModel().getBoolean("finalMode");

		errors.state(request, !(isFinalMode && !hasDescriptor), "descriptor", "employer.job.form.error.descriptor");

		Collection<Job> jobs = this.repository.findManyByEmployerId(request.getPrincipal().getActiveRoleId());
		for (Job job : jobs) {
			errors.state(request, !reference.equals(job.getReference()), "reference", "employer.job.form.error.reference");
		}

		errors.state(request, this.is_spam(reference, spamWords), "spamWord", "employer.job.form.error.spam");
		errors.state(request, this.is_spam(title, spamWords), "spamWord", "employer.job.form.error.spam");
		errors.state(request, this.is_spam(description, spamWords), "spamWord", "employer.job.form.error.spam");
		errors.state(request, this.is_spam(moreInfo, spamWords), "spamWord", "employer.job.form.error.spam");
		errors.state(request, this.is_spam(descriptor, spamWords), "spamWord", "employer.job.form.error.spam");

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
}
