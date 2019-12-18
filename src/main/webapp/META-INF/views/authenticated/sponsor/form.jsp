<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.sponsor.form.label.organisationName" path="organisationName"/>
	<jstl:if test="${creditCard}">
	<acme:form-submit method="get" test="${command == 'update'}" code="authenticated.sponsor.credit-card.form.button.update" action="/sponsor/credit-card/update?sponsor_id=${sponsorId}"/>
	</jstl:if>
	<jstl:if test="${!creditCard}">
	<acme:form-submit method="get" test="${command == 'update'}" code="authenticated.sponsor.credit-card.form.button.create" action="/sponsor/credit-card/create?sponsor_id=${sponsorId}"/>
	</jstl:if>
	<acme:form-submit test="${command == 'create'}" code="authenticated.sponsor.form.button.create" action="/authenticated/sponsor/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.sponsor.form.button.update" action="/authenticated/sponsor/update"/>	
	<acme:form-return code="authenticated.sponsor.form.button.return" />
</acme:form>