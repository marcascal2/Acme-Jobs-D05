
package acme.features.authenticated.auditor_record;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditor_records.AuditorRecord;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedAuditorRecordListService implements AbstractListService<Authenticated, AuditorRecord> {

	@Autowired
	AuthenticatedAuditorRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditorRecord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<AuditorRecord> request, final AuditorRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "creationMoment");
	}

	@Override
	public Collection<AuditorRecord> findMany(final Request<AuditorRecord> request) {
		assert request != null;

		Collection<AuditorRecord> result;
		int jobId = request.getModel().getInteger("job_id");

		result = this.repository.findManyByJobId(jobId);

		return result;
	}

}
