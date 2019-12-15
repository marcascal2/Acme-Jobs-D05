
package acme.features.sponsor.non_commercial_banners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class SponsorNonCommercialBannerDeleteService implements AbstractDeleteService<Sponsor, NonCommercialBanner> {

	@Autowired
	SponsorNonCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<NonCommercialBanner> request) {
		int sponsorId;
		int bannerId;
		Sponsor sponsor;
		NonCommercialBanner banner;
		boolean result;

		sponsorId = request.getPrincipal().getActiveRoleId();
		bannerId = request.getModel().getInteger("id");
		sponsor = this.repository.findSponsorbySponsorId(sponsorId);
		banner = this.repository.findOneById(bannerId);

		result = banner.getSponsor().equals(sponsor);

		return result;
	}

	@Override
	public void bind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "target", "jingle");
	}

	@Override
	public NonCommercialBanner findOne(final Request<NonCommercialBanner> request) {
		int bannerId;
		NonCommercialBanner banner;

		bannerId = request.getModel().getInteger("id");
		banner = this.repository.findOneById(bannerId);

		return banner;
	}

	@Override
	public void validate(final Request<NonCommercialBanner> request, final NonCommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<NonCommercialBanner> request, final NonCommercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);
	}

}
