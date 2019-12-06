<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="administrator.spam_word.form.label.spanishTranslation" path="spanishTranslation" />
	<acme:form-moment code="administrator.spam_word.form.label.englishTranslation" path="englishTranslation" />
	<acme:form-url code="administrator.spam_word.form.label.spamThreshold" path="spamThreshold" />
	
	<acme:form-submit test="${command == 'show' }"
	 code="administrator.spam_word.form.button.update"
	 action="/administrator/spam-word/update"/>
	 
	 <acme:form-submit test="${command == 'update' }"
	 code="administrator.spam_word.form.button.update"
	 action="/administrator/spam-word/update"/>

	<acme:form-return code="administrator.spam_word.form.button.return" />
</acme:form>
