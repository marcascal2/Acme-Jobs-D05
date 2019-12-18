<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="sponsor.commercial-banner.list.label.picture" path="picture" width="25%" />
	<acme:list-column code="sponsor.commercial-banner.list.label.slogan" path="slogan" width="25%" />
	<acme:list-column code="sponsor.commercial-banner.list.label.target" path="target" width="25%" />
</acme:list>