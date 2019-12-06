
package acme.features.administrator.spam_word;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam_words.SpamWord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorSpamWordListService implements AbstractListService<Administrator, SpamWord> {

	//Internal state
	@Autowired
	AdministratorSpamWordRepository repository;


	@Override
	public boolean authorise(final Request<SpamWord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<SpamWord> request, final SpamWord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "spanishTranslation", "englishTranslation", "spamThreshold");
	}

	@Override
	public Collection<SpamWord> findMany(final Request<SpamWord> request) {
		assert request != null;
		Collection<SpamWord> result;
		result = this.repository.findManyAll();
		return result;
	}

}
