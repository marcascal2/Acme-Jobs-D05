<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="consumer.offer.form.label.ticker" path="ticker"/>
	<acme:form-textbox code="consumer.offer.form.label.title" path="title"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-moment code="consumer.offer.form.label.moment" path="moment"/>
	</jstl:if>
	<acme:form-moment code="consumer.offer.form.label.deadline" path="deadline"/>
	<acme:form-textbox code="consumer.offer.form.label.description" path="description"/>
	<acme:form-money code="consumer.offer.form.label.reward" path="reward"/>
	
	<jstl:if test="${command == 'create'}">
		<acme:form-checkbox code="consumer.offer.form.label.checkbox" path="accepted" />
	</jstl:if>
	
	<acme:form-submit test="${command == 'create'}"
		code="provider.request.form.button.create" 
		action="/consumer/offer/create"/>
	<acme:form-return code="consumer.provider.form.button.return"/>
</acme:form>