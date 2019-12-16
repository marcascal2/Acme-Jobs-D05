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

	<acme:form-submit method="get" code="authenticated.job.form.button.duty" action="/authenticated/duty/list?id=${descriptorId}"/>
	<acme:form-return code="auditor.job.form.button.return" />
	<acme:form-submit code="auditor.job.form.button.list-audit-records" action="/auditor/audit-record/list?job_id=${idJob}" method="get"/>
	<acme:form-submit test="${!isAudited}" method="get" code="auditor.job.form.button.write-audit-record" action="/auditor/audit-record/create?idJob=${idJob}"/>
	<acme:form-submit test="${isDraftMode}" method="get" code="auditor.job.form.button.change-audit-record" action="/auditor/audit-record/show?id=${idAuditRecord}"/>
</acme:form>