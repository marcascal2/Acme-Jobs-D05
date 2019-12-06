<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="authenticated.message-thread.form.label.title" path="title" />
	<acme:form-textbox code="authenticated.message-thread.form.label.moment" path="moment" />

	<acme:form-return code="authenticated.message-thread.form.button.return" />
	<acme:form-submit code="authenticated.message-thread.form.button.list-messages" action="/authenticated/message/list?messageThreadId=${messageThreadId}" method="get"/>	
	
</acme:form>