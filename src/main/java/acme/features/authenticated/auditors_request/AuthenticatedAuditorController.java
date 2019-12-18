
package acme.features.authenticated.auditors_request;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.auditors_request.AuditorsRequest;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/auditors-request/")
public class AuthenticatedAuditorController extends AbstractController<Authenticated, AuditorsRequest> {

	//Internal state
	@Autowired
	private AuthenticatedAuditorCreateService createService;


	//Constructors
	@PostConstruct
	public void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
