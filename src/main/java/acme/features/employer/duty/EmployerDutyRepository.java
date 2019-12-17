
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.descriptors.Descriptor;
import acme.entities.duties.Duty;
import acme.entities.roles.Employer;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.id = ?1")
	Duty findOneById(int dutyId);

	@Query("select d from Duty d where d.descriptor.id = ?1")
	Collection<Duty> findManyByDescriptorId(int descriptorId);

	@Query("select d from Descriptor d where d.id = ?1")
	Descriptor findDescriptorById(int descriptorId);

	@Query("select e from Employer e where e.id = ?1")
	Employer findEmployerById(int employerId);
}
