
package acme.features.auditor.audit_record;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.audit_records.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditRecordCreateService implements AbstractCreateService<Auditor, AuditRecord> {
	//Internal state ------------------------------------------------------------

	@Autowired
	AuditorAuditRecordRepository repository;


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "status", "body");

		int jobId;
		jobId = request.getModel().getInteger("idJob");
		model.setAttribute("idJob", jobId);
	}

	@Override
	public AuditRecord instantiate(final Request<AuditRecord> request) {
		assert request != null;

		AuditRecord result;
		int jobId;
		int auditorId;
		Job job;
		UserAccount auditor;

		jobId = request.getModel().getInteger("idJob");
		job = this.repository.findJobById(jobId);

		auditorId = request.getPrincipal().getAccountId();
		auditor = this.repository.findAuditorById(auditorId);

		result = new AuditRecord();
		result.setJob(job);
		result.setAuditor(auditor);

		return result;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<AuditRecord> request, final Response<AuditRecord> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
