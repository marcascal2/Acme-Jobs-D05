<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.job.form.label.reference" path="reference" />
	<acme:form-textbox code="authenticated.job.form.label.title" path="title" />
	<acme:form-textbox code="authenticated.job.form.label.status" path="status" />
	<acme:form-moment code="authenticated.job.form.label.deadline" path="deadline" />
	<acme:form-money code="authenticated.job.form.label.salary" path="salary" />
	<acme:form-url code="authenticated.job.form.label.moreInfo" path="moreInfo" />
	<acme:form-textarea code="authenticated.job.form.label.description" path="description" />
	<acme:form-textarea readonly="true" code="authenticated.job.form.label.descriptor" path="descriptor" />

		<acme:form-submit method="get" code="authenticated.job.form.button.duty" action="/authenticated/duty/list?id=${descriptorId}"/>
	<acme:form-return code="authenticated.job.form.button.return" />
	
	<acme:form-submit code="authenticated.job.form.button.list-audit-records" action="/authenticated/audit-record/list?job_id=${idJob}" method="get"/>	
</acme:form>