
package acme.features.authenticated.auditors_request;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.auditors_request.AuditorsRequest;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAuditorRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select ar from AuditorsRequest ar where ar.userAccount.id = ?1")
	AuditorsRequest userAlreadyRequested(int id);

}
