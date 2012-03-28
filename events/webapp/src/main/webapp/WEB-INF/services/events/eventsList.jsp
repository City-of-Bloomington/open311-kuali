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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:choose>
	<c:when test="${category.returnPage eq 'Home'}">
		<c:url var="back" value="/home" />
	</c:when>
	<c:otherwise>
		<c:url var="back" value="/events" />
	</c:otherwise>
</c:choose>
<kme:page title="${category.title}" id="events" backButton="true"
	homeButton="true" backButtonURL="${back}">
	<kme:content>

		<kme:listView id="eventslist" dataTheme="c" dataDividerTheme="b" filter="false">
			<c:choose>
				<c:when test="${empty events}">
					<kme:listItem><h3 class="wrap">There are no ${category.title} events scheduled.</h3></kme:listItem>
				</c:when>
				<c:otherwise>
					<c:set var="lastDate" value="NULL" />
					<c:forEach items="${events}" var="event" varStatus="status">
						<c:if test="${lastDate != event.displayStartDate}">
							<c:set var="lastDate" value="${event.displayStartDate}" />
							<kme:listItem dataTheme="b" dataRole="list-divider">
								<fmt:formatDate value="${event.startDate}"
									pattern="EEEE MMMM d, yyyy" />
							</kme:listItem>
						</c:if>
						<kme:listItem>
							<c:url var="url" value="/events/viewEvent">
								<c:param name="categoryId" value="${category.categoryId}"></c:param>
								<c:param name="campus" value="${campus}"></c:param>
								<c:param name="eventId" value="${event.eventId}"></c:param>
							</c:url>
							<a style="padding-right: 25px !important;" href="${url}">
								<h3 class="wrap">
									<c:out value="${event.title}" />
								</h3>
								<p class="wrap">
									<fmt:formatDate value="${event.startDate}"
										pattern="EEEE MMMM d, yyyy @ h:mm a" />
								</p>
							</a>
						</kme:listItem>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</kme:listView>
	</kme:content>
</kme:page>
