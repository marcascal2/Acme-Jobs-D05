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
	<acme:form-checkbox code="employer.job.form.label.finalMode" path="finalMode" />

	<acme:form-textbox code="employer.job.form.label.descriptor" path="descriptor" />
	<acme:form-errors path="application" />
	<acme:form-errors path="percentageTimeForWeek" />
	<acme:form-errors path="spamWord" />
	
	<jstl:if test="${command != 'create'}">
	<jstl:if test="${not empty descriptor}">
		<acme:form-submit method="get" code="employer.job.form.button.duty" action="/employer/duty/list?descriptor_id=${descriptorId }"/>
		<jstl:if test="${not finalMode}">
			<acme:form-submit method="get" code="employer.duty.form.button.create" action="/employer/duty/create?descriptor_id=${descriptorId }"/>
		</jstl:if>
	</jstl:if>
		<acme:form-submit method="get" code="employer.job.form.button.application" action="/employer/application/list_mine?job_id=${idJob}"/>
		<acme:form-submit  method="get" code="employer.job.form.button.list-auditor-records" action="/authenticated/auditor-record/list?job_id=${idJob}"/>
	</jstl:if>
	
	<acme:form-submit test="${command == 'show'}" 
		code="employer.job.form.button.update" 
		action="/employer/job/update"/>
	<acme:form-submit test="${command == 'show'}" 
		code="employer.job.form.button.delete"
		action="/employer/job/delete" />
	<acme:form-submit method="post" test="${command == 'create'}" 
		code="employer.job.form.button.create" 
		action="/employer/job/create"/>
	<acme:form-submit test="${command == 'update'}" 
		code="employer.job.form.button.update" 
		action="/employer/job/update"/>
	<acme:form-submit test="${command == 'delete'}" 
		code="employer.job.form.button.delete" 
		action="/employer/job/delete"/>
	<acme:form-return code="employer.job.form.button.return" />
</acme:form>