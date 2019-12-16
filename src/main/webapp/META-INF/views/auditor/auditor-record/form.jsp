<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="auditor.auditor_record.form.label.title" path="title"/>
	<acme:form-textbox code="auditor.auditor_record.form.label.status" path="status"/>
	<acme:form-textbox code="auditor.auditor_record.form.label.creationMoment" path="creationMoment"/>
	<acme:form-textarea code="auditor.auditor_record.form.label.body" path="body"/>
	<acme:form-textarea code="auditor.auditor_record.form.label.job" path="job"/>
	
	
	<acme:form-submit test="${command == 'show'}"
		code="auditor.auditor_record.form.buttom.update"
		action="/auditor/auditor-record/update"/>
	
	<acme:form-submit test="${command == 'create'}"
		code="auditor.auditor_record.form.buttom.create"
		action="/auditor/auditor-record/create"/>
		
	<acme:form-submit test="${command == 'update'}"
		code="auditor.auditor_record.form.buttom.update"
		action="/auditor/auditor-record/update"/>
		
	<acme:form-submit code="auditor.auditor-record.form.buttom.list-auditor-records" action="/authenticated/auditor-record/list?job_id=${idJob}" method="get"/>
		
	<acme:form-return
		code="auditor.auditor_record.form.buttom.return"/>


</acme:form>
