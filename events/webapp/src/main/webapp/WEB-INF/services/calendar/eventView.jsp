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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility"%>


<kme:page title="Event" id="Calendar-Events" backButton="true" homeButton="true" cssFilename="events" backButtonURL="${pageContext.request.contextPath}/events">


	<kme:content>
			<ul data-role="listview" data-theme="g">
				<li>
					<h3 style="white-space: normal">
						<c:out value="${event.title}" />
					</h3>
					<p class="wrap">
						<c:out value="${event.location}" />
					</p>
					<p class="wrap">
						<c:out value="${event.displayDate}" />
					</p></li>
				<li>
					<h3>Alert</h3>
					<p class="wrap">
						<c:choose>
							<c:when test="${not empty event.reminder}">
								<c:out value="${event.reminder}" /> before event.
                        </c:when>
							<c:otherwise>No Alert</c:otherwise>
						</c:choose>
					</p></li>
				<li>
					<h3>Availability</h3>
					<p class="wrap">
						<c:out value="${event.showAs}" />
					</p></li>
				<c:if test="${not empty event.category}">
					<li>
						<h3>Category</h3>
						<p class="wrap">
							<c:out value="${event.category}" />
						</p></li>
				</c:if>
				<c:if test="${not empty event.recurrenceMessage}">
					<li>
						<h3>Recurrence</h3>
						<p class="wrap">
							<c:out value="${event.recurrenceMessage}" />
						</p></li>
				</c:if>
				<li>
					<h3>Description</h3>
					<p class="wrap">
						<c:out value="${event.description}" />
					</p></li>
			</ul>
		</div>
		<div data-role="footer" data-id="events-footer" data-position="fixed" role="contentinfo" data-theme="b" class="ui-bar">

			<c:choose>
				<c:when test="${not empty event.seriesId}">
					<div data-role="controlgroup" data-type="horizontal">
						<c:if test="${event.meeting}">
							<a href="${pageContext.request.contextPath}/calendar/invite?eventId=${event.eventId}&seriesId=${event.seriesId}&date=${event.date}" data-role="button" data-ajax="false">invites</a>
						</c:if>
						<c:if test="${event.writeAccess}">
							<a href="${pageContext.request.contextPath}/calendar/editEvent?eventId=${event.eventId}&seriesId=${event.seriesId}&date=${event.date}" data-role="button" data-ajax="false">edit</a>
						</c:if>
						<a href="${pageContext.request.contextPath}/calendar/event?eventId=${event.seriesId}&occurrenceId=${event.eventId}&date=${event.date}" data-role="button" data-ajax="false">series</a>
						<c:if test="${event.writeAccess}">
							<a href="${pageContext.request.contextPath}/calendar/deleteEvent?eventId=${event.eventId}&seriesId=${event.seriesId}&date=${event.date}" data-role="button" data-ajax="false">delete</a>
						</c:if>
						<c:if test="${not empty event.oncourseSiteId}">
							<a href="${pageContext.request.contextPath}/myclasses/${event.oncourseSiteId}" data-role="button" data-ajax="false">course details</a>
						</c:if>
					</div>
				</c:when>
				<c:otherwise>
					<div data-role="controlgroup" data-type="horizontal">
						<c:if test="${event.meeting}">
							<a href="${pageContext.request.contextPath}/calendar/invite?eventId=${event.eventId}&occurrenceId=${occurrenceId}&occurrenceDate=${occurrenceDate}" data-role="button" data-ajax="false">invites</a>
						</c:if>
						<c:if test="${event.writeAccess}">
							<a href="${pageContext.request.contextPath}/calendar/editEvent?eventId=${event.eventId}" data-role="button" data-ajax="false">edit</a>
						</c:if>
						<c:if test="${not empty occurrenceId}">
							<a href="${pageContext.request.contextPath}/calendar/event?eventId=${event.eventId}&date=${occurrenceDate}" data-role="button" data-ajax="false">occurrence</a>
						</c:if>
						<c:if test="${event.writeAccess}">
							<a href="${pageContext.request.contextPath}/calendar/deleteEvent?eventId=${event.eventId}" data-role="button" data-ajax="false">delete</a>
						</c:if>
						<c:if test="${not empty event.oncourseSiteId}">
							<a href="${pageContext.request.contextPath}/myclasses/${event.oncourseSiteId}" data-role="button" data-ajax="false">course details</a>
						</c:if>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	 </kme:content></kme:page>