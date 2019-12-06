<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.challenge.list.label.deadline" path="deadline" width="200"/>
	<acme:list-column code="authenticated.challenge.list.label.title" path="title" width="200"/>
	<acme:list-column code="authenticated.challenge.list.label.goldGoal" path="goldGoal" width="200"/>
	<acme:list-column code="authenticated.challenge.list.label.silverGoal" path="silverGoal" width="200"/>
	<acme:list-column code="authenticated.challenge.list.label.bronzeGoal" path="bronzeGoal" width="200"/>
</acme:list>
