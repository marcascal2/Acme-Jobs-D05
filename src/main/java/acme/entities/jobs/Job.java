
package acme.entities.jobs;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.applications.Application;
import acme.entities.audit_records.AuditRecord;
import acme.entities.descriptors.Descriptor;
import acme.entities.roles.Employer;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Job extends DomainEntity {

	//Serialisation identifier --------------------------------------------------------

	private static final long		serialVersionUID	= 1L;

	//Attributes ----------------------------------------------------------------------

	@Column(unique = true)
	@NotBlank
	@Length(min = 5, max = 10)
	@Pattern(regexp = "[\\w]{4}-[\\w]{4}")
	private String					reference;

	@NotNull
	private JobStatus				status;

	@NotBlank
	private String					title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date					deadline;

	@NotNull
	@Valid
	private Money					salary;

	@NotBlank
	private String					description;

	@URL
	private String					moreInfo;

	//Relationships -------------------------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Employer				employer;

	@Valid
	@OneToOne(mappedBy = "job")
	private Descriptor				descriptor;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "job")
	private Collection<Application>	application;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "job")
	private Collection<AuditRecord>	auditRecords;
}
