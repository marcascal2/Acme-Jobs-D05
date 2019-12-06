
package acme.entities.descriptors;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Descriptor extends DomainEntity {

	private static final long		serialVersionUID	= 1L;

	//Attributes ----------------------------------------------
	@NotBlank
	private String					title;

	//Relationships -------------------------------------------------------------------
	@NotNull
	@Valid
	@OneToOne(optional = false)
	@JoinColumn(name = "job_id")
	private Job						job;

	@NotEmpty
	@OneToMany(mappedBy = "descriptor", fetch = FetchType.EAGER)
	private Collection<@Valid Duty>	duty;
}
