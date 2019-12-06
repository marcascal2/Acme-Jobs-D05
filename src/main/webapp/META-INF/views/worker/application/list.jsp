<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="worker.application.list.label.reference" path="referenceNumber" width="10%"/>
	<acme:list-column code="worker.application.list.label.moment" path="moment" width="20%" />
	<acme:list-column code="worker.application.list.label.status" path="status" width="20%" />
	<acme:list-column code="worker.application.list.label.skills" path="skills" width="40%" />
</acme:list>
