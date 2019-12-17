
package acme.features.auditor.audit_record;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.audit_records.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/audit-record/")
public class AuditorAuditRecordController extends AbstractController<Auditor, AuditRecord> {

	@Autowired
	private AuditorAuditRecordListService	listService;

	@Autowired
	private AuditorAuditRecordUpdateService	updateService;

	@Autowired
	private AuditorAuditRecordShowService	showService;

	@Autowired
	private AuditorAuditRecordCreateService	createService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
