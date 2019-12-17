
package acme.features.employer.application;

import java.util.Collection;
import java.util.List;

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

		String jobId;

		request.unbind(entity, model, "referenceNumber", "moment", "status", "skills", "justification");
		jobId = request.getServletRequest().getParameter("job_id");
		if (jobId != null) {
			System.out.println("HEY MAQUINA, ESTOY PONIENDO EL ID");
			model.setAttribute("jobId", jobId);
		}
	}

	@Override
	public Collection<Application> findMany(final Request<Application> request) {
		assert request != null;
		Collection<Application> result;
		Principal principal;
		String jobId;
		String groupByCondition;

		jobId = request.getServletRequest().getParameter("job_id");

		if (jobId == null) {
			principal = request.getPrincipal();
			result = this.repository.findManyByEmployerId(principal.getActiveRoleId());
		} else {
			groupByCondition = request.getServletRequest().getParameter("group_by");
			if (!(groupByCondition == null)) {
				switch (groupByCondition) {
				case "reference":
					List<String[]> j = this.repository.groupedByReference(Integer.parseInt(jobId));
					j.forEach(x -> System.out.println(x.toString()));
					result = null;
					break;
				case "status":
					result = this.repository.groupedByStatus(Integer.parseInt(jobId));
					break;
				case "moment":
					result = this.repository.groupedByMoment(Integer.parseInt(jobId));
					break;
				default:
					result = this.repository.findManyByJobId(Integer.parseInt(jobId));
					break;
				}
			} else {
				result = this.repository.findManyByJobId(Integer.parseInt(jobId));
			}

		}

		return result;
	}

}
