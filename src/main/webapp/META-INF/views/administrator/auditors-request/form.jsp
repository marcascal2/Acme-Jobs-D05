<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form >
	<acme:form-textbox code="administrator.auditors-request.form.label.firm" path="firm"/>
	<acme:form-textbox code="administrator.auditors-request.form.label.responsabilityStatement" path="responsabilityStatement" />
	
	<acme:form-submit code="administrator.auditors-request.form.button.update" action="/administrator/auditors-request/update"/>
	<acme:form-return code="administrator.auditors-request.form.button.return" />
</acme:form>