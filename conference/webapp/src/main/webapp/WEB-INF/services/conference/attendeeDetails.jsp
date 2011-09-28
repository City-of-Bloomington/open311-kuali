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
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>

<kme:page title="Attendee Details" id="attendeedetails" homeButton="true" backButton="true" cssFilename="conference">
	<kme:content>
	    <kme:listView id="attendeeList" filter="false" dataTheme="c" dataInset="false" cssClass="attendeeDetails">
			<kme:listItem dataRole="list-divider">
	        	${attendee.firstName} ${attendee.lastName}
	        </kme:listItem>
	        <kme:listItem>
	        	<p>
	        	<c:if test="${not empty attendee.institution}" >Title: ${attendee.title}<br/></c:if>
	        	<c:if test="${not empty attendee.institution}" >Organization: ${attendee.institution}<br/></c:if>
	        	<c:if test="${not empty attendee.institution}" >Campus: ${attendee.campus}<br/></c:if>
	        	<c:if test="${not empty attendee.workAddress1}" >${attendee.workAddress1}<br/></c:if>
	        	<c:if test="${not empty attendee.workAddress2}" >${attendee.workAddress2}<br/></c:if>
	        	<c:if test="${not empty attendee.workCity}" >${attendee.workCity} </c:if>
	        	<c:if test="${not empty attendee.workState}" >${attendee.workState}</c:if>
	        	<c:if test="${not empty attendee.workState && not empty attendee.workZip}" >, </c:if>
	        	<c:if test="${not empty attendee.workZip}" >${attendee.workZip}</c:if>
	        	<c:if test="${not empty attendee.country}" ><br/>${attendee.country}</c:if>
	        	</p>
	        </kme:listItem>
	        
	        <c:if test="${not empty attendee.cellPhone}" >
				<kme:listItem cssClass="link-phone">
					<c:set var="cellPhone"><c:out value="${attendee.cellPhone}" /></c:set>
					<a href="tel:${cellPhone}">Mobile: <c:out value="${cellPhone}" /></a>
				</kme:listItem>
			</c:if>
			 <c:if test="${not empty attendee.workPhone}" >
				<kme:listItem cssClass="link-phone">
					<c:set var="workPhone"><c:out value="${attendee.workPhone}" /></c:set>
					<a href="tel:${workPhone}">Work: <c:out value="${workPhone}" /></a>
				</kme:listItem>
			</c:if>
	        <c:if test="${not empty attendee.email}" >
				<kme:listItem cssClass="link-email">
					<c:set var="email"><c:out value="${attendee.email}" /></c:set>
					<a href="mailto:${email}"><c:out value="${email}" /></a>
				</kme:listItem>
			</c:if>
		</kme:listView>
	</kme:content>
</kme:page>