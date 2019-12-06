
package acme.features.sponsor.commercial_banners;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class SponsorCommercialBannerListMineService implements AbstractListService<Sponsor, CommercialBanner> {

	//Internal state
	@Autowired
	SponsorCommercialBannerRepository repository;


	//AbstractListService<Sponsor, Announcement> interface
	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "picture", "slogan", "target", "creditCard");
	}

	@Override
	public Collection<CommercialBanner> findMany(final Request<CommercialBanner> request) {
		assert request != null;
		Collection<CommercialBanner> result = Collections.emptyList();
		Principal principal = request.getPrincipal();
		int sponsorId = principal.getActiveRoleId();
		Sponsor sponsor = this.repository.findSponsorbySponsorId(principal.getActiveRoleId());
		boolean creditCard;
		creditCard = sponsor.getCreditCard().equals("") || sponsor.getCreditCard() == null ? false : true;
		if (creditCard) {
			result = this.repository.findManyCommercialBannersBySponsor(sponsorId);
		}
		return result;
	}
}
