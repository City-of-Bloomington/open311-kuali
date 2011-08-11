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
	<title>Publishing :: Edit Layout</title>
</head>
<body>
<h2>Edit Layout</h2>
<form:form action="${pageContext.request.contextPath}/publishing/layout/new" commandName="layout" data-ajax="false" method="post">
	<form:hidden path="homeScreenId"/>
	<form:hidden path="versionNumber"/>
	<label for="homeScreenName">Title:</label>
	<form:input path="homeScreenName" /><br/>
	<form:errors path="homeScreenName" />

	<a href="${pageContext.request.contextPath}/publishing/layout">Cancel</a>
	<input type="submit" value="Save" />
</form:form>
<br />
<table border="1">
	<tr>
		<th>Add Tools</th>
		<th>Selected Tools</th>
	</tr>
	<tr>
		<td>
			<form:form action="${pageContext.request.contextPath}/publishing/layout/new/addTool" commandName="layout" data-ajax="false" method="post">
				<div data-role="fieldcontain">
				    <select id="toolToAdd">
				    	<c:forEach items="${availableTools}" var="tool" varStatus="status">
							<option value="${tool.toolId}">${tool.title}</option>
						</c:forEach>
				    </select>
				</div> 
				<input type="submit" value="Add" />
			</form:form>
		</td>
		<td>
			<ul>
				<c:forEach items="${layout.homeTools}" var="homeTool" varStatus="status">
					<li>
						<form:form action="${pageContext.request.contextPath}/publishing/layout/new/removeTool/${homeTool.homeToolId}" commandName="layout" data-ajax="false" method="post">
							${homeTool.tool.title} <input type="submit" value="Remove" />
						</form:form>
					</li>
				</c:forEach>
			</ul>
		</td>
	</tr>
</table>
</body>
</html>
