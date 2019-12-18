
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.message_threads.MessageThread;
import acme.entities.messages.Message;
import acme.entities.spam_words.SpamWord;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findOne(int id);

	@Query("select m from Message m where m.messageThread.id = ?1")
	Collection<Message> findManyByMessageThread(int messageThreadId);

	@Query("select mt from MessageThread mt where mt.id = ?1")
	MessageThread findMessageThreadById(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select sw from SpamWord sw")
	Collection<SpamWord> findAllSpamWords();
}
