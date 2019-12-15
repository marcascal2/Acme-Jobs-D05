<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
<acme:form>

<acme:form-submit method="get" code="employer.application.form.button.groupByReference"
	action="/employer/application/list_mine?job_id=${job_id }&group_by=reference"/>
	
<acme:form-submit method="get" code="employer.application.form.button.groupByStatus"
	action="/employer/application/list_mine?job_id=${job_id}&group_by=status"/>
	
<acme:form-submit method="get" code="employer.application.form.button.groupByMoment"
	action="/employer/application/list_mine?job_id=${job_id}&group_by=moment"/>
</acme:form>
<acme:list>
	<acme:list-column code="employer.application.list.label.reference" path="referenceNumber" width="10%"/>
	<acme:list-column code="employer.application.list.label.moment" path="moment" width="10%"/>
	<acme:list-column code="employer.application.list.label.status" path="status" width="10%"/>
	<acme:list-column code="employer.application.list.label.skills" path="skills" width="60%"/>
	<acme:list-column code="employer.application.list.label.count" path="count" width="10%"/>
</acme:list>