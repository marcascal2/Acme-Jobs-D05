
package acme.features.authenticated.message_thread;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message_threads.MessageThread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageThreadShowService implements AbstractShowService<Authenticated, MessageThread> {

	@Autowired
	private AuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		boolean result;
		int messageThreadId;
		MessageThread messageThread;
		Collection<UserAccount> authorisedUsers;
		Principal principal;

		messageThreadId = request.getModel().getInteger("id");
		messageThread = this.repository.findOneById(messageThreadId);
		authorisedUsers = messageThread.getUsers();
		principal = request.getPrincipal();

		result = authorisedUsers.stream().anyMatch(x -> x.getId() == principal.getAccountId());

		return result;
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment", "messages");
		model.setAttribute("messageThreadId", entity.getId());
	}

	@Override
	public MessageThread findOne(final Request<MessageThread> request) {
		assert request != null;

		MessageThread result;
		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findOneById(id);

		return result;
	}

}
