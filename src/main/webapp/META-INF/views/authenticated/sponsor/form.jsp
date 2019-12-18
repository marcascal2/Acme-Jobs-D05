<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.sponsor.form.label.organisationName" path="organisationName"/>
	<acme:form-textbox code="authenticated.sponsor.form.label.credit-card.titleHolder" path="creditCard.titleHolder"/>
	<acme:form-textbox code="authenticated.sponsor.form.label.credit-card.creditCardNumber" path="creditCard.creditCardNumber"/>
	<acme:form-textbox code="authenticated.sponsor.form.label.credit-card.month" path="creditCard.month"/>
	<acme:form-textbox code="authenticated.sponsor.form.label.credit-card.year" path="creditCard.year"/>
	<acme:form-textbox code="authenticated.sponsor.form.label.credit-card.cvc" path="creditCard.cvc"/>
	<acme:form-submit test="${command == 'create'}" code="authenticated.sponsor.form.button.create" action="/authenticated/sponsor/create"/>
	<acme:form-submit method="get" test="${command == 'update'}" code="authenticated.sponsor.form.button.update" action="/authenticated/sponsor/update"/>	
	<acme:form-return code="authenticated.sponsor.form.button.return" />
</acme:form>