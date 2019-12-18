
package acme.features.sponsor.commercial_banners;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.CommercialBanner;
import acme.entities.roles.Sponsor;
import acme.entities.spam_words.SpamWord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCommercialBannerRepository extends AbstractRepository {

	@Query("select cb from CommercialBanner cb where cb.id = ?1")
	CommercialBanner findOneById(int id);

	@Query("select cb from CommercialBanner cb where cb.sponsor.id = ?1")
	Collection<CommercialBanner> findManyCommercialBannersBySponsor(int sponsorId);

	@Query("select s from Sponsor s where s.id = ?1")
	Sponsor findSponsorbySponsorId(int sponsorId);

	@Query("select sw from SpamWord sw")
	Collection<SpamWord> findAllSpamWords();

	@Query("select c from CommercialBanner c where c.sponsor.id = ?1")
	CommercialBanner findOneCommercialBannerBySponsorId(int sponsorId);
}
