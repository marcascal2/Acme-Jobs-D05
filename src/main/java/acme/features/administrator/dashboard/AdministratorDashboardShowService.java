
package acme.features.administrator.dashboard;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

		Date limit_date = Date.valueOf(LocalDate.now().minusDays(28));
		List<LocalDate> dates = this.getDates(limit_date);
		List<String> labels = dates.stream().map(d -> d.toString()).collect(Collectors.toList());

		model.setAttribute("labels", labels);

		request.unbind(entity, model, "totalNumberOfAnnouncement", "totalNumberOfCompanyRecords", "totalNumberOfInvestorRecords", "minimumOfRewardsRequests", "maximumOfRewardsRequests", "averageOfRewardsRequests", "standardDeviationOfRewardsRequests",
			"minimumOfRewardsOffers", "maximumOfRewardsOffers", "averageOfRewardsOffers", "standardDeviationOfRewardsOffers", "companiesPerSector", "investorsPerSector");
		request.unbind(entity, model, "averageNumberOfJobsPerEmployer", "averageNumberOfApplicationsPerEmployer", "averageNumberOfApplicationsPerWorker", "ratioOfDraftJobs", "ratioOfPublishedJobs", "ratioOfPendingApplications",
			"ratioOfAcceptedApplications", "ratioOfRejectedApplications");
		request.unbind(entity, model, "numberOfPendingApplicationsPerDay", "numberOfAcceptedApplicationsPerDay", "numberOfRejectedApplicationsPerDay");
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

		//Tablas de aplicaciones con su estado por día
		Date limit_date = Date.valueOf(LocalDate.now().minusDays(28));

		List<String[]> pend = this.repository.numberOfPendingApplicationsPerDay(limit_date);
		List<String[]> accep = this.repository.numberOfAcceptedApplicationsPerDay(limit_date);
		List<String[]> rejec = this.repository.numberOfRejectedApplicationsPerDay(limit_date);

		List<LocalDate> dates = this.getDates(limit_date);

		result.setNumberOfPendingApplicationsPerDay(this.getDataByDate(pend, dates));
		result.setNumberOfAcceptedApplicationsPerDay(this.getDataByDate(accep, dates));
		result.setNumberOfRejectedApplicationsPerDay(this.getDataByDate(rejec, dates));

		return result;
	}

	private List<Integer> getDataByDate(final List<String[]> list, final List<LocalDate> dates) {
		Map<LocalDate, Integer> lp = new HashMap<LocalDate, Integer>();
		List<String[]> l = list;
		for (LocalDate ld1 : dates) {
			lp.put(ld1, 0);
		}

		for (int i = 0; i < list.size(); i++) {
			LocalDate ld2 = LocalDate.parse(list.get(i)[0].substring(0, 10));
			if (lp.containsKey(ld2)) {
				lp.replace(ld2, 0, Integer.parseInt(l.get(i)[1]));
			}
		}

		TreeMap<LocalDate, Integer> lpo = new TreeMap<>(lp);
		List<Integer> result = lpo.values().stream().collect(Collectors.toList());
		return result;
	}

	private List<LocalDate> getDates(final Date limit_date) {
		Date now = Date.valueOf(LocalDate.now());
		long numberOfDaysBetween = (now.getTime() - limit_date.getTime()) / (1000 * 60 * 60 * 24);
		List<LocalDate> dates = IntStream.iterate(0, i -> i + 1).limit(numberOfDaysBetween).mapToObj(i -> limit_date.toLocalDate().plusDays(i)).collect(Collectors.toList());
		return dates;
	}

}
