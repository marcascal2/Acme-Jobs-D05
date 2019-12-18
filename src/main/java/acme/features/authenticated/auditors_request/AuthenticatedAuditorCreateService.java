
package acme.features.authenticated.auditors_request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditors_request.AuditorsRequest;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedAuditorCreateService implements AbstractCreateService<Authenticated, AuditorsRequest> {

	@Autowired
	private AuthenticatedAuditorRepository repository;


	@Override
	public boolean authorise(final Request<AuditorsRequest> request) {
		assert request != null;

		AuditorsRequest auditorRequest;
		Principal principal;
		boolean alreadyRequested;
		principal = request.getPrincipal();
		auditorRequest = this.repository.userAlreadyRequested(principal.getAccountId());
		alreadyRequested = !(auditorRequest == null);

		if (alreadyRequested) {
			return false;
		}

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
	public AuditorsRequest instantiate(final Request<AuditorsRequest> request) {
		assert request != null;

		AuditorsRequest result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new AuditorsRequest();
		result.setUserAccount(userAccount);
		result.setAccepted(false);

		return result;
	}

	@Override
	public void validate(final Request<AuditorsRequest> request, final AuditorsRequest entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AuditorsRequest> request, final AuditorsRequest entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
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
