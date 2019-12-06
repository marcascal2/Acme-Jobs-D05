<%@ page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.company_record.form.label.name" path="incor"/>
	<acme:form-textbox code="anonymous.company_record.form.label.sector" path="sector"/>
	<acme:form-textbox code="anonymous.company_record.form.label.ceoName" path="ceoName"/>
	<acme:form-textarea code="anonymous.company_record.form.label.description" path="description"/>
	<acme:form-url code="anonymous.company_record.form.label.webSite" path="webSite"/>
	<acme:form-textbox code="anonymous.company_record.form.label.phone" path="phone"/>
	<acme:form-textbox code="anonymous.company_record.form.label.email" path="email"/>
	<acme:form-checkbox code="anonymous.company_record.form.label.incorporated" path="incorporated"/>
	<acme:form-double code="anonymous.company_record.form.label.stars" path="stars"/>
	<acme:form-return code="anonymous.company_record.form.buttom.return"/>
</acme:form>
