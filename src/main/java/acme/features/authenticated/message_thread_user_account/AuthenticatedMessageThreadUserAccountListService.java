
package acme.features.authenticated.message_thread_user_account;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message_threads.MessageThread;
import acme.entities.message_threads_user_accounts.MessageThreadUserAccount;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageThreadUserAccountListService implements AbstractListService<Authenticated, MessageThreadUserAccount> {

	@Autowired
	AuthenticatedMessageThreadUserAccountRepository repository;


	@Override
	public boolean authorise(final Request<MessageThreadUserAccount> request) {
		assert request != null;
		try {
			int accountId = request.getPrincipal().getAccountId();
			UserAccount user = this.repository.findOneUserAccountById(accountId);
			int messageThreadId = request.getModel().getInteger("messageThreadId");
			MessageThread messageThread = this.repository.findOneMessageThreadById(messageThreadId);

			boolean result = messageThread.getMessageThreadsOfUserAccount().stream().anyMatch(x -> x.getUserAccount().equals(user));
			return result;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void unbind(final Request<MessageThreadUserAccount> request, final MessageThreadUserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);

		model.setAttribute("username", entity.getUserAccount().getUsername());
		model.setAttribute("fullName", entity.getUserAccount().getIdentity().getFullName());
	}

	@Override
	public Collection<MessageThreadUserAccount> findMany(final Request<MessageThreadUserAccount> request) {
		assert request != null;

		Collection<MessageThreadUserAccount> result;
		int messageThreadId = request.getModel().getInteger("messageThreadId");

		result = this.repository.findManyByMessageThread(messageThreadId);

		return result;
	}

}
