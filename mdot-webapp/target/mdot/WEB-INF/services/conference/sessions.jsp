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

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<spring:message code="sessions.title" var="title"/>
<spring:message code="abbr.wednesday" var="wed"/>
<spring:message code="abbr.thursday" var="thu"/>
<spring:message code="abbr.friday" var="fri"/>
<spring:message code="abbr.saturday" var="sat"/>
<spring:message code="abbr.wednesday" var="wed"/>
<spring:message code="abbr.alldays" var="all"/>
<spring:message code="label.time" var="time"/>
<spring:message code="label.location" var="location"/>
<spring:message code="label.description" var="description"/>
<spring:message code="label.ends" var="ends"/>
<spring:message code="time.tbd" var="tbd"/>

<kme:page title="${title}" id="conference" backButton="true" backButtonURL="${pageContext.request.contextPath}/conference" homeButton="true" cssFilename="conference">
	<kme:content>
	
		<div id="daySelectTabs">
			<a class="${param.date eq '092811' ? 'selected' : ''}" href="?date=092811">${wed}</a><a class="${param.date eq '092911' ? 'selected' : ''}" href="?date=092911">${thu}</a><a class="${param.date eq '093011' ? 'selected' : ''}" href="?date=093011">${fri}</a><a class="${param.date eq '100111' ? 'selected' : ''}" href="?date=100111">${sat}</a><a class="${empty param.date ? 'selected' : ''}" href="?date=">${all}</a>
		</div>
		<kme:listView>
			
			<c:forEach items="${sessions}" var="session" varStatus="status">
		    	<kme:listItem cssClass="${session.trackCSSClass}">
		    		<a href="sessionDetails/${session.id}">
		    			<h3 class="wrap">
		    				${session.title}
		    			</h3>
		    			<p class="wrap">${time}: 
			    			<c:choose>
			    				<c:when test="${not empty session.startTime && session.startTime != 'null'}">
									<fmt:parseDate value="${session.startTime}" pattern="yyyy-MM-dd HH:mm:ss" var="sdate"/>
									<fmt:formatDate value="${sdate}" pattern="E hh:mm a"/>
					    			<c:if test="${not empty session.endTime && session.endTime != 'null'}">
					    				 - 	<fmt:parseDate value="${session.endTime}" pattern="yyyy-MM-dd HH:mm:ss" var="edate"/>
											<fmt:formatDate value="${edate}" pattern="E hh:mm a"/>
					    			</c:if>
			    				</c:when>
			    				<c:otherwise>
			    					<c:choose>
					    				<c:when test="${not empty session.endTime && session.endTime != 'null'}">
					    					 ${ends}:  <fmt:parseDate value="${session.endTime}" pattern="yyyy-MM-dd HH:mm:ss" var="edate"/>
													<fmt:formatDate value="${edate}" pattern="E hh:mm a"/>
					    				</c:when>
					    				<c:otherwise>
					    					${tbd}
					    				</c:otherwise>
				    				</c:choose>
			    				</c:otherwise>
			    			</c:choose>
		    			</p>
		    			<p class="wrap">${location}: 
		    				<c:choose>
			    				<c:when test="${not empty session.location && session.location != 'null'}">
			    					${session.location}
			    				</c:when>
			    				<c:otherwise>
			    					${tbd}
			    				</c:otherwise>
			    			</c:choose>
		    			</p>
		    			<%-- <p class="wrap">Track: 
		    				<c:choose>
			    				<c:when test="${not empty session.track && session.track != 'null'}">
			    					${session.track}
			    				</c:when>
			    				<c:otherwise>
			    					${tbd}
			    				</c:otherwise>
			    			</c:choose>
		    			</p>
		    			<p class="wrap">Level: 
		    				<c:choose>
			    				<c:when test="${not empty session.level && session.level != 'null'}">
			    					${session.level}
			    				</c:when>
			    				<c:otherwise>
			    					${tbd}
			    				</c:otherwise>
			    			</c:choose>
		    			</p>
		    			<p class="wrap">Type: 
		    				<c:choose>
			    				<c:when test="${not empty session.type && session.type != 'null'}">
			    					${session.type}
			    				</c:when>
			    				<c:otherwise>
			    					${tbd}
			    				</c:otherwise>
			    			</c:choose>
		    			</p>
		    			--%>
		    			
		    			
		    			<%-- <p class="wrap">${description}: ${session.description}</p> --%>
		    		</a>
		    	</kme:listItem>            
		    </c:forEach>
		</kme:listView>
	</kme:content>
</kme:page>
