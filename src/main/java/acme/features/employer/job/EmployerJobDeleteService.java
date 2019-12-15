
package acme.features.employer.job;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class EmployerJobDeleteService implements AbstractDeleteService<Employer, Job> {

	//Internal state
	@Autowired
	EmployerJobRepository repository;


	//AbstractDeleteService<Employe, Job>

	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "reference", "status", "title", "deadline", "salary", "description", "moreInfo", "finalMode");
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

		Collection<Application> applications = new ArrayList<Application>();
		applications.addAll(entity.getApplication());
		for (Application a : applications) {
			errors.state(request, a.getWorker() == null, "application", "employer.job.form.error.application");
		}
	}

	@Override
	public void delete(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;
		this.repository.deleteAll(entity.getDescriptor().getDuty());
		this.repository.delete(entity.getDescriptor());
		this.repository.delete(entity);

	}

}
