
package acme.entities.auditors_request;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import acme.framework.entities.DomainEntity;
import acme.framework.entities.UserAccount;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AuditorsRequest extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				firm;

	@NotBlank
	private String				responsabilityStatement;

	@NotNull
	private Boolean				accepted;

	@NotNull
	@OneToOne()
	private UserAccount			userAccount;
}
