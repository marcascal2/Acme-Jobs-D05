<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="administrator.company_record.form.label.name" path="name"/>
	<acme:form-textbox code="administrator.company_record.form.label.sector" path="sector"/>
	<acme:form-textbox code="administrator.company_record.form.label.ceoName" path="ceoName"/>
	<acme:form-textbox code="administrator.company_record.form.label.description" path="description"/>
	<acme:form-url code="administrator.company_record.form.label.webSite" path="webSite"/>
	<acme:form-textbox code="administrator.company_record.form.label.phone" path="phone" placeholder="+999 (9999) 999999"/>
	<acme:form-textbox code="administrator.company_record.form.label.email" path="email"/>
	<acme:form-checkbox code="administrator.company_record.form.label.incorporated" path="incorporated"/>
	<acme:form-double code="administrator.company_record.form.label.stars" path="stars" placeholder="5.0"/>
	
	<acme:form-submit test="${command == 'show'}"
		code="administrator.company_record.form.button.update"
		action="/administrator/company-record/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="administrator.company_record.form.button.delete" 
		action="/administrator/company-record/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="administrator.company_record.form.button.create"
		action="/administrator/company-record/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="administrator.company_record.form.button.update"
		action="/administrator/company-record/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="administrator.company_record.form.button.delete"
		action="/administrator/company-record/delete"/>
	<acme:form-return
		code="administrator.company_record.form.button.return"/>
</acme:form>
