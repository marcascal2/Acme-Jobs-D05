
package acme.features.authenticated.message_thread_user_account;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message_threads.MessageThread;
import acme.entities.message_threads_user_accounts.MessageThreadUserAccount;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageThreadUserAccountShowService implements AbstractShowService<Authenticated, MessageThreadUserAccount> {

	@Autowired
	AuthenticatedMessageThreadUserAccountRepository repository;


	@Override
	public boolean authorise(final Request<MessageThreadUserAccount> request) {
		assert request != null;

		int messageThreadUserAccountId;
		MessageThreadUserAccount messageThreadUserAccount;
		MessageThread messageThread;
		Principal principal;
		UserAccount user;
		Collection<UserAccount> authorisedUsers;
		boolean result;

		messageThreadUserAccountId = request.getModel().getInteger("id");
		messageThreadUserAccount = this.repository.findOneById(messageThreadUserAccountId);
		principal = request.getPrincipal();
		user = this.repository.findOneUserAccountById(principal.getAccountId());
		messageThread = messageThreadUserAccount.getMessageThread();

		authorisedUsers = messageThread.getMessageThreadsOfUserAccount().stream().map(x -> x.getUserAccount()).collect(Collectors.toList());
		result = authorisedUsers.contains(user);
		return result;
	}

	@Override
	public void unbind(final Request<MessageThreadUserAccount> request, final MessageThreadUserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "userAccount.username");
		model.setAttribute("fullName", entity.getUserAccount().getIdentity().getFullName());
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

}
