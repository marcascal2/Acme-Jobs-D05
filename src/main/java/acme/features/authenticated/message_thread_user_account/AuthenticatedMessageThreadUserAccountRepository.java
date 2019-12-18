
package acme.features.authenticated.message_thread_user_account;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.message_threads.MessageThread;
import acme.entities.message_threads_user_accounts.MessageThreadUserAccount;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadUserAccountRepository extends AbstractRepository {

	@Query("select mtua from MessageThreadUserAccount mtua")
	Collection<MessageThreadUserAccount> findManyAll();

	@Query("select mtua from MessageThreadUserAccount mtua where mtua.messageThread.id = ?1")
	Collection<MessageThreadUserAccount> findManyByMessageThread(int messageThreadId);

	@Query("select ua from UserAccount ua")
	Collection<UserAccount> findManyUserAccounts(int messageThreadId);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select mt from MessageThread mt join mt.messageThreadsOfUserAccount where mt.id = ?1")
	MessageThread findOneMessageThreadById(int id);

	@Query("select mtua from MessageThreadUserAccount mtua where mtua.id = ?1")
	MessageThreadUserAccount findOneById(int id);

}
