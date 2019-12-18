
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerDutyUpdateService implements AbstractUpdateService<Employer, Duty> {

	//Internal state
	@Autowired
	EmployerDutyRepository repository;


	//AbstractUpdateService<Employer, Duty>

	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		boolean result = false;

		int idEmployer = request.getPrincipal().getActiveRoleId();
		int idDuty = request.getModel().getInteger("id");

		Duty duty = this.repository.findOneById(idDuty);
		Employer employer = this.repository.findEmployerById(idEmployer);

		result = duty.getDescriptor().getJob().getEmployer().equals(employer);

		return result;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "description", "percentageTimeForWeek");
	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;
		Duty result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);
		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String descriptorId;
		descriptorId = request.getServletRequest().getParameter("descriptor_id");
		Collection<Duty> duties = this.repository.findManyByDescriptorId(Integer.parseInt(descriptorId));
		Double sum = 0.0;
		for (Duty duty : duties) {
			if (duty.getPercentageTimeForWeek() != null && duty.getId() != entity.getId()) {
				sum = sum + duty.getPercentageTimeForWeek();
			}
		}

		errors.state(request, sum >= 0 && sum <= 100, "percentageTimeForWeek", "employer.duty.form.error.percentageLimit");

	}

	@Override
	public void update(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}

}
