<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.audit_record.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.audit_record.form.label.status" path="status"/>
	<acme:form-textbox code="authenticated.audit_record.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textarea code="authenticated.audit_record.form.label.body" path="body"/>
	
	
	<acme:form-return code="authenticated.audit_record.form.buttom.return"/>
</acme:form>
