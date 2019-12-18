
package acme.features.sponsor.commercial_banners;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.credit_cards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.entities.spam_words.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorCommercialBannerCreateService implements AbstractCreateService<Sponsor, CommercialBanner> {

	//Internal state
	@Autowired
	SponsorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		Principal principal;
		Sponsor sponsor;
		boolean hasCreditCard;

		principal = request.getPrincipal();
		sponsor = this.repository.findSponsorbySponsorId(principal.getActiveRoleId());
		hasCreditCard = !sponsor.getCreditCard().equals(null);

		return hasCreditCard;
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

		request.unbind(entity, model, "picture", "slogan", "target", "creditCard.titleHolder", "creditCard.creditCardNumber", "creditCard.month", "creditCard.year", "creditCard.cvc");
	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {
		assert request != null;

		Principal principal;
		Sponsor sponsor;
		CommercialBanner result = new CommercialBanner();

		principal = request.getPrincipal();
		sponsor = this.repository.findSponsorbySponsorId(principal.getActiveRoleId());
		result.setSponsor(sponsor);
		result.setCreditCard(sponsor.getCreditCard());

		return result;
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

		int uaId = request.getPrincipal().getAccountId();
		CommercialBanner cb = this.repository.findOneCommercialBannerBySponsorId(uaId);
		CreditCard c = cb.getCreditCard();
		String s1 = c.getMonth() + "/" + c.getYear();
		LocalDate exp = LocalDate.parse(s1, DateTimeFormatter.ofPattern("MM/yyyy"));
		String s2 = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/yyyy"));
		LocalDate now = LocalDate.parse(s2, DateTimeFormatter.ofPattern("MM/yyyy"));

		boolean expiredCard = exp.compareTo(now) < 0;
		errors.state(request, expiredCard, "expiredCard", "sponsor.commercial-banner.form.errors.expiredCard");
	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		int uaId = request.getPrincipal().getActiveRoleId();
		CommercialBanner cb = this.repository.findOneCommercialBannerBySponsorId(uaId);
		CreditCard updatedCC = cb.getCreditCard();

		CreditCard newCC = new CreditCard();

		newCC.setTitleHolder(updatedCC.getTitleHolder());
		newCC.setCvc(updatedCC.getCvc());
		newCC.setCreditCardNumber(updatedCC.getCreditCardNumber());
		newCC.setMonth(updatedCC.getMonth());
		newCC.setYear(updatedCC.getYear());

		entity.setCreditCard(newCC);

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
