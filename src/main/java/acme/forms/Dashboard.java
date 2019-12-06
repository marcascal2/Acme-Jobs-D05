
package acme.forms;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	//Serialisation identifier -----------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes -------------------------------------------------------

	Integer						totalNumberOfAnnouncement;
	Integer						totalNumberOfCompanyRecords;
	Integer						totalNumberOfInvestorRecords;
	Double						minimumOfRewardsRequests;
	Double						maximumOfRewardsRequests;
	Double						averageOfRewardsRequests;
	Double						standardDeviationOfRewardsRequests;
	Double						minimumOfRewardsOffers;
	Double						maximumOfRewardsOffers;
	Double						averageOfRewardsOffers;
	Double						standardDeviationOfRewardsOffers;
	List<String[]>				companiesPerSector;
	List<String[]>				investorsPerSector;

	Double						averageNumberOfJobsPerEmployer;
	Double						averageNumberOfApplicationsPerEmployer;
	Double						averageNumberOfApplicationsPerWorker;
	Double						ratioOfDraftJobs;
	Double						ratioOfPublishedJobs;
	Double						ratioOfPendingApplications;
	Double						ratioOfAcceptedApplications;
	Double						ratioOfRejectedApplications;
}
