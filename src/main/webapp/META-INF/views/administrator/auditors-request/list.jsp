<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.auditors-request.list.label.username" path="username" width="20%"/>
	<acme:list-column code="administrator.auditors-request.list.label.firm" path="firm" width="40%"/>
	<acme:list-column code="administrator.auditors-request.list.label.responsabilityStatement" path="responsabilityStatement" width="40%"/>
</acme:list>