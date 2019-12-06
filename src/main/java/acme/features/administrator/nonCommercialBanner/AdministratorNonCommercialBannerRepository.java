
package acme.features.administrator.nonCommercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonCommercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorNonCommercialBannerRepository extends AbstractRepository {

	@Query("select cb from NonCommercialBanner cb where cb.id = ?1")
	NonCommercialBanner findOneNonCommercialBannerById(int id);

	@Query("select cb from NonCommercialBanner cb")
	Collection<NonCommercialBanner> findManyNonCommercialBanners();
}
