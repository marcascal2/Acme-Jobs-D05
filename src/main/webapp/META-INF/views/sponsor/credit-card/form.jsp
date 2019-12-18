<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="sponsor.credit-card.form.label.titleHolder" path="titleHolder"/>
	<acme:form-textbox code="sponsor.credit-card.form.label.creditCardNumber" path="creditCardNumber"/>
	<acme:form-textbox code="sponsor.credit-card.form.label.month" path="month"/>
	<acme:form-textbox code="sponsor.credit-card.form.label.year" path="year"/>
	<acme:form-textbox code="sponsor.credit-card.form.label.cvc" path="cvc"/>
	
	<acme:form-hidden path="sponsorId"/>
	
	<acme:form-submit test="${command == 'create'}" code="sponsor.credit-card.form.button.create" action="/sponsor/credit-card/create"/>
	<acme:form-submit test="${command == 'update'}" code="sponsor.credit-card.form.button.update" action="/sponsor/credit-card/update"/>
	<acme:form-return code="sponsor.credit-card.form.button.return" />
</acme:form>