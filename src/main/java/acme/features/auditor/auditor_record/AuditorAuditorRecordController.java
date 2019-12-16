
package acme.features.auditor.auditor_record;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.auditor_records.AuditorRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/auditor-record")
public class AuditorAuditorRecordController extends AbstractController<Auditor, AuditorRecord> {

	@Autowired
	private AuditorAuditorRecordListPublishedService	listPublishedService;

	@Autowired
	private AuditorAuditorRecordShowService				showService;

	@Autowired
	private AuditorAuditorRecordCreateService			createService;

	@Autowired
	private AuditorAuditorRecordUpdateService			updateService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_PUBLISHED, BasicCommand.LIST, this.listPublishedService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
	}

}
