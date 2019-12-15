
package acme.entities.descriptors;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Descriptor extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	//Attributes ----------------------------------------------
	@NotBlank
	private String				title;

	//Relationships -------------------------------------------------------------------
	@OneToOne(optional = true)
	private Job					job;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "descriptor")
	private Collection<Duty>	duty;
}
