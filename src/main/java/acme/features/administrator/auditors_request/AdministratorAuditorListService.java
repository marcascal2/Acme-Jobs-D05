
package acme.features.administrator.auditors_request;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditors_request.AuditorsRequest;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorAuditorListService implements AbstractListService<Administrator, AuditorsRequest> {

	// Internal state ------------------------------------------------------------------

	@Autowired
	AdministratorAuditorRepository repository;

	// AbstractListService<Administrator, AuditorsRequest> interface ----------------------


	@Override
	public boolean authorise(final Request<AuditorsRequest> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<AuditorsRequest> request, final AuditorsRequest entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "firm", "responsabilityStatement");

		String username = entity.getUserAccount().getUsername();
		model.setAttribute("username", username);
	}

	@Override
	public Collection<AuditorsRequest> findMany(final Request<AuditorsRequest> request) {
		assert request != null;

		Collection<AuditorsRequest> result;

		result = this.repository.findMany();

		return result;
	}
}
