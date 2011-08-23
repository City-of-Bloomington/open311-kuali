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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility"%>
<c:set var="sportname">
	<c:out value="${sport.name}" escapeXml="true"></c:out>
</c:set>
<kme:page title="${sportname}" id="athletics-news" backButton="true" homeButton="true" cssFilename="athletics" backButtonURL="${pageContext.request.contextPath}/athletics?selectedTab=tab2">
	<kme:content>
	
	
<script type="text/javascript">
 $(window).load(function() {
     $('.tabs-tab1').addClass('selected');
     $('.tabs-panel1').show();
 });
</script>

		<div class="tabs-tabcontainer">

			<c:url var="newsUrl" value="/athletics/viewSport">
				<c:param name="sportId" value="${sport.sportId}" />
			</c:url>

			
				<a style="width:32.733333333333334%" class="tabs-tab1" name="tabs-tab1" href="${newsUrl}">News</a>
			
			<c:if test="${sport.seasonId > 0}">
				<c:url var="rosterUrl" value="/athletics/viewRoster">
					<c:param name="sportId" value="${sport.sportId}" />
					<c:param name="seasonId" value="${sport.seasonId}" />
				</c:url>
				<c:url var="scheduleUrl" value="/athletics/viewSchedule">
					<c:param name="sportId" value="${sport.sportId}" />
					<c:param name="seasonId" value="${sport.seasonId}" />
				</c:url>
					<a style="width:32.733333333333334%" class="tabs-tab2" name="tabs-tab2" href="${rosterUrl}">Roster</a>
					<a style="width:32.733333333333334%" class="tabs-tab3" name="tabs-tab3" href="${scheduleUrl}">Schedule</a>
			</c:if>
		</div>

		<div class="tabs-panel1" name="tabs-panel1">
			<ul data-role="listview" data-theme="c">
				<c:choose>
					<c:when test="${not empty newsStream}">
						<c:forEach items="${newsStream.articles}" var="item" varStatus="status">
							<c:forEach items="${item.articles}" var="article" varStatus="status">
								<li><c:url var="articleUrl" value="/athletics/viewStory">
										<c:param name="sportId" value="${sport.sportId}" />
										<c:param name="link" value="${article.link}" />
									</c:url> <a href="${articleUrl}"> <c:choose>
											<c:when test="${not empty article.thumbnailImageUrl}">
												<img src="<c:out value="${article.thumbnailImageUrl}" escapeXml="true"  />" />
											</c:when>
											<c:otherwise>
												<img src="${pageContext.request.contextPath}/images/default-blockiu.png" />
											</c:otherwise>
										</c:choose>
										<p class="wrap">
											<strong><c:out value="${article.title}" escapeXml="true" /> </strong>
										</p> <%--<p>
								<c:out value="${article.description}" escapeXml="false" />
							</p>--%>
										<p class="datestamp">
											<fmt:formatDate value="${article.publishDate}" pattern="EEE, dd MMM yyyy" />
										</p> </a>
								</li>
							</c:forEach>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<li>There is currently no news for <c:out value="${sport.name}" />.</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</kme:content>
</kme:page>
