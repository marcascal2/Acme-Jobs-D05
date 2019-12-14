
package acme.features.authenticated.audit_record;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.audit_records.AuditRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.id = ?1")
	AuditRecord findOneById(int id);

	@Query("select a from AuditRecord a where a.job.id = ?1")
	Collection<AuditRecord> findManyByJobId(int jobId);

}
