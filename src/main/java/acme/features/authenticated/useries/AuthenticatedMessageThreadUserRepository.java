
package acme.features.authenticated.useries;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.message_threads.MessageThread;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadUserRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select mt from MessageThread mt join fetch mt.users where mt.id = ?1")
	MessageThread findOneMessageThreadById(int id);

}
