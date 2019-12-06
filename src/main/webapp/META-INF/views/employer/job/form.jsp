<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="false">
	<acme:form-textbox code="employer.job.form.label.reference" path="reference" />
	<acme:form-textbox code="employer.job.form.label.title" path="title" />
	<acme:form-textbox code="employer.job.form.label.status" path="status" />
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline" />
	<acme:form-money code="employer.job.form.label.salary" path="salary" />
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo" />
	<acme:form-textarea code="employer.job.form.label.description" path="description" />
	<acme:form-textarea readonly="true" code="employer.job.form.label.descriptor" path="descriptor" />
	
	<acme:form-submit method="get" code="employer.job.form.button.duty" action="/employer/duty/list?id=${descriptorId}"/>
	<acme:form-return code="employer.job.form.button.return" />
	<acme:form-submit code="employer.job.form.button.list-auditor-records" action="/authenticated/auditor-record/list?job_id=${idJob}" method="get"/>	
	
</acme:form>