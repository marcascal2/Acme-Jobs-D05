
package acme.features.administrator.spam_word;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.spam_words.SpamWord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorSpamWordRepository extends AbstractRepository {

	@Query("select s from SpamWord s where s.id = ?1")
	SpamWord findOneById(int id);

	@Query("select s from SpamWord s")
	Collection<SpamWord> findManyAll();
}
