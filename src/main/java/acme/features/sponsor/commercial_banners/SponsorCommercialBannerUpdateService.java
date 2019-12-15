
package acme.features.sponsor.commercial_banners;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.entities.spam_words.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorCommercialBannerUpdateService implements AbstractUpdateService<Sponsor, CommercialBanner> {

	@Autowired
	SponsorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		int sponsorId;
		int bannerId;
		Sponsor sponsor;
		CommercialBanner banner;
		boolean result;

		sponsorId = request.getPrincipal().getActiveRoleId();
		bannerId = request.getModel().getInteger("id");
		sponsor = this.repository.findSponsorbySponsorId(sponsorId);
		banner = this.repository.findOneById(bannerId);

		result = banner.getSponsor().equals(sponsor);

		return result;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creditCard");
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "target");
	}

	@Override
	public CommercialBanner findOne(final Request<CommercialBanner> request) {
		int bannerId;
		CommercialBanner banner;

		bannerId = request.getModel().getInteger("id");
		banner = this.repository.findOneById(bannerId);

		return banner;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Collection<SpamWord> spamWords;
		spamWords = this.repository.findAllSpamWords();

		boolean isSpam = this.is_spam(entity.getSlogan(), spamWords);
		errors.state(request, !isSpam, "slogan", "sponsor.commercial-banner.form.error.span");
	}

	@Override
	public void update(final Request<CommercialBanner> request, final CommercialBanner entity) {
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
