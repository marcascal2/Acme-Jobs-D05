
package acme.features.authenticated.message_thread_user_account;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message_threads.MessageThread;
import acme.entities.message_threads_user_accounts.MessageThreadUserAccount;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageThreadUserAccountCreateService implements AbstractCreateService<Authenticated, MessageThreadUserAccount> {

	@Autowired
	AuthenticatedMessageThreadUserAccountRepository repository;


	@Override
	public boolean authorise(final Request<MessageThreadUserAccount> request) {
		assert request != null;
		int accountId = request.getPrincipal().getAccountId();
		UserAccount user = this.repository.findOneUserAccountById(accountId);
		int messageThreadId = request.getModel().getInteger("messageThreadId");
		MessageThread messageThread = this.repository.findOneMessageThreadById(messageThreadId);

		boolean result = messageThread.getMessageThreadsOfUserAccount().stream().anyMatch(x -> x.getUserAccount().equals(user));

		return result;
	}

	@Override
	public void bind(final Request<MessageThreadUserAccount> request, final MessageThreadUserAccount entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "userAccount", "messageThread");
	}

	@Override
	public void unbind(final Request<MessageThreadUserAccount> request, final MessageThreadUserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;

		int messageThreadId = request.getModel().getInteger("messageThreadId");

		Collection<UserAccount> allUserAccounts = this.repository.findManyUserAccounts(messageThreadId);
		MessageThread messageThread = this.repository.findOneMessageThreadById(messageThreadId);
		List<UserAccount> existingUserAccounts = messageThread.getMessageThreadsOfUserAccount().stream().map(x -> x.getUserAccount()).collect(Collectors.toList());
		List<UserAccount> usersToAdd = allUserAccounts.stream().filter(x -> !existingUserAccounts.contains(x)).collect(Collectors.toList());

		List<String> ids = usersToAdd.stream().map(x -> String.valueOf(x.getId())).collect(Collectors.toList());
		List<String> usernames = usersToAdd.stream().map(x -> x.getUsername()).collect(Collectors.toList());
		String[] ids_arrays = ids.stream().toArray(n -> new String[n]);
		String[] usernames_arrays = usernames.stream().toArray(n -> new String[n]);

		model.setAttribute("user_usernames", usernames_arrays);
		model.setAttribute("user_ids", ids_arrays);
		model.setAttribute("messageThreadId", messageThreadId);

		request.unbind(entity, model);
	}

	@Override
	public MessageThreadUserAccount instantiate(final Request<MessageThreadUserAccount> request) {
		assert request != null;

		MessageThreadUserAccount result;
		UserAccount user = new UserAccount();
		MessageThread messageThread = new MessageThread();

		result = new MessageThreadUserAccount();
		result.setUserAccount(user);
		result.setMessageThread(messageThread);

		return result;
	}

	@Override
	public void validate(final Request<MessageThreadUserAccount> request, final MessageThreadUserAccount entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<MessageThreadUserAccount> request, final MessageThreadUserAccount entity) {
		assert request != null;
		assert entity != null;

		int userId = request.getModel().getInteger("userToAdd");
		int messageThreadId = request.getModel().getInteger("messageThreadId");

		UserAccount user = this.repository.findOneUserAccountById(userId);
		MessageThread messageThread = this.repository.findOneMessageThreadById(messageThreadId);

		entity.setMessageThread(messageThread);
		entity.setUserAccount(user);
		this.repository.save(entity);

		Collection<MessageThreadUserAccount> messageThreadsUserAccounts = messageThread.getMessageThreadsOfUserAccount();
		messageThreadsUserAccounts.add(entity);
		messageThread.setMessageThreadsOfUserAccount(messageThreadsUserAccounts);
		this.repository.save(messageThread);

	}

}
