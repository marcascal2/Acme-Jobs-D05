
package acme.features.administrator.auditors_request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditors_request.AuditorsRequest;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorAuditorShowService implements AbstractShowService<Administrator, AuditorsRequest> {

	@Autowired
	private AdministratorAuditorRepository repository;


	@Override
	public boolean authorise(final Request<AuditorsRequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<AuditorsRequest> request, final AuditorsRequest entity, final Model model) {
		assert request != null;
		assert entity != null;

		request.unbind(entity, model, "firm", "responsabilityStatement");
	}

	@Override
	public AuditorsRequest findOne(final Request<AuditorsRequest> request) {
		assert request != null;

		AuditorsRequest result;
		int auditorRequestId;
		auditorRequestId = request.getModel().getInteger("id");

		result = this.repository.findOneById(auditorRequestId);
		return result;
	}

}
