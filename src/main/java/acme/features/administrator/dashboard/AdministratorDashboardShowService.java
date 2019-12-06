
package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	//Internal state
	@Autowired
	private AdministratorDashboardRepository repository;


	//AbstractShowService<Administrator, Dashboard> interface
	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "totalNumberOfAnnouncement", "totalNumberOfCompanyRecords", "totalNumberOfInvestorRecords", "minimumOfRewardsRequests", "maximumOfRewardsRequests", "averageOfRewardsRequests", "standardDeviationOfRewardsRequests",
			"minimumOfRewardsOffers", "maximumOfRewardsOffers", "averageOfRewardsOffers", "standardDeviationOfRewardsOffers", "companiesPerSector", "investorsPerSector");
		request.unbind(entity, model, "averageNumberOfJobsPerEmployer", "averageNumberOfApplicationsPerEmployer", "averageNumberOfApplicationsPerWorker", "ratioOfDraftJobs", "ratioOfPublishedJobs", "ratioOfPendingApplications",
			"ratioOfAcceptedApplications", "ratioOfRejectedApplications");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		Dashboard result = new Dashboard();

		result.setTotalNumberOfAnnouncement(this.repository.findManyAllAnnouncement());
		result.setTotalNumberOfCompanyRecords(this.repository.findManyAllCompanyRecord());
		result.setTotalNumberOfInvestorRecords(this.repository.findManyAllInvestorRecord());
		result.setMaximumOfRewardsRequests(this.repository.findMaximumRewardsRequest());
		result.setMinimumOfRewardsRequests(this.repository.findMinimumRewardsRequest());
		result.setAverageOfRewardsRequests(this.repository.findAverageRewardsRequest());
		result.setStandardDeviationOfRewardsRequests(this.repository.findStandardDeviationRewardsRequest());
		result.setAverageOfRewardsOffers(this.repository.findAverageOfRewardsOffers());
		result.setMaximumOfRewardsOffers(this.repository.findMaximumOfRewardsOffers());
		result.setMinimumOfRewardsOffers(this.repository.findMinimumOfRewardsOffers());
		result.setStandardDeviationOfRewardsOffers(this.repository.findStandardDeviationRewardsOffers());
		result.setCompaniesPerSector(this.repository.findCompaniesPerSector());
		result.setInvestorsPerSector(this.repository.findInvestorsPerSector());

		Double averageNumberOfJobsPerEmployer = this.repository.averageNumberOfJobsPerEmployer();
		Double averageNumberOfApplicationsPerEmployer = this.repository.averageNumberOfApplicationsPerEmployer();
		Double averageNumberOfApplicationsPerWorker = this.repository.averageNumberOfApplicationsPerWorker();
		Double ratioOfDraftJobs = this.repository.ratioOfDraftJobs();
		Double ratioOfPublishedJobs = this.repository.ratioOfPublishedJobs();
		Double ratioOfPendingApplications = this.repository.ratioOfPendingApplications();
		Double ratioOfAcceptedApplications = this.repository.ratioOfAcceptedApplications();
		Double ratioOfRejectedApplications = this.repository.ratioOfRejectedApplications();

		result.setAverageNumberOfApplicationsPerEmployer(averageNumberOfApplicationsPerEmployer);
		result.setAverageNumberOfApplicationsPerWorker(averageNumberOfApplicationsPerWorker);
		result.setAverageNumberOfJobsPerEmployer(averageNumberOfJobsPerEmployer);
		result.setRatioOfAcceptedApplications(ratioOfAcceptedApplications);
		result.setRatioOfDraftJobs(ratioOfDraftJobs);
		result.setRatioOfPendingApplications(ratioOfPendingApplications);
		result.setRatioOfPublishedJobs(ratioOfPublishedJobs);
		result.setRatioOfRejectedApplications(ratioOfRejectedApplications);

		return result;
	}
}
