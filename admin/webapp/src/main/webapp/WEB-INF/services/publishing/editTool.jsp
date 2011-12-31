<%--
  Copyright 2011 The Kuali Foundation Licensed under the Educational Community
  License, Version 2.0 (the "License"); you may not use this file except in
  compliance with the License. You may obtain a copy of the License at
  http://www.osedu.org/licenses/ECL-2.0 Unless required by applicable law or
  agreed to in writing, software distributed under the License is distributed
  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
  express or implied. See the License for the specific language governing
  permissions and limitations under the License.
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<spring:message code="edittool.title" var="title"/>
<kme:page title="${title}" id="edittool" backButton="true" homeButton="true" cssFilename="publishing">
	<kme:content>
		<form:form action="${pageContext.request.contextPath}/publishing/tool/edit" commandName="tool" data-ajax="false" method="post">
			<form:hidden path="toolId"/>
	    	<form:hidden path="versionNumber"/>
			<fieldset>
				<label for="title"><spring:message code="edittool.tool.title"/>:</label>
				<form:input path="title" size="80" />
				<form:errors path="title" />
				<label for="alias"><spring:message code="edittool.tool.alias"/>:</label>
				<form:input path="alias" size="80" />
				<form:errors path="alias" />
				<label for="url"><spring:message code="edittool.tool.url"/>:</label>
				<form:input path="url" size="80" />
				<form:errors path="url" />
				<label for="description"><spring:message code="edittool.tool.description"/>:</label>
				<form:input path="description" size="80" />
				<form:errors path="description" />
				<label for="iconUrl"><spring:message code="edittool.tool.iconurl"/>:</label>
		   		<form:input path="iconUrl"  size="80" />
				<form:errors path="iconUrl" />
				<label for="contacts"><spring:message code="edittool.tool.contacts"/>:</label>
		   		<form:input path="contacts"  size="80" />
				<form:errors path="contacts" />
				<label for="programmingLanguage"><spring:message code="edittool.tool.programmingLanguage"/>:</label>
		   		<form:input path="programmingLanguage"  size="80" />
				<form:errors path="programmingLanguage" />
				<label for="keywords"><spring:message code="edittool.tool.keywords"/>:</label>
		   		<form:input path="keywords"  size="80" />
				<form:errors path="keywords" />
				<label for="licence"><spring:message code="edittool.tool.licence"/>:</label>
		   		<form:input path="licence"  size="80" />
				<form:errors path="licence" />
				<div data-inline="true">
                	<div class="ui-grid-a">
                    	<div class="ui-block-a">
                    		<input data-theme="a" class="submit" type="submit" id="saveButton" value="<spring:message code="common.save"/>" />
                    	</div>
                    	<div class="ui-block-b">
                    		<a data-theme="c"  href="${pageContext.request.contextPath}/publishing/tool" data-role="button"><spring:message code="common.cancel"/></a>                    		
                    	</div>
                	</div>
            	</div>
			</fieldset>
		</form:form>
	</kme:content>
</kme:page>


