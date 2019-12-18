
package acme.features.authenticated.message_thread_user_account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message_threads_user_accounts.MessageThreadUserAccount;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedMessageThreadUserAccountDeleteService implements AbstractDeleteService<Authenticated, MessageThreadUserAccount> {

	//Internal state ------------------------------------------------------------

	@Autowired
	AuthenticatedMessageThreadUserAccountRepository repository;


	@Override
	public boolean authorise(final Request<MessageThreadUserAccount> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<MessageThreadUserAccount> request, final MessageThreadUserAccount entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<MessageThreadUserAccount> request, final MessageThreadUserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "text", "moreInfo");
	}

	@Override
	public MessageThreadUserAccount findOne(final Request<MessageThreadUserAccount> request) {
		assert request != null;

		MessageThreadUserAccount result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<MessageThreadUserAccount> request, final MessageThreadUserAccount entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<MessageThreadUserAccount> request, final MessageThreadUserAccount entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

	@Override
	public void onSuccess(final Request<MessageThreadUserAccount> request, final Response<MessageThreadUserAccount> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
