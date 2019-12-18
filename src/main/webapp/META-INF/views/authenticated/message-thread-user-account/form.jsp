<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<jstl:if test="${command != 'create'}">
		<acme:form-textbox readonly="true" code="authenticated.message-thread-user-account.list.label.username" path="userAccount.username" />
		<acme:form-textbox readonly="true" code="authenticated.message-thread-user-account.list.label.fullName" path="fullName" />
	</jstl:if>
	
	<jstl:if test="${command == 'create'}">
		<acme:form-select code="Usuario: " path="userToAdd" readonly="false">
			<jstl:forEach items="${user_usernames}" var="username" varStatus="loop">
				<acme:form-option code="${username}" value="${user_ids[loop.index]}"/>
			</jstl:forEach>
		</acme:form-select>
	</jstl:if>
	
	<input id="messageThreadId" name="messageThreadId" value="${messageThreadId}" type="hidden" />
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.message-thread-user-account.form.button.add" action="/authenticated/message-thread-user-account/create"/>
	<acme:form-submit test="${command == 'show'}" code="authenticated.message-thread-user-account.form.button.delete" action="/authenticated/message-thread-user-account/delete"/>
	<acme:form-return code="authenticated.message-thread-user-account.form.button.return" />
</acme:form>