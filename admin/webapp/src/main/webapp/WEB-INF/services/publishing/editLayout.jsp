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
<form:form action="${pageContext.request.contextPath}/publishing/layout/edit" commandName="layout" data-ajax="false" method="post" id="fm" name="fm">
	<form:hidden path="homeScreenId"/>
	<form:hidden path="versionNumber"/>
	<c:forEach items="${layout.homeTools}" var="homeTool" varStatus="status">
		<form:hidden path="homeTools[${status.index}].toolId"/>
		<form:hidden path="homeTools[${status.index}].tool.toolId"/>
		<form:hidden path="homeTools[${status.index}].tool.title"/>
		<form:hidden path="homeTools[${status.index}].tool.url"/>
		<form:hidden path="homeTools[${status.index}].tool.description"/>
		<form:hidden path="homeTools[${status.index}].tool.iconUrl"/>
		<form:hidden path="homeTools[${status.index}].tool.versionNumber"/>
		<form:hidden path="homeTools[${status.index}].homeScreenId"/>
		<form:hidden path="homeTools[${status.index}].order"/>
		<form:hidden path="homeTools[${status.index}].versionNumber"/>
	</c:forEach>
	<label for="homeScreenName">Title:</label>
	<form:input path="homeScreenName" /><br/>
	<form:errors path="homeScreenName" />
	<br />
	<table border="1">
		<tr>
			<th>Add Tools</th>
			<th>Selected Tools</th>
		</tr>
		<tr>
			<td>
				<div data-role="fieldcontain">
				    <select id="toolToAdd" name="toolToAdd">
				    	<c:forEach items="${availableTools}" var="tool" varStatus="status">
							<option value="${tool.toolId}">${tool.title}</option>
						</c:forEach>
				    </select>
				</div>
				<input name="add" value="Add" type="submit" alt="add tool"/>
				<%--<input type="submit" value="Add" /> --%>
			</td>
			<td>
				<table>
					<input type="hidden" id="removeId" name="removeId" value="" />
					<c:forEach items="${layout.homeTools}" var="homeTool" varStatus="status">
						<tr>
							<td>${homeTool.tool.title}</td>
							<td>
								<input name="remove" value="Remove" type="submit" alt="remove tool" onclick="javascript:document.forms['fm'].elements['removeId'].value = '${homeTool.toolId}';"/>
								<input name="up" value="Up" type="submit" alt="remove tool" onclick="javascript:document.forms['fm'].elements['removeId'].value = '${homeTool.toolId}';"/>
								<input name="down" value="Down" type="submit" alt="remove tool" onclick="javascript:document.forms['fm'].elements['removeId'].value = '${homeTool.toolId}';"/>
							</td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>
	<br />

	<a href="${pageContext.request.contextPath}/publishing/layout">Cancel</a>
	<input type="submit" value="Save" />
</form:form>

</body>
</html>
