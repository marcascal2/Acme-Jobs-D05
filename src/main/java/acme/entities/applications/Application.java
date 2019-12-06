
package acme.entities.applications;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Application extends DomainEntity {

	//Serialisation identifier
	private static final long	serialVersionUID	= 1L;

	//Atributes

	@NotBlank
	@Column(unique = true)
	@Length(min = 5, max = 15)
	@Pattern(regexp = "[0-Z]{1,4}-[0-Z]{1,4}:[0-Z]{1,4}")
	private String				referenceNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@NotNull
	private ApplicationStatus	status;

	private String				statement;

	@NotBlank
	private String				skills;

	@NotBlank
	private String				qualifications;

	//Delivered attributes

	//Relationships

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Worker				worker;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job					job;
}
