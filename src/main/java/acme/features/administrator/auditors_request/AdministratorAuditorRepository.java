
package acme.features.administrator.auditors_request;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditors_request.AuditorsRequest;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAuditorRepository extends AbstractRepository {

	@Query("select ar from AuditorsRequest ar where ar.id = ?1")
	AuditorsRequest findOneById(int id);

	@Query("select ar from AuditorsRequest ar where ar.accepted = false")
	Collection<AuditorsRequest> findMany();

}
