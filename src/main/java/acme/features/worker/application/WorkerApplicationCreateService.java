
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.applications.ApplicationStatus;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	@Autowired
	private WorkerApplicationRepository repository;


	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		boolean result = true;

		int idWorker = request.getPrincipal().getActiveRoleId();
		int idJob = request.getModel().getInteger("idJob");
		Job job = this.repository.findJobById(idJob);
		Worker w = this.repository.findWorkerById(idWorker);

		for (Application a : job.getApplication()) {
			if (a.getWorker().equals(w)) {
				result = false;
				break;
			}
		}

		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int workerId = request.getPrincipal().getActiveRoleId();
		Worker worker = this.repository.findWorkerById(workerId);

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int workerId = request.getPrincipal().getActiveRoleId();
		Worker worker = this.repository.findWorkerById(workerId);
		entity.setQualifications(worker.getQualifications());
		entity.setSkills(worker.getSkills());

		request.unbind(entity, model, "referenceNumber", "status", "statement", "skills", "qualifications");

		int jobId;
		jobId = request.getModel().getInteger("idJob");
		model.setAttribute("idJob", jobId);
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		assert request != null;

		Application result;
		int jobId;
		int workerId;
		Job job;
		Worker worker;

		jobId = request.getModel().getInteger("idJob");
		job = this.repository.findJobById(jobId);

		workerId = request.getPrincipal().getActiveRoleId();
		worker = this.repository.findWorkerById(workerId);

		result = new Application();
		result.setJob(job);
		result.setWorker(worker);
		result.setStatus(ApplicationStatus.PENDING);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		String reference = entity.getReferenceNumber();
		Application a = this.repository.findOneApplicationByReference(reference);

		errors.state(request, a == null, "referenceNumber", "worker.application.form.error.reference");
	}

	@Override
	public void create(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Application> request, final Response<Application> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
