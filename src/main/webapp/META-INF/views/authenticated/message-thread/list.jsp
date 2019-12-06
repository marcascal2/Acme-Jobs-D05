<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.message-thread.list.label.title" path="title" width="70%"/>
	<acme:list-column code="authenticated.message-thread.list.label.moment" path="moment" width="30%"/>
</acme:list>