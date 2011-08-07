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

<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
	<title>Publishing :: Notification</title>
</head>
<body>
<h2>Notification</h2>
<form:form action="${pageContext.request.contextPath}/publishing/notificationSubmit" commandName="notification" data-ajax="false" method="post">
    <form:hidden path="notificationId"/>
    <form:hidden path="versionNumber"/>
	<fieldset>
	<label for="title">Title:</label>
	<form:input path="title" /><br/>
	<form:errors path="title" />
	</fieldset>
	<fieldset>
	<label for="message">Message:</label>
	<form:input path="message" /><br/>
	<form:errors path="message" />
	</fieldset>
	<fieldset>
	<label for="primaryCampus">Campus:</label>
	<form:input path="primaryCampus" /><br/>
	<form:errors path="primaryCampus" />
	</fieldset>
	<fieldset>
	<label for="startDate">Start date:</label>
   	<form:input path="startDate" /><br/>
	<form:errors path="startDate" />
	</fieldset>
	<fieldset>
	<label for="endDate">End date:</label>
	<form:input path="endDate" /><br/>
	<form:errors path="endDate" />
	</fieldset>
	<fieldset>
	<label for="notificationType">Type:</label>
	<form:input path="notificationType" /><br/>
	<form:errors path="notificationType" />
	</fieldset>
	<a href="${pageContext.request.contextPath}/publishing/notifications">Cancel</a>
	<input type="submit" value="Submit" />
</form:form>
</body>
</html>
