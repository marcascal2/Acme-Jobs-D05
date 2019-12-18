<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox readonly="true" code="employer.application.form.label.job" path="job" />
	
	<acme:form-textbox readonly="true" code="employer.application.form.label.reference" path="referenceNumber" />
	<acme:form-moment readonly="true" code="employer.application.form.label.moment" path="moment" />

	<acme:form-textbox readonly="false" code="employer.application.form.label.status" path="status" placeholder="PENDING, ACCEPTED or REJECTED"/>
	<acme:form-textarea readonly="false" code="employer.application.form.label.justification" path="justification" />
	
	<acme:form-textbox readonly="true" code="employer.application.form.label.statement" path="statement" />
	<acme:form-textbox readonly="true" code="employer.application.form.label.skills" path="skills" />
	<acme:form-textbox readonly="true" code="employer.application.form.label.qualifications" path="qualifications" />
	
	<acme:form-submit test="${command == 'show'}" 
		code="employer.application.form.button.update" 
		action="/employer/application/update"/>
	<acme:form-submit test="${command == 'update'}" 
		code="employer.application.form.button.update" 
		action="/employer/application/update"/>
	<acme:form-return code="employer.application.form.button.return" />
	
	
	
	
</acme:form>