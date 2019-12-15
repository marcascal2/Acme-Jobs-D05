<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="auditor.audit_record.form.label.creationMoment" path="creationMoment" readonly="true"/>
	</jstl:if>

	<acme:form-textbox code="auditor.audit_record.form.label.status" path="status"/>
	<acme:form-textbox code="auditor.audit_record.form.label.title" path="title"/>
	<acme:form-textarea code="auditor.audit_record.form.label.body" path="body"/>
	
	<acme:form-hidden path="idJob"/>
	
	<acme:form-submit test="${ command == 'create'}" code="auditor.audit_record.form.button.create" action="/auditor/audit-record/create"/>
	<acme:form-return code="auditor.audit_record.form.buttom.return"/>
</acme:form>
