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

<c:choose>
	<c:when test="${selectedTab eq 'tab2'}">
		<c:set var="jsFilename" value="show-tab2" />
	</c:when>
	<c:when test="${selectedTab eq 'tab3'}">
		<c:set var="jsFilename" value="show-tab3" />
	</c:when>
	<c:otherwise>
		<c:set var="jsFilename" value="show-tab1" />
	</c:otherwise>
</c:choose>

<kme:page title="Athletics" id="athletics" backButton="true" homeButton="true" cssFilename="athletics" backButtonURL="${pageContext.request.contextPath}/home" jsFilename="${jsFilename}">
	<kme:content>
		<div class="tabs-tabcontainer">
				<a style="width:32.733333333333334%" class="tabs-tab1" name="tabs-tab1" href="#"><c:out value="${athletics.matchData.category}" /> </a>
				<a style="width:32.733333333333334%" class="tabs-tab2" name="tabs-tab2" href="#"><c:out value="${athletics.sportData.category}" /> </a>
				<a style="width:32.733333333333334%" class="tabs-tab3" name="tabs-tab3" href="#"><c:out value="${athletics.newsData.category}" /> </a>
		</div>

		<div class="tabs-panel1" name="tabs-panel1" id="matches">
				<jsp:include page="autoMatch.jsp" />
		</div>
		<div class="tabs-panel2" name="tabs-panel2">
			<ul data-role="listview">
				<c:forEach var="sport" items="${athletics.sportData.sports}">
					<li><a href="${pageContext.request.contextPath}/athletics/viewSport?sportId=${sport.sportId}"> <c:if test="${not empty sport.thumbnail}">
								<img src='<c:out value="${sport.thumbnail}"/>' class="ui-li-icon ui-li-thumb" />
							</c:if>${sport.name}</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div class="tabs-panel3" name="tabs-panel3">
			<ul data-role="listview" data-theme="c" class="news-list">
				<c:forEach var="news" items="${athletics.newsData.news}">
					<li><c:url var="story" value="/athletics/viewStory">
							<c:param name="link" value="${news.url}" />
							<c:param name="selectedTab" value="tab3" />
						</c:url> <a href="${story}"> <img src='<c:out value="${news.thumbnail}" escapeXml="true" />' class="rowicon-news" />
							<p>
								<c:out value="${news.title}" />
							</p> </a>
					</li>
				</c:forEach>
			</ul>
		</div>
	</kme:content>
</kme:page>
