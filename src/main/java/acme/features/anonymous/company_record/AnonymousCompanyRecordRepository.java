
package acme.features.anonymous.company_record;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.company_records.CompanyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousCompanyRecordRepository extends AbstractRepository {

	@Query("select a from CompanyRecord a where a.id = ?1")
	CompanyRecord findOneById(int id);

	@Query("select a from CompanyRecord a")
	Collection<CompanyRecord> findManyAll();

	@Query("select a from CompanyRecord a where a.stars like 5")
	Collection<CompanyRecord> findTopRecords();

}
