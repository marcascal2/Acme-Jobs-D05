
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class EmployerApplicationListMineService implements AbstractListService<Employer, Application> {

	//Internal state
	@Autowired
	EmployerApplicationRepository repository;


	//AbstractListService<Employer, Application>
	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "referenceNumber", "moment", "status", "skills", "justification");

		model.setAttribute("job_id", request.getServletRequest().getParameter("job_id"));
	}

	@Override
	public Collection<Application> findMany(final Request<Application> request) {
		assert request != null;
		Collection<Application> result;
		Principal principal;
		String jobId;

		jobId = request.getServletRequest().getParameter("job_id");

		if (request.getServletRequest().getParameter("job_id") == null) {
			principal = request.getPrincipal();
			result = this.repository.findManyByEmployerId(principal.getActiveRoleId());
		} else {

			if (request.getServletRequest().getParameter("group_by") == "reference") {
				result = this.repository.groupedByReference();
			} else if (request.getServletRequest().getParameter("group_by") == "status") {
				result = this.repository.groupedByStatus();
			} else if (request.getServletRequest().getParameter("group_by") == "moment") {
				result = this.repository.groupedByMoment();
			} else {
				result = this.repository.findManyByJobId(Integer.parseInt(jobId));
			}
		}

		return result;
	}

}
