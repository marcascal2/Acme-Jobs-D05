
package acme.features.authenticated.sponsor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.credit_cards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedSponsorCreateService implements AbstractCreateService<Authenticated, Sponsor> {

	//Internal state
	@Autowired
	AuthenticatedSponsorRepository repository;


	//AbstractCreateService<Authenticated, Sponsor>

	@Override
	public boolean authorise(final Request<Sponsor> request) {
		assert request != null;

		boolean result = true;

		int idUA = request.getPrincipal().getAccountId();
		UserAccount ua = this.repository.findOneUserAccountById(idUA);
		Sponsor s = this.repository.findOneSponsorByUserAccountId(idUA);
		if (ua.getRoles().contains(s)) {
			result = false;
		}

		return result;
	}

	@Override
	public void bind(final Request<Sponsor> request, final Sponsor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Sponsor> request, final Sponsor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "organisationName", "creditCard.titleHolder", "creditCard.creditCardNumber", "creditCard.month", "creditCard.year", "creditCard.cvc");
	}

	@Override
	public Sponsor instantiate(final Request<Sponsor> request) {
		assert request != null;

		Sponsor result;
		Principal principal;
		int userAccountId;
		UserAccount ua;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		ua = this.repository.findOneUserAccountById(userAccountId);

		result = new Sponsor();
		result.setUserAccount(ua);
		return result;
	}

	@Override
	public void validate(final Request<Sponsor> request, final Sponsor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		int uaId = request.getPrincipal().getAccountId();
		Sponsor sp = this.repository.findOneSponsorByUserAccountId(uaId);
		CreditCard c = sp.getCreditCard();
		String s1 = c.getMonth() + "/" + c.getYear();
		LocalDate exp = LocalDate.parse(s1, DateTimeFormatter.ofPattern("MM/yyyy"));
		String s2 = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/yyyy"));
		LocalDate now = LocalDate.parse(s2, DateTimeFormatter.ofPattern("MM/yyyy"));

		boolean expiredCard = exp.compareTo(now) < 0;
		errors.state(request, expiredCard, "expiredCard", "authenticated.sponsor.form.errors.expiredCard");

	}

	@Override
	public void create(final Request<Sponsor> request, final Sponsor entity) {
		assert request != null;
		assert entity != null;

		int uaId = request.getPrincipal().getAccountId();
		Sponsor sp = this.repository.findOneSponsorByUserAccountId(uaId);
		CreditCard updatedCC = sp.getCreditCard();

		CreditCard newCC = new CreditCard();

		newCC.setTitleHolder(updatedCC.getTitleHolder());
		newCC.setCvc(updatedCC.getCvc());
		newCC.setCreditCardNumber(updatedCC.getCreditCardNumber());
		newCC.setMonth(updatedCC.getMonth());
		newCC.setYear(updatedCC.getYear());

		entity.setCreditCard(newCC);

		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<Sponsor> request, final Response<Sponsor> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}
}
