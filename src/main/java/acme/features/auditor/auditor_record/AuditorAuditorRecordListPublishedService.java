
package acme.features.auditor.auditor_record;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditor_records.AuditorRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class AuditorAuditorRecordListPublishedService implements AbstractListService<Auditor, AuditorRecord> {

	@Autowired
	AuditorAuditorRecordRepository repository;


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

		request.unbind(entity, model, "title", "status", "creationMoment");
	}

	@Override
	public Collection<AuditorRecord> findMany(final Request<AuditorRecord> request) {
		assert request != null;

		Collection<AuditorRecord> allAuditorRecord;
		Collection<AuditorRecord> result = new ArrayList<>();
		int jobId = request.getModel().getInteger("job_id");

		allAuditorRecord = this.repository.findManyByJobId(jobId);
		for (AuditorRecord a : allAuditorRecord) {
			if (a.getStatus().startsWith("PU")) {
				result.add(a);
			}
		}

		return result;
	}

}
