
package acme.features.sponsor.credit_card;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.credit_cards.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/credit-card/")
public class SponsorCreditCardController extends AbstractController<Sponsor, CreditCard> {

	//Internal state
	@Autowired
	private SponsorCreditCardCreateService	createService;

	@Autowired
	private SponsorCreditCardUpdateService	updateService;


	//Constructors
	@PostConstruct
	public void initialise() {
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}
}
