<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="worker.job.form.label.reference" path="reference" />
	<acme:form-textbox code="worker.job.form.label.title" path="title" />
	<acme:form-textbox code="worker.job.form.label.status" path="status" />
	<acme:form-moment code="worker.job.form.label.deadline" path="deadline" />
	<acme:form-money code="worker.job.form.label.salary" path="salary" />
	<acme:form-url code="worker.job.form.label.moreInfo" path="moreInfo" />
	<acme:form-textarea code="worker.job.form.label.description" path="description" />
	<acme:form-textarea readonly="true" code="worker.job.form.label.descriptor" path="descriptor" />

	<acme:form-submit method="get" code="worker.job.form.button.duty" action="/authenticated/duty/list?id=${descriptorId}"/>	
	<acme:form-submit code="worker.job.form.button.list-auditor-records" action="/authenticated/auditor-record/list?job_id=${idJob}" method="get"/>
	
	<acme:form-return code="worker.job.form.button.return" />
	<acme:form-submit method="get" code="worker.job.form.button.apply" action="/worker/application/create?idJob=${idJob}"/>
</acme:form>