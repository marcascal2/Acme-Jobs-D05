
package acme.features.auditor.audit_record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audit_records.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuditorAuditRecordUpdateService implements AbstractUpdateService<Auditor, AuditRecord> {

	//Internal state ------------------------------------------------------------
	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		boolean result;

		int idAuditRecord = request.getModel().getInteger("id");
		int idAuditor = request.getPrincipal().getAccountId();
		AuditRecord a = this.repository.findOneById(idAuditRecord);

		result = a.getAuditor().getId() == idAuditor ? true : false;

		return result;
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");

	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "status", "body");
	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {

		int idAuditRecord = request.getModel().getInteger("id");

		AuditRecord result = this.repository.findOneById(idAuditRecord);

		return result;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<AuditRecord> request, final Response<AuditRecord> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
