
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
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

		String newReference = request.getModel().getString("reference");
		String[] newJob = newReference.split("-");
		String newRefJob = newJob[1].trim();

		Collection<Job> totalJobs = this.repository.findAllJobs();
		for (Job job : totalJobs) {
			String oldReference = job.getReference();
			String[] oldJob = oldReference.split("-");
			String oldRefJob = oldJob[1].trim();

			errors.state(request, !oldRefJob.equals(newRefJob), "reference", "employer.job.form.error.ref");
			errors.state(request, !oldReference.equals(newReference), "reference", "employer.job.form.error.reference");
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

}
