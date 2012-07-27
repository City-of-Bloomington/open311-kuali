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
	<title>Publishing :: Edit News Source</title>
</head>
<body>
	<h2>Edit News Source</h2>
	<form:form action="${pageContext.request.contextPath}/publishing/news/edit" commandName="source" data-ajax="false" method="post">
		<form:hidden path="id"/>
	    <form:hidden path="versionNumber"/>
	    <form:hidden path="order"/>
		<fieldset>
			<table>
				<tr>
					<th><label for="name">Name</label></th>
					<td><form:input path="name" size="80" /></td>
					<td><form:errors path="name" /></td>
				</tr>
				<tr>
					<th><label for="url">Url</label></th>
					<td><form:input path="url" size="80" /></td>
					<td><form:errors path="url" /></td>
				</tr>
				<tr>
					<th><label for="active">Active</label></th>
					<td><form:checkbox path="active" id="active"/></td>
				</tr>
			</table>
		</fieldset>
		<a href="${pageContext.request.contextPath}/publishing/news">Cancel</a>
		<input type="submit" value="Save" />
	</form:form>
</body>
</html>
