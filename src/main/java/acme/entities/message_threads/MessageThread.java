
package acme.entities.message_threads;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import acme.entities.messages.Message;
import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MessageThread extends DomainEntity {

	//Serialisation identifier --------------------------------------------------------

	private static final long			serialVersionUID	= 1L;

	//Attributes ----------------------------------------------------------------------

	@NotBlank
	private String						title;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date						moment;

	//Relationships -------------------------------------------------------------------

	@Valid
	@ManyToMany()
	private Collection<UserAccount>		users;

	@Valid
	@OneToMany(mappedBy = "messageThread", fetch = FetchType.EAGER)
	private Collection<@Valid Message>	messages;
}
