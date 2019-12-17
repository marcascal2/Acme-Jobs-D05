
package acme.features.auditor.audit_record;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.audit_records.AuditRecord;
import acme.entities.jobs.Job;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorAuditRecordRepository extends AbstractRepository {

	@Query("select a from AuditRecord a where a.id = ?1")
	AuditRecord findOneById(int id);

	@Query("select a from AuditRecord a where a.job.id = ?1 and a.status = acme.entities.audit_records.AuditRecordStatus.PUBLISHED")
	Collection<AuditRecord> findManyByJobId(int jobId);

	@Query("select j from Job j where j.id = ?1")
	Job findJobById(int jobId);

	@Query("select ar from AuditRecord ar where ar.auditor.id = ?1 and ar.job.id = ?2")
	AuditRecord findAuditRecordByAuditorAndJobId(int auditorId, int jobId);

	@Query("select a from UserAccount a where a.id = ?1")
	UserAccount findAuditorById(int auditorId);

}
