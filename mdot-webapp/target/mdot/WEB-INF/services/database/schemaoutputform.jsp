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

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<kme:page title="database" id="database-webapp" backButton="true" homeButton="true" cssFilename="database" jsFilename="database">
	<kme:content>

<form:form action="${pageContext.request.contextPath}/database" commandName="form" data-ajax="false" method="post">
<fieldset>

<div data-role="fieldcontain">
	<label for="dialectType" class="select">Database:</label>
	<form:select data-theme="c" path="dialectType" multiple="false" items="${dialectTypes}" class="required"/>
	<form:errors path="dialectType"/>
</div> 

<div data-role="fieldcontain">
    <label for="delimiter">Delimiter:</label>
    <form:input path="delimiter" type="text" value="" />
</div>

<div data-role="fieldcontain">
    <label for="newLineBeforeDelimiter">New Line for Delimiter?:</label>
	<form:checkbox path="newLineBeforeDelimiter" id="newLineBeforeDelimiterCheckbox"/>
</div>

<div data-role="fieldcontain">
    <label for="removeForeignKeys">Remove foreign keys:</label>
	<form:checkbox path="removeForeignKeys" id="removeForeignKeysCheckbox"/>
</div>

<div data-inline="true">
    <div class="ui-grid-a">
        <div class="ui-block-a"><a href="${pageContext.request.contextPath}/database" data-theme="c"  data-role="button">Cancel</a></div>
        <div class="ui-block-b">
            <input data-theme="a" class="submit" type="submit" value="View" />
        </div>
        <div class="ui-block-c">
            <input data-theme="a" class="submit" type="submit" value="Download" name="download" />
        </div>
    </div>
</div>

</fieldset>
</form:form>

	</kme:content>
</kme:page>
