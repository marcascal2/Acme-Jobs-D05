
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.entities.spam_words.SpamWord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerJobRepository extends AbstractRepository {

	@Query("select j from Job j where j.id = ?1")
	Job findOneById(int id);

	@Query("select j from Job j where j.employer.id = ?1")
	Collection<Job> findManyByEmployerId(int employerId);

	@Query("select a from Application a where a.id = ?1")
	Application findApplicationById(int id);

	@Query("select e from Employer e where e.id = ?1")
	Employer findEmployerById(int accountId);

	@Query("select s from SpamWord s")
	Collection<SpamWord> findManyAllSpamWord();

	@Query("select d from Duty d where d.descriptor.id = ?1")
	Collection<Duty> findManyByDescriptorId(int descriptorId);

}
