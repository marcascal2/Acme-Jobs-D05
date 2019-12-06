<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.company_record.list.label.name" path="incor" width="20%"/>
	<acme:list-column code="administrator.company_record.list.label.sector" path="sector" width="20%"/>
	<acme:list-column code="administrator.company_record.list.label.phone" path="phone" width="20%"/>
	<acme:list-column code="administrator.company_record.list.label.ceoName" path="ceoName" width="20%"/>
</acme:list>