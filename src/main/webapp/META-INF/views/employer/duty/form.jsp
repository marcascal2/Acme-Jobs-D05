<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="employer.job.duty.form.label.job" path="job" />

	<acme:form-textbox code="employer.job.duty.form.label.title" path="title" />
	<acme:form-textbox code="employer.job.duty.form.label.description" path="description" />
	<acme:form-textbox code="employer.job.duty.form.label.percentage" path="percentageTimeForWeek" />

	<acme:form-return code="employer.job.duty.form.button.return" />
</acme:form>