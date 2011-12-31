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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="attendeelist.title" var="title"/>

<kme:page title="${title}" id="conference" backButton="true"
	homeButton="true">
	<kme:content>
		<kme:listView>
			<kme:listItem>
				<a href="attendees?start=A&end=C">A - C</a>
			</kme:listItem>
			<kme:listItem>
				<a href="attendees?start=D&end=F">D - F</a>
			</kme:listItem>
			<kme:listItem>
				<a href="attendees?start=G&end=I">G - I</a>
			</kme:listItem>
			<kme:listItem>
				<a href="attendees?start=J&end=L">J - L</a>
			</kme:listItem>
			<kme:listItem>
				<a href="attendees?start=M&end=O">M - O</a>
			</kme:listItem>
			<kme:listItem>
				<a href="attendees?start=P&end=R">P - R</a>
			</kme:listItem>
			<kme:listItem>
				<a href="attendees?start=S&end=U">S - U</a>
			</kme:listItem>
			<kme:listItem>
				<a href="attendees?start=V&end=Z">V - Z</a>
			</kme:listItem>
		</kme:listView>
	</kme:content>
</kme:page>
