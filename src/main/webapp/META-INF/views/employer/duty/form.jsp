<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command != 'create' }">
	<acme:form-textbox readonly='true' code="employer.duty.list.label.job" path="job" />
	</jstl:if>
	<acme:form-textbox code="employer.duty.form.label.title" path="title"/>
	<acme:form-textbox code="employer.duty.form.label.description" path="description" />
	<acme:form-double code="employer.duty.form.label.percentage" path="percentageTimeForWeek" />

	<acme:form-submit method="post" test="${command == 'create'}" 
		code="employer.duty.form.button.create" 
		action="/employer/duty/create?descriptor_id=${descriptor_id}"/>
		
	<acme:form-submit test="${command == 'show'}" 
		code="employer.duty.form.button.update" 
		action="/employer/duty/update"/>
	
	<acme:form-submit test="${command == 'update'}" 
		code="employer.duty.form.button.update" 
		action="/employer/duty/update"/>
	
	<acme:form-return code="employer.duty.form.button.return" />
</acme:form>