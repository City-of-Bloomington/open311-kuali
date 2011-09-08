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
	<title>Tours</title>
</head>
<body>
	<h2>Tours</h2>
	<a href="${pageContext.request.contextPath}/tours/new">new</a><br /><br />
	<table>
		<c:forEach items="${tours}" var="tour" varStatus="status">
			<tr>
				<td>${tour.name}</td>
				<td><a href="${pageContext.request.contextPath}/tours/edit/${tour.tourId}">edit</a> <a href="${pageContext.request.contextPath}/tours/delete/${tour.tourId}">delete</a> <a href="${pageContext.request.contextPath}/tours/kml/${tour.tourId}">kml</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<h2>Common Points of Interest</h2>
	<a href="${pageContext.request.contextPath}/tours/poi/new">new</a><br /><br />
	<table>
		<c:forEach items="${pois}" var="poi" varStatus="status">
			<tr>
				<td>${poi.name}</td>
				<td><a href="${pageContext.request.contextPath}/tours/poi/edit/${poi.poiId}">edit</a> <a href="${pageContext.request.contextPath}/tours/poi/delete/${poi.poiId}">delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
