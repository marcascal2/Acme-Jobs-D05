
package acme.features.sponsor.credit_card;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.credit_cards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorCreditCardUpdateService implements AbstractUpdateService<Sponsor, CreditCard> {

	@Autowired
	SponsorCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		int sponsorId = request.getPrincipal().getActiveRoleId();
		int sId = request.getModel().getInteger("sponsorId");
		boolean result = sponsorId == sId;

		return result;
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int sponsorId = request.getPrincipal().getAccountId();
		model.setAttribute("sponsorId", sponsorId);

		request.unbind(entity, model, "titleHolder", "creditCardNumber", "month", "year", "cvc");
	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;

		int sponsorId = request.getPrincipal().getActiveRoleId();
		CreditCard result = this.repository.findOneBySponsorId(sponsorId);

		return result;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		CreditCard c = entity;

		String newMonth = entity.getMonth();
		String newYear = entity.getYear();
		LocalDate newDate = LocalDate.of(Integer.parseInt(newYear), Integer.parseInt(newMonth), 1);
		LocalDate actualDate = LocalDate.now();
		LocalDate limitDate = actualDate.withDayOfMonth(1);

		if (c != null) {
			boolean expiredCard = limitDate.isBefore(newDate);
			errors.state(request, expiredCard, "year", "sponsor.credit-card.form.errors.expiredCard");
		}

	}

	@Override
	public void update(final Request<CreditCard> request, final CreditCard entity) {
		assert request != null;
		assert entity != null;

		int sponsorId = request.getPrincipal().getAccountId();
		Sponsor s = this.repository.findOneSponsorByUserAccountId(sponsorId);

		s.setCreditCard(entity);

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<CreditCard> request, final Response<CreditCard> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
