
package acme.features.authenticated.auditor_record;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.auditor_records.AuditorRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/auditor-record/")
public class AuthenticatedAuditorRecordController extends AbstractController<Authenticated, AuditorRecord> {

	@Autowired
	private AuthenticatedAuditorRecordListService	listService;

	@Autowired
	private AuthenticatedAuditorRecordShowService	showService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
