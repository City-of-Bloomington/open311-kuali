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

<c:if test="${empty athletics.matchData.matches}">
	There are no game updates.
</c:if>
<c:set var="previousDay" value="false" />
<c:forEach var="match" items="${athletics.matchData.matches}">
	<c:if test="${match.gameDay ne today and not previousDay}">
		<br />
		<ul data-role="listview" data-theme="c" data-dividertheme="d" data-inset="false">
			<li data-role="list-divider">recent games</li>
		</ul>
		<br />
		<c:set var="previousDay" value="true" />
	</c:if>

	<div class="container_12 match-info">
		<div class="grid_3 team-score">
			<c:if test="${not empty match.thumbnail}">
				<img src='<c:out value="${match.thumbnail}"/>' />
			</c:if>
			<span><c:out value="${match.score}" escapeXml="true" /> </span>
		</div>
		<div class="grid_6 ">
			<span class="match-outcome"> <c:if test="${not empty match.winLoss}">
					<c:out value="${match.winLoss}" />
				</c:if> </span> <span class="sport-category"> <c:out value="${match.sportName}" escapeXml="true" /> </span> <span class="opponent"> <c:choose>
					<c:when test="${not empty match.opponent}">
						<c:out value="${match.home}" />&nbsp;<c:out value="${match.opponent}" escapeXml="true" />
					</c:when>
					<c:when test="${not empty match.name}">
						<c:out value="${match.name}" />
					</c:when>
					<c:otherwise>TBD</c:otherwise>
				</c:choose> </span> <span class="date-time"> <c:choose>
					<c:when test="${match.winLoss eq 'in progress' and not empty match.timeRemaining}">
						<c:out value="${match.timeRemaining}" escapeXml="true" />
					</c:when>
					<c:when test="${not empty match.dateTime}">
						<c:out value="${match.dateTime}" escapeXml="true" />
					</c:when>
				</c:choose> </span> <span class="location"> <c:if test="${not empty match.location}">
					<c:out value="${match.location}" escapeXml="true" />
				</c:if> </span>
		</div>
		<div class="grid_3 team-score">
			<c:if test="${not empty match.oppThumbnail}">
				<img src='<c:out value="${match.oppThumbnail}"/>' />
			</c:if>

			<span><c:out value="${match.oppScore}" escapeXml="true" /> </span>
		</div>
	</div>
</c:forEach>

