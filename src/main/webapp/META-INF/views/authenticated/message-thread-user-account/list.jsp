<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.message-thread-user-account.list.label.username" path="username" width="30%"/>
	<acme:list-column code="authenticated.message-thread-user-account.list.label.fullName" path="fullName" width="70%"/>
</acme:list>