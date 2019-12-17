
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyCreateService implements AbstractCreateService<Employer, Duty> {

	//Internal state
	@Autowired
	EmployerDutyRepository repository;


	//AbstractCreateService<Employer, Duty>

	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		boolean result = false;

		int idEmployer = request.getPrincipal().getActiveRoleId();
		int idDescriptor = request.getModel().getInteger("descriptor_id");

		Descriptor descriptor = this.repository.findDescriptorById(idDescriptor);
		Employer employer = this.repository.findEmployerById(idEmployer);

		result = descriptor.getJob().getEmployer().equals(employer);

		return result;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "percentageTimeForWeek");

		String descriptorId = request.getServletRequest().getParameter("descriptor_id");
		model.setAttribute("descriptor_id", Integer.parseInt(descriptorId));
	}

	@Override
	public Duty instantiate(final Request<Duty> request) {
		Duty result;
		Descriptor descriptor;
		String descriptorId;
		descriptorId = request.getServletRequest().getParameter("descriptor_id");

		descriptor = this.repository.findDescriptorById(Integer.parseInt(descriptorId));
		result = new Duty();
		result.setDescriptor(descriptor);

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
			if (duty.getPercentageTimeForWeek() != null) {
				sum = sum + duty.getPercentageTimeForWeek();
			}
		}

		double new_percentage = entity.getPercentageTimeForWeek();

		sum = sum + new_percentage;

		errors.state(request, !(sum > 100), "percentageTimeForWeek", "employer.duty.form.error.percentageLimit");
	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}
}
