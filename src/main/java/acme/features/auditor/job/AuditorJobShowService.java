
package acme.features.auditor.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audit_records.AuditRecord;
import acme.entities.descriptors.Descriptor;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorJobShowService implements AbstractShowService<Auditor, Job> {

	@Autowired
	private AuditorJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		int idJob = entity.getId();
		int idAuditor = request.getPrincipal().getAccountId();
		model.setAttribute("idJob", idJob);

		AuditRecord a = this.repository.findAuditRecordByAuditorAndJobId(idAuditor, idJob);
		boolean isAudited = a == null ? false : true;

		AuditRecord d = this.repository.findAuditRecordDraftJob(idAuditor, idJob);
		boolean isDraftMode = d == null ? false : true;
		model.setAttribute("isDraftMode", isDraftMode);
		model.setAttribute("isAudited", isAudited);

		if (d != null) {
			model.setAttribute("idAuditRecord", d.getId());
		}

		request.unbind(entity, model, "reference", "title", "status", "deadline");
		request.unbind(entity, model, "salary", "moreInfo", "description", "finalMode");

		Descriptor descriptor = entity.getDescriptor();
		model.setAttribute("descriptor", descriptor.getTitle());

		model.setAttribute("descriptorId", descriptor.getId());
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
