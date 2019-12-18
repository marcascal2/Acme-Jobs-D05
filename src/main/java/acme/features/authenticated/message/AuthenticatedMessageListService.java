
package acme.features.authenticated.message;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message_threads.MessageThread;
import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListService implements AbstractListService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		boolean result;
		UserAccount user;
		MessageThread messageThread;
		Collection<UserAccount> authorisedUsers;
		int messageThreadId;

		user = this.repository.findOneUserAccountById(request.getPrincipal().getAccountId());
		messageThreadId = request.getModel().getInteger("messageThreadId");
		messageThread = this.repository.findMessageThreadById(messageThreadId);

		authorisedUsers = messageThread.getMessageThreadsOfUserAccount().stream().map(x -> x.getUserAccount()).collect(Collectors.toList());

		result = authorisedUsers.contains(user);

		return result;
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "tags", "body");
	}

	@Override
	public Collection<Message> findMany(final Request<Message> request) {
		assert request != null;

		Collection<Message> result;
		int messageThreadId;

		messageThreadId = request.getModel().getInteger("messageThreadId");

		result = this.repository.findManyByMessageThread(messageThreadId);

		return result;
	}

}
