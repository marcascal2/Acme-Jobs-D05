
package acme.features.administrator.dashboard;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(*) from Announcement")
	Integer findManyAllAnnouncement();

	@Query("select count(*) from CompanyRecord")
	Integer findManyAllCompanyRecord();

	@Query("select count(*) from InvestorRecord")
	Integer findManyAllInvestorRecord();

	@Query("select AVG(reward.amount) from Request")
	Double findAverageRewardsRequest();

	@Query("select MAX(reward.amount) from Request")
	Double findMaximumRewardsRequest();

	@Query("select MIN(reward.amount) from Request")
	Double findMinimumRewardsRequest();

	@Query("select STDDEV(reward.amount) from Request")
	Double findStandardDeviationRewardsRequest();

	@Query("select AVG(reward.amount) from Offer")
	Double findAverageOfRewardsOffers();

	@Query("select MAX(reward.amount) from Offer")
	Double findMaximumOfRewardsOffers();

	@Query("select MIN(reward.amount) from Offer")
	Double findMinimumOfRewardsOffers();

	@Query("select STDDEV(reward.amount) from Offer")
	Double findStandardDeviationRewardsOffers();

	@Query("select c.sector, count(c) from CompanyRecord c group by c.sector")
	List<String[]> findCompaniesPerSector();

	@Query("select c.sector, count(c) from InvestorRecord c group by c.sector")
	List<String[]> findInvestorsPerSector();

	@Query("select AVG(select count(j) from Job j where j.employer.id = e.id) from Employer e")
	Double averageNumberOfJobsPerEmployer();

	@Query("select AVG(select count(a) from Application a where exists(select j from Job j where j.employer.id = e.id and a.job.id = j.id)) from Employer e")
	Double averageNumberOfApplicationsPerEmployer();

	@Query("select AVG(select count(a) from Application a where a.worker.id = w.id) from Worker w")
	Double averageNumberOfApplicationsPerWorker();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.applications.ApplicationStatus.PENDING")
	Double ratioOfPendingApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.applications.ApplicationStatus.ACCEPTED")
	Double ratioOfAcceptedApplications();

	@Query("select 1.0 * count(a) / (select count(b) from Application b) from Application a where a.status = acme.entities.applications.ApplicationStatus.REJECTED")
	Double ratioOfRejectedApplications();

	@Query("select 1.0 * count(j) / (select count(k) from Job k) from Job j where j.status = acme.entities.jobs.JobStatus.DRAFT")
	Double ratioOfDraftJobs();

	@Query("select 1.0 * count(j) / (select count(k) from Job k) from Job j where j.status = acme.entities.jobs.JobStatus.PUBLISHED")
	Double ratioOfPublishedJobs();

	@Query("select a.moment, count(*) from Application a where a.status = acme.entities.applications.ApplicationStatus.PENDING and a.moment >= ?1 group by a.moment order by a.moment")
	List<String[]> numberOfPendingApplicationsPerDay(Date limit_date);

	@Query("select a.moment, count(a) from Application a where a.status = acme.entities.applications.ApplicationStatus.ACCEPTED and a.moment >= ?1 group by a.moment order by a.moment")
	List<String[]> numberOfAcceptedApplicationsPerDay(Date limit_date);

	@Query("select a.moment, count(a) from Application a where a.status = acme.entities.applications.ApplicationStatus.REJECTED and a.moment >= ?1 group by a.moment order by a.moment")
	List<String[]> numberOfRejectedApplicationsPerDay(Date limit_date);

}
