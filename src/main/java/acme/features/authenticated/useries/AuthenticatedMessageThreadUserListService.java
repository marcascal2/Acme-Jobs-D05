
package acme.features.authenticated.useries;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message_threads.MessageThread;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedMessageThreadUserListService implements AbstractListService<Authenticated, UserAccount> {

	@Autowired
	AuthenticatedMessageThreadUserRepository repository;


	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "username");
	}

	@Override
	public Collection<UserAccount> findMany(final Request<UserAccount> request) {
		assert request != null;

		MessageThread messageThread;
		int messageThreadId;
		Collection<UserAccount> result;

		//		messageThreadId = request.getModel().getInteger("messageThreadId");
		messageThread = this.repository.findOneMessageThreadById(3250);
		result = messageThread.getUsers();

		return result;
	}

}
