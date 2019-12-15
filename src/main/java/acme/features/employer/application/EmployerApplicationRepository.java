
package acme.features.employer.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerApplicationRepository extends AbstractRepository {

	@Query("select a from Application a where a.id = ?1")
	Application findOneById(int id);

	@Query("select a from Application a where a.job.employer.id = ?1")
	Collection<Application> findManyByEmployerId(int employerId);

	@Query("select a from Application a where a.job.id = ?1")
	Collection<Application> findManyByJobId(int jobId);

	@Query("select a.referenceNumber, count(a) from Application a group by a.referenceNumber")
	Collection<Application> groupedByReference();

	@Query("select a.status, count(a) from Application a group by a.status")
	Collection<Application> groupedByStatus();

	@Query("select a.moment, count(a) from Application a group by a.moment")
	Collection<Application> groupedByMoment();

}
