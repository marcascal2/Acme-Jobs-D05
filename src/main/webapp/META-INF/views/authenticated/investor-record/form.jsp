<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.investor-record.form.label.investorName" path="investorName"/>
	<acme:form-textbox code="authenticated.investor-record.form.label.sector" path="sector"/>
	<acme:form-textarea code="authenticated.investor-record.form.label.statement" path="statement"/>
	<acme:form-url code="authenticated.investor-record.form.label.stars" path="stars"/>
	
	
	
	<acme:form-return code="authenticated.investor-record.form.buttom.return"/>
</acme:form>