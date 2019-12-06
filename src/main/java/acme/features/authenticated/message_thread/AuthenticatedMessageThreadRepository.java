
package acme.features.authenticated.message_thread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.message_threads.MessageThread;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select mt from MessageThread mt join mt.messages where mt.id = ?1")
	MessageThread findOneById(int id);

	@Query("select mt from MessageThread mt join mt.users u where u.id = ?1")
	Collection<MessageThread> findManyByUserId(int userId);
}
