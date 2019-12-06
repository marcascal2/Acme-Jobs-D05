<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form>
	<acme:form-textbox code="administrator.investor-record.form.label.investorName" path="investorName"/>
	<acme:form-textbox code="administrator.investor-record.form.label.sector" path="sector"/>
	<acme:form-textbox code="administrator.investor-record.form.label.statement" path="statement"/>
	<acme:form-textbox code="administrator.investor-record.form.label.stars" path="stars" placeholder="5.0"/>
	
	<acme:form-submit test="${command == 'show'}"
		code="administrator.investor-record.form.buttom.update"
		action="/administrator/investor-record/update"/>
	<acme:form-submit test="${command == 'show'}"
		code="administrator.investor-record.form.buttom.delete" 
		action="/administrator/investor-record/delete"/>
	<acme:form-submit test="${command == 'create'}"
		code="administrator.investor-record.form.buttom.create"
		action="/administrator/investor-record/create"/>
	<acme:form-submit test="${command == 'update'}"
		code="administrator.investor-record.form.buttom.update"
		action="/administrator/investor-record/update"/>
	<acme:form-submit test="${command == 'delete'}"
		code="administrator.investor-record.form.buttom.delete"
		action="/administrator/investor-record/delete"/>
	<acme:form-return
		code="administrator.investor-record.form.buttom.return"/>
</acme:form>