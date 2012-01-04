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
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>My Calendar</title>
<link href="${pageContext.request.contextPath}/css/jquery.mobile-1.0b1.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/custom.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mobile-1.0b1.min.js"></script>
</head>

<body>
	<div data-role="page" id="Calendar-Events">
		<div data-role="header">
			<h1>Invite</h1>
			<c:choose>
				<c:when test="${referer eq 'pending'}">
					<a href="${pageContext.request.contextPath}/calendar/pendingMeetings" data-icon="back" data-iconpos="notext">meetings</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/calendar/event?eventId=${invite.eventId}&date=${date}&occurrenceId=${occurrenceId}" data-icon="back" data-iconpos="notext" data-ajax="false">view event</a>
				</c:otherwise>
			</c:choose>
			<a href="${pageContext.request.contextPath}/home" data-icon="home" data-iconpos="notext">home</a>
		</div>
		<div data-role="content">
		
		 <h3 style=" margin-top:0px">Event Details</h3>
		
		
		  <ul data-role="listview" data-theme="c" data-inset="true">
    		<li>
      			<h4 class="wrap"">
						<c:out value="${invite.title}" />
					</h4>
     			  <p class="wrap"><c:choose>
							<c:when test="${invite.allDay}">
								<c:out value="${invite.start}" /> all day
							</c:when>
							<c:otherwise>
								<c:out value="${invite.start}" />
								-
								<c:out value="${invite.end}" />
							</c:otherwise>
						</c:choose></p> 
     			 <c:if test="${not empty invite.recurrenceMessage}">
					
						<h4>Recurrence</h4>
						<p class="wrap"">
							<c:out value="${invite.recurrenceMessage}" />
						</p>
				</c:if>
    			  <h4>Host</h4>
      			   <p class="wrap"><c:out value="${invite.hostName}" /></p>
    		</li>
   
  			</ul>
		
		
		
<div data-role="collapsible" data-collapsed="true">
    <h3>Accepted</h3>
   
<c:forEach var="accepted" items="${invite.acceptedAttendees}">
						
							<c:out value="${accepted.name}" />
						<br />
					</c:forEach>

  </div>
  <div data-role="collapsible" data-collapsed="true">
    <h3>Tentative</h3>

<c:forEach var="tentative" items="${invite.tentativeAttendees}">
					
							<c:out value="${tentative.name}" />
						<br />
					</c:forEach>
 
  </div>
  <div data-role="collapsible" data-collapsed="true">
    <h3>Declined</h3>
    
       <c:forEach var="declined" items="${invite.declinedAttendees}">
					
							<c:out value="${declined.name}" />
							<br />
					</c:forEach>
    
  </div>
  <div data-role="collapsible" data-collapsed="true">
    <h3>Awaiting Reply</h3>
   
      <c:forEach var="noReply" items="${invite.noReplyAttendees}">
					
							<c:out value="${noReply.name}" />
						<br />
					</c:forEach>
    
  </div>
		
		

		</div>
		<div data-role="footer" class="ui-bar" data-position="fixed" data-theme="b">
			
				<c:choose>
					<c:when test="${invite.cancelation}">
						<a href="${pageContext.request.contextPath}/calendar/meetingAction?eventId=${invite.eventId}&type=R&occurrenceId=${occurrenceId}&occurrenceDate=${date}&referer=${referer}" data-role="button" data-icon="delete" data-ajax="false">Delete</a>
						<a href="${pageContext.request.contextPath}/calendar/meetingAction?eventId=${invite.eventId}&type=K&occurrenceId=${occurrenceId}&occurrenceDate=${date}&referer=${referer}" data-role="button" data-icon="check"data-ajax="false">Keep</a>
					</c:when>
					<c:otherwise>
						<a href="${pageContext.request.contextPath}/calendar/meetingAction?eventId=${invite.eventId}&type=A&occurrenceId=${occurrenceId}&occurrenceDate=${date}&referer=${referer}" data-role="button" data-icon="check" data-ajax="false">Accept</a>
						<a href="${pageContext.request.contextPath}/calendar/meetingAction?eventId=${invite.eventId}&type=T&occurrenceId=${occurrenceId}&occurrenceDate=${date}&referer=${referer}" data-role="button" data-icon="question" data-ajax="false">Tentative</a>
						<a href="${pageContext.request.contextPath}/calendar/meetingAction?eventId=${invite.eventId}&type=D&occurrenceId=${occurrenceId}&occurrenceDate=${date}&referer=${referer}" data-role="button" data-icon="delete" data-ajax="false">Decline</a>
					</c:otherwise>
				</c:choose>
			
		</div>
	</div>
	<!-- /page -->

</body>
</html>