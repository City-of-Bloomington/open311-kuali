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

<kme:page title="Athletics" id="athletics" backButton="true" homeButton="true" cssFilename="athletics">
	<kme:content>
	
<%-- This script needs to go in an external service.js file --%>
<script type="text/javascript">
 $(window).load(function() {
     $('.tabs-tab1').addClass('selected');
     $('.tabs-panel1').show();
 });
</script>


    <div class="tabs-tabcontainer container_12">
      <div class="grid_4"><a class="tabs-tab1" name="tabs-tab1" href="#"><c:out value="${athletics.matchData.category}" /></a></div>
      <div class="grid_4"><a class="tabs-tab2" name="tabs-tab2" href="#"><c:out value="${athletics.sportData.category}" /></a></div>
      <div class="grid_4"><a class="tabs-tab3" name="tabs-tab3" href="#"><c:out value="${athletics.newsData.category}" /></a></div>
    </div>


    <div class="tabs-panel1" name="tabs-panel1">
		
			<c:forEach var="match" items="${athletics.matchData.matches}">

				<div class="container_12 match-info">
					<div class="grid_3 team-score">
						<c:if test="${not empty match.thumbnail}">
							<img src='<c:out value="${match.thumbnail}"/>' />
						</c:if>

						<span><c:out value="${match.score}" escapeXml="true" />
						</span>

					</div>


					<div class="grid_6 ">
					<span class="match-outcome">
						<c:if test="${not empty match.winLoss}">
							<c:out value="${match.winLoss}" />
						</c:if>
						</span>
						<span class="sport-category">
						<c:out value="${match.sportName}" escapeXml="true" />
						</span>
						<span class="opponent">
						<c:choose>
							<c:when test="${not empty match.opponent}">
								<c:out value="${match.home}" />&nbsp;<c:out
									value="${match.opponent}" escapeXml="true" />
							</c:when>
							<c:when test="${not empty match.name}">
								<c:out value="${match.name}" />
							</c:when>
							<c:otherwise>TBD</c:otherwise>
						</c:choose>
						</span>

                        <span class="date-time">
						<c:choose>
							<c:when
								test="${match.winLoss eq 'in progress' and not empty match.timeRemaining}">
								<c:out value="${match.timeRemaining}" escapeXml="true" />

							</c:when>
							<c:when test="${not empty match.dateTime}">
								<c:out value="${match.dateTime}" escapeXml="true" />

							</c:when>
						</c:choose>
						</span>
						<span class="location">
						<c:if test="${not empty match.location}">
							<c:out value="${match.location}" escapeXml="true" />
						</c:if>
						</span>

					</div>
					<div class="grid_3 team-score">
						<c:if test="${not empty match.oppThumbnail}">
							<img src='<c:out value="${match.oppThumbnail}"/>' />
						</c:if>

						<span><c:out value="${match.oppScore}" escapeXml="true" /></span>
					</div>
				</div>
			</c:forEach>
		
			
	
		
		</div>
		
		
		
    <div class="tabs-panel2" name="tabs-panel2">



	
	   <ul data-role="listview">
			<c:forEach var="sport" items="${athletics.sportData.sports}">
				<li>
				
						<a href="${pageContext.request.contextPath}/athletics/viewSport?sportId=${sport.sportId}"> <c:if test="${not empty sport.thumbnail}">
								<img src='<c:out value="${sport.thumbnail}"/>' class="ui-li-icon ui-li-thumb" />
							</c:if>${sport.name}</a>
				
				</li>
			</c:forEach>
		</ul>
		



		</div>
    <div class="tabs-panel3" name="tabs-panel3">	

		 <ul data-role="listview" data-theme="c" class="news-list">
			<c:forEach var="news" items="${athletics.newsData.news}">
				<li>
					
						<c:url var="story" value="/athletics/viewStory">
							<c:param name="link" value="${news.url}" />
						</c:url>
						
						<a href="${story}"> <img src='<c:out value="${news.thumbnail}" escapeXml="true" />' class="rowicon-news" /> <p><c:out value="${news.title}" /></p>
				
							</a>
					
				</li>
			</c:forEach>
		</ul>


</div>
	
		
		
		
	



		
		
	</kme:content>
</kme:page>
