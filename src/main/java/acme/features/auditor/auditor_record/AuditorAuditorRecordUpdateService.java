
package acme.features.auditor.auditor_record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditor_records.AuditorRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuditorAuditorRecordUpdateService implements AbstractUpdateService<Auditor, AuditorRecord> {

	@Autowired
	AuditorAuditorRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditorRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<AuditorRecord> request, final AuditorRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<AuditorRecord> request, final AuditorRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "status", "creationMoment", "body");
	}

	@Override
	public AuditorRecord findOne(final Request<AuditorRecord> request) {
		assert request != null;

		AuditorRecord result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<AuditorRecord> request, final AuditorRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<AuditorRecord> request, final AuditorRecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
