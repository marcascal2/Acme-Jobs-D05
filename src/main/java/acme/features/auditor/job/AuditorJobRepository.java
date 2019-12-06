
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditor_records.AuditorRecord;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneById(int id);

	@Query("select j from Job j")
	Collection<Job> findManyAll();

	@Query("SELECT ar FROM AuditorRecord ar WHERE ar.auditor.id = ?1")
	Collection<AuditorRecord> findAuditedJobs(int auditor_id);
}
