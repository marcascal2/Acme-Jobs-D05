
package acme.features.authenticated.message_thread_user_account;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.message_threads_user_accounts.MessageThreadUserAccount;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/message-thread-user-account/")
public class AuthenticatedMessageThreadUserAccountController extends AbstractController<Authenticated, MessageThreadUserAccount> {

	//Internal state
	@Autowired
	private AuthenticatedMessageThreadUserAccountListService	listService;

	@Autowired
	private AuthenticatedMessageThreadUserAccountShowService	showService;

	@Autowired
	private AuthenticatedMessageThreadUserAccountCreateService	createService;

	@Autowired
	private AuthenticatedMessageThreadUserAccountDeleteService	deleteService;


	//Constructors
	@PostConstruct
	public void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}

}
