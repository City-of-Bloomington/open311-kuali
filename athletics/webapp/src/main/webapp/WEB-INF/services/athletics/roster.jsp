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

<c:set var="sportname">
	<c:out value="${rosterData.sport.name}" escapeXml="true"></c:out>
</c:set>

<kme:page title="${sportname}" id="athletics-roster" backButton="true"
	homeButton="true" cssFilename="athletics"
	backButtonURL="${pageContext.request.contextPath}/athletics?selectedTab=tab2">
	<kme:content>

		<script type="text/javascript">
			$(window).load(function() {
				$('.tabs-tab2').addClass('selected');
				$('.tabs-panel2').show();
			});
		</script>

		<div class="tabs-tabcontainer">
			<c:url var="newsUrl" value="/athletics/viewSport">
				<c:param name="sportId" value="${rosterData.sport.sportId}" />
			</c:url>

			<a style="width: 32.733333333333334%" class="tabs-tab1" name="tabs-tab1" href="${newsUrl}">News</a>

			<c:if test="${rosterData.sport.seasonId > 0}">
				<c:url var="rosterUrl" value="/athletics/viewRoster">
					<c:param name="sportId" value="${rosterData.sport.sportId}" />
					<c:param name="seasonId" value="${rosterData.sport.seasonId}" />
				</c:url>
				<c:url var="scheduleUrl" value="/athletics/viewSchedule">
					<c:param name="sportId" value="${rosterData.sport.sportId}" />
					<c:param name="seasonId" value="${rosterData.sport.seasonId}" />
				</c:url>

				<a style="width: 32.733333333333334%" class="tabs-tab2 selected" name="tabs-tab2" href="${rosterUrl}">Roster</a>
				<a style="width: 32.733333333333334%" class="tabs-tab3" name="tabs-tab3" href="${scheduleUrl}">Schedule</a>

			</c:if>
		</div>

		<div class="tabs-panel2" name="tabs-panel2" style="display:block">
			<ul data-role="listview" data-theme="c" id="rosterList">
				<c:forEach items="${rosterData.players}" var="player" varStatus="status">
					<li>
						<c:url var="playerUrl" value="/athletics/viewPlayer">
							<c:param name="sportId" value="${rosterData.sport.sportId}" />
							<c:param name="seasonId" value="${rosterData.sport.seasonId}" />
							<c:param name="playerId" value="${player.playerId}" />
						</c:url> 
						<a href="${playerUrl}">
							<img src="<c:out value="${player.thumbnailMedium}" escapeXml="true" />" />
							<h3 class="wrap">
								<c:if test="${not empty player.number}">
									<c:out value="${player.number}" escapeXml="true" />
									<c:if test="${not empty player.name}"> - </c:if>
								</c:if>
								<c:if test="${not empty player.name}">
									<c:out value="${player.name}" escapeXml="true" />
								</c:if>
							</h3>
							<p class="wrap">
								<c:if test="${not empty player.position}">
									<c:out value="${player.position}" escapeXml="true" />,</c:if>
								<c:if test="${not empty player.height}">
									<c:out value="${player.height}" escapeXml="true" />
								</c:if>
								<c:if test="${not empty player.weight}">
									<c:out value="${player.weight}" escapeXml="true" />
								</c:if>
							</p>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>

	</kme:content>
</kme:page>
