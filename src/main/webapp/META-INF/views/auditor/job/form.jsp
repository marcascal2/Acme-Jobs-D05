<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="auditor.job.form.label.reference" path="reference" />
	<acme:form-textbox code="auditor.job.form.label.title" path="title" />
	<acme:form-textbox code="auditor.job.form.label.status" path="status" />
	<acme:form-moment code="auditor.job.form.label.deadline" path="deadline" />
	<acme:form-money code="auditor.job.form.label.salary" path="salary" />
	<acme:form-url code="auditor.job.form.label.moreInfo" path="moreInfo" />
	<acme:form-textarea code="auditor.job.form.label.description" path="description" />

	<acme:form-return code="auditor.job.form.button.return" />
	<acme:form-submit code="auditor.job.form.button.list-audit-records" action="/auditor/audit-record/list?job_id=${idJob}" method="get"/>	
	
</acme:form>