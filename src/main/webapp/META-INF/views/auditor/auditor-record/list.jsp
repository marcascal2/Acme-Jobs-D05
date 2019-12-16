<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="auditor.auditor_record.list.label.title" path="title" width="50%"/>
	<acme:list-column code="auditor.auditor_record.list.label.status" path="status" width="30%"/>
	<acme:list-column code="auditor.auditor_record.list.label.moment" path="creationMoment" width="20%"/>
</acme:list>