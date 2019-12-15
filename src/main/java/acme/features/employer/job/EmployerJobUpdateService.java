
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	//Internal state
	@Autowired
	EmployerJobRepository repository;


	//AbstractUpdateService<Employer, Job>
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

		request.bind(entity, errors, "descriptor");

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

		Job job;
		job = this.repository.findOneById(entity.getId());

		errors.state(request, !job.isFinalMode() || !entity.isFinalMode(), "finalMode", "employer.job.form.error.finalMode");

		Collection<Duty> duties = this.repository.findManyByDescriptorId(entity.getDescriptor().getId());
		Double sum = 0.0;
		boolean sum100 = sum == 100;

		for (Duty duty : duties) {
			if (duty.getPercentageTimeForWeek() != null) {
				sum = sum + duty.getPercentageTimeForWeek();
			}
		}
		System.out.println(sum100);

		errors.state(request, !(entity.isFinalMode() && !sum100), "percentageTimeForWeek", "employer.duty.form.error.percentage");
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		assert entity != null;

		Descriptor oldDescriptor = entity.getDescriptor();
		if (oldDescriptor != null) {
			oldDescriptor.setJob(null);
			this.repository.save(oldDescriptor);
		}
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
