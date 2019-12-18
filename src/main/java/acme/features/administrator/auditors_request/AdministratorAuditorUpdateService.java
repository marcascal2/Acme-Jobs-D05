
package acme.features.administrator.auditors_request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditors_request.AuditorsRequest;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorAuditorUpdateService implements AbstractUpdateService<Administrator, AuditorsRequest> {

	@Autowired
	private AdministratorAuditorRepository repository;


	@Override
	public boolean authorise(final Request<AuditorsRequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<AuditorsRequest> request, final AuditorsRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<AuditorsRequest> request, final AuditorsRequest entity, final Model model) {
		assert request != null;
		assert entity != null;

		request.unbind(entity, model, "firm", "responsabilityStatement");
	}

	@Override
	public void validate(final Request<AuditorsRequest> request, final AuditorsRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public AuditorsRequest findOne(final Request<AuditorsRequest> request) {
		AuditorsRequest auditorRequest;
		int auditorRequestId;

		auditorRequestId = request.getModel().getInteger("id");

		auditorRequest = this.repository.findOneById(auditorRequestId);

		return auditorRequest;
	}

	@Override
	public void update(final Request<AuditorsRequest> request, final AuditorsRequest entity) {
		entity.setAccepted(true);

		Auditor auditor = new Auditor();
		auditor.setFirm(entity.getFirm());
		auditor.setResponsabilityStatement(entity.getResponsabilityStatement());
		auditor.setUserAccount(entity.getUserAccount());

		this.repository.save(entity);
		this.repository.save(auditor);
	}

	@Override
	public void onSuccess(final Request<AuditorsRequest> request, final Response<AuditorsRequest> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
