
package acme.features.auditor.job;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditor_records.AuditorRecord;
import acme.entities.jobs.Job;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class AuditorJobListNonAuditedService implements AbstractListService<Authenticated, Job> {

	@Autowired
	AuditorJobRepository repository;


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

		request.unbind(entity, model, "reference", "title", "status", "deadline");
	}

	@Override
	public Collection<Job> findMany(final Request<Job> request) {
		assert request != null;

		Collection<AuditorRecord> auditorRecords;
		Collection<Job> allJobs;
		Collection<Job> auditedJobs;
		Collection<Job> result;
		Principal principal;
		principal = request.getPrincipal();

		auditorRecords = this.repository.findAuditedJobs(principal.getAccountId());
		auditedJobs = auditorRecords.stream().map(x -> x.getJob()).collect(Collectors.toList());

		allJobs = this.repository.findManyAll();

		result = allJobs.stream().filter(x -> !auditedJobs.contains(x)).collect(Collectors.toList());

		return result;
	}

}
