
package acme.features.anonymous.investor_record;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.investor_records.InvestorRecord;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Anonymous;

@Controller
@RequestMapping("/anonymous/investor-record/")
public class AnonymousInvestorRecordController extends AbstractController<Anonymous, InvestorRecord> {

	@Autowired
	private AnonymousInvestorRecordListService		listService;

	@Autowired
	private AnonymousInvestorRecordShowService		showService;

	@Autowired
	private AnonymousTopInvestorRecordListService	topListService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.LIST_TOP, BasicCommand.LIST, this.topListService);
	}

}
