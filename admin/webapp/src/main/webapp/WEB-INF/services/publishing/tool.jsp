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

<!DOCTYPE html>
<html>
<head>
	<title>Publishing :: Tool</title>
</head>
<body>
	<h2>Tool</h2>
	<a href="${pageContext.request.contextPath}/publishing/tool/new">new</a><br /><br />
	<table>
		<c:forEach items="${tools}" var="tool" varStatus="status">
			<tr>
				<td><c:out value="${tool.title}"/></td><td><a href="${pageContext.request.contextPath}/publishing/tool/edit/${tool.toolId}">edit</a> <a href="${pageContext.request.contextPath}/publishing/tool/delete/${tool.toolId}">delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
