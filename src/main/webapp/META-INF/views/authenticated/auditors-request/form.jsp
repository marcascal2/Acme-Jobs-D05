<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form >
	<acme:form-textbox code="authenticated.auditors-request.form.label.firm" path="firm"/>
	<acme:form-textbox code="authenticated.auditors-request.form.label.responsabilityStatement" path="responsabilityStatement" />
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.auditors-request.form.button.create" action="/authenticated/auditors-request/create"/>
	<acme:form-return code="authenticated.auditors-request.form.button.return" />
</acme:form>