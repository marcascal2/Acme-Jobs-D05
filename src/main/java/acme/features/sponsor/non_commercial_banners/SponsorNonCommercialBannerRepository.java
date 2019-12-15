
package acme.features.sponsor.non_commercial_banners;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercialBanner;
import acme.entities.roles.Sponsor;
import acme.entities.spam_words.SpamWord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select cb from NonCommercialBanner cb where cb.id = ?1")
	NonCommercialBanner findOneById(int id);

	@Query("select cb from NonCommercialBanner cb where cb.sponsor.id = ?1")
	Collection<NonCommercialBanner> findManyNonCommercialBannersBySponsor(int sponsorId);

	@Query("select s from Sponsor s where s.id = ?1")
	Sponsor findSponsorbySponsorId(int sponsorId);

	@Query("select sw from SpamWord sw")
	Collection<SpamWord> findAllSpamWords();
}
