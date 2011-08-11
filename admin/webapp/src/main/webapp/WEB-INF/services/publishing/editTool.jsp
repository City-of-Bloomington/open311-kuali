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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
	<title>Publishing :: Edit Tool</title>
</head>
<body>
	<h2>Edit Tool</h2>
	<form:form action="${pageContext.request.contextPath}/publishing/tool/new" commandName="tool" data-ajax="false" method="post">
		<form:hidden path="toolId"/>
	    <form:hidden path="versionNumber"/>
		<fieldset>
			<label for="title">Title:</label>
			<form:input path="title" /><br/>
			<form:errors path="title" />
			<label for="url">Url:</label>
			<form:input path="url" /><br/>
			<form:errors path="url" />
			<label for="description">Description:</label>
			<form:input path="description" /><br/>
			<form:errors path="description" />
			<label for="iconUrl">Icon Url:</label>
		   	<form:input path="iconUrl" /><br/>
			<form:errors path="iconUrl" />
			<a href="${pageContext.request.contextPath}/publishing/tool">Cancel</a>
			<input type="submit" value="Save" />
		</fieldset>
	</form:form>
</body>
</html>
