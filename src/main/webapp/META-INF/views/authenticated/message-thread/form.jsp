<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.message-thread.form.label.title" path="title" />
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox code="authenticated.message-thread.form.label.moment" path="moment" />
	</jstl:if>

	<acme:form-return code="authenticated.message-thread.form.button.return" />
	<acme:form-submit test="${command == 'create'}" 
		code="authenticated.message-thread.form.button.create" 
		action="/authenticated/message-thread/create"/>
	<acme:form-submit test="${command != 'create'}"
		code="authenticated.message-thread.form.button.list-users" 
		action="/authenticated/message-thread-user-account/list?messageThreadId=${messageThreadId}" method="get"/>	
	<acme:form-submit test="${command != 'create'}"
		code="authenticated.message-thread.form.button.list-messages" 
		action="/authenticated/message/list?messageThreadId=${messageThreadId}" method="get"/>	
	<acme:form-submit test="${command == 'show' && !allUsersAdded}" method="get" 
		code="authenticated.message-thread.form.button.add-user" 
		action="/authenticated/message-thread-user-account/create?messageThreadId=${messageThreadId}"/>
	<acme:form-submit test="${command == 'show'}" method="get" 
		code="authenticated.message-thread.form.button.add-message" 
		action="/authenticated/message/create?messageThreadId=${messageThreadId}"/>
</acme:form>