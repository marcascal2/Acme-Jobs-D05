
package acme.entities.company_records;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CompanyRecord extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				name;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				ceoName;

	@NotBlank
	private String				description;

	@NotBlank
	@URL
	private String				webSite;

	@Pattern(regexp = "([+][^0][\\d]{0,2})?[ ]?([(][\\d]{0,4}[)])?[ ]?([\\d]{6,10})$")
	private String				phone;

	@NotBlank
	@Email
	private String				email;

	private Boolean				incorporated;

	@Range(min = 0, max = 5)
	private Double				stars;


	@Transient
	public String getIncor() {
		StringBuilder res = new StringBuilder();
		res.append(this.name);
		if (this.getIncorporated() == true) {
			res.append(", Inc");
		} else {
			res.append(", LLC");
		}
		return res.toString();
	}

}
