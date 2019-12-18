
package acme.features.authenticated.message_thread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message_threads.MessageThread;
import acme.entities.message_threads_user_accounts.MessageThreadUserAccount;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageThreadCreateService implements AbstractCreateService<Authenticated, MessageThread> {

	//Internal state ------------------------------------------------------------

	@Autowired
	AuthenticatedMessageThreadRepository repository;

	//AbstractCreateService<Administrator, MessageThread> interface --------------


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title");
	}

	@Override
	public MessageThread instantiate(final Request<MessageThread> request) {
		MessageThread result;

		result = new MessageThread();

		return result;
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<MessageThread> request, final MessageThread entity) {
		Date moment;
		MessageThreadUserAccount mtua = new MessageThreadUserAccount();
		Principal principal = request.getPrincipal();
		UserAccount user = this.repository.findUserAccountById(principal.getAccountId());
		Collection<MessageThreadUserAccount> messageThreadUsers = new ArrayList<MessageThreadUserAccount>();

		entity.setMessageThreadsOfUserAccount(messageThreadUsers);

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);

		this.repository.save(entity);

		mtua.setUserAccount(user);
		mtua.setMessageThread(entity);
		this.repository.save(mtua);

		messageThreadUsers.add(mtua);
		this.repository.save(entity);
	}

}
