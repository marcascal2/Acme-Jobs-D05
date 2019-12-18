
package acme.features.authenticated.message;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.message_threads.MessageThread;
import acme.entities.messages.Message;
import acme.entities.spam_words.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {

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
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "tags", "body");

		int messageThreadId = request.getModel().getInteger("messageThreadId");
		model.setAttribute("messageThreadId", messageThreadId);
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		Message result = new Message();

		int messageThreadId = request.getModel().getInteger("messageThreadId");
		MessageThread messageThread = this.repository.findMessageThreadById(messageThreadId);

		result.setMessageThread(messageThread);

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String confirmCreation = request.getModel().getString("confirmation");
		boolean confirmation = confirmCreation.equals("true");
		errors.state(request, confirmation, "confirmation", "authenticated.message.form.error.confirmation");

		Collection<SpamWord> spamWords;
		spamWords = this.repository.findAllSpamWords();

		boolean bodyIsSpam = this.is_spam(entity.getBody(), spamWords);
		boolean tagsAreSpam = this.is_spam(entity.getTags(), spamWords);
		boolean titleIsSpam = this.is_spam(entity.getTitle(), spamWords);

		errors.state(request, !bodyIsSpam, "body", "sponsor.messsage.form.error.body.spam");
		errors.state(request, !tagsAreSpam, "tags", "sponsor.messsage.form.error.tags.spam");
		errors.state(request, !titleIsSpam, "title", "sponsor.messsage.form.error.title.spam");
	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

	private boolean is_spam(final String text, final Collection<SpamWord> spamWords) {
		List<String> list = Arrays.asList(text.split(" "));

		for (SpamWord spamWord : spamWords) {
			double spanishFrequency = (double) Collections.frequency(list, spamWord.getSpanishTranslation()) / list.size() * 100;
			if (spanishFrequency > spamWord.getSpamThreshold()) {
				return true;
			}
			double englishFrequency = (double) Collections.frequency(list, spamWord.getEnglishTranslation()) / list.size() * 100;
			if (englishFrequency > spamWord.getSpamThreshold()) {
				return true;
			}
		}

		return false;
	}

}
