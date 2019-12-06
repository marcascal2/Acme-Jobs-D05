
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class EmployerApplicationShowService implements AbstractShowService<Employer, Application> {

	//Internal state
	@Autowired
	EmployerApplicationRepository repository;


	//AbstractShowService<Employer,Application>
	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		boolean result;
		int appId;
		Application application;
		Employer employer;
		Principal principal;
		appId = request.getModel().getInteger("id");
		application = this.repository.findOneById(appId);
		employer = application.getJob().getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "referenceNumber", "moment", "status", "skills");
		request.unbind(entity, model, "statement", "qualifications");

		model.setAttribute("job", entity.getJob().getTitle());
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;
		Application result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}
}
