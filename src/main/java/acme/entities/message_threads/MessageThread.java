
package acme.entities.message_threads;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import acme.entities.message_threads_user_accounts.MessageThreadUserAccount;
import acme.entities.messages.Message;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MessageThread extends DomainEntity {

	//Serialisation identifier --------------------------------------------------------

	private static final long						serialVersionUID	= 1L;

	//Attributes ----------------------------------------------------------------------

	@NotBlank
	private String									title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date									moment;

	//Relationships -------------------------------------------------------------------

	@Valid
	@OneToMany(mappedBy = "messageThread")
	private Collection<MessageThreadUserAccount>	messageThreadsOfUserAccount;

	@Valid
	@OneToMany(mappedBy = "messageThread", fetch = FetchType.EAGER)
	private Collection<@Valid Message>				messages;
}
