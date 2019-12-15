
package acme.features.administrator.auditors_request;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.auditors_request.AuditorsRequest;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/auditors-request/")
public class AdministratorAuditorController extends AbstractController<Administrator, AuditorsRequest> {

	//Internal state
	@Autowired
	private AdministratorAuditorShowService		showService;

	@Autowired
	private AdministratorAuditorUpdateService	updateService;

	@Autowired
	private AdministratorAuditorListService		listService;


	//Constructors
	@PostConstruct
	public void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
