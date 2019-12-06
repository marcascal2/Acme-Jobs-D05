<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:form-textbox code="authenticated.offer.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="authenticated.offer.form.label.title" path="title"/>
	<acme:form-textbox code="authenticated.offer.form.label.moment" path="moment"/>
	<acme:form-textbox code="authenticated.offer.form.label.deadline" path="deadline"/>
	<acme:form-textbox code="authenticated.offer.form.label.description" path="description"/>
	<acme:form-money code="authenticated.offer.form.label.reward" path="reward"/>
	

	<acme:form-return code="authenticated.provider.form.button.return"/>
</acme:form>