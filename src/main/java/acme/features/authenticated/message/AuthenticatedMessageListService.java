
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message_threads.MessageThread;
import acme.entities.messages.Message;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageListService implements AbstractListService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		Principal principal;
		MessageThread messageThread;
		int messageThreadId;

		principal = request.getPrincipal();
		messageThreadId = request.getModel().getInteger("messageThreadId");
		messageThread = this.repository.findMessageThreadById(messageThreadId);

		return messageThread.getUsers().stream().anyMatch(x -> x.getId() == principal.getAccountId());
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
