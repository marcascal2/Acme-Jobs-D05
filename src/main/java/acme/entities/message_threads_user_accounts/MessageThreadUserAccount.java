
package acme.entities.message_threads_user_accounts;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import acme.entities.message_threads.MessageThread;
import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MessageThreadUserAccount extends DomainEntity {

	//Serialisation identifier --------------------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ----------------------------------------------------------------------

	//Relationships -------------------------------------------------------------------

	@NotNull
	@OneToOne()
	MessageThread				messageThread;

	@NotNull
	@OneToOne()
	UserAccount					userAccount;
}
