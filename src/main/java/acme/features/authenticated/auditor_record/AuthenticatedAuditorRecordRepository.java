
package acme.features.authenticated.auditor_record;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditor_records.AuditorRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditorRecordRepository extends AbstractRepository {

	@Query("select a from AuditorRecord a where a.id = ?1")
	AuditorRecord findOneById(int id);

	@Query("select a from AuditorRecord a where a.job.id = ?1")
	Collection<AuditorRecord> findManyByJobId(int jobId);

}
