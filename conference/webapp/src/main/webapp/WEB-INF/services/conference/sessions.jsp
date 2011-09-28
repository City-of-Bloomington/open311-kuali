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

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>

<kme:page title="Sessions" id="conference" backButton="true" backButtonURL="${pageContext.request.contextPath}/conference" homeButton="true" cssFilename="conference">
	<kme:content>
	
		<div id="daySelectTabs">
			<a class="${param.date eq '092811' ? 'selected' : ''}" href="?date=092811">Wed</a><a class="${param.date eq '092911' ? 'selected' : ''}" href="?date=092911">Thu</a><a class="${param.date eq '093011' ? 'selected' : ''}" href="?date=093011">Fri</a><a class="${param.date eq '100111' ? 'selected' : ''}" href="?date=100111">Sat</a><a class="${empty param.date ? 'selected' : ''}" href="?date=">All</a>
		</div>
		<kme:listView>
			
			<c:forEach items="${sessions}" var="session" varStatus="status">
		    	<kme:listItem>
		    		<a href="sessionDetails/${session.id}">
		    			<h3 class="wrap">
		    				${session.title}
		    			</h3>
		    			<p class="wrap">Time: 
			    			<c:choose>
			    				<c:when test="${not empty session.startTime && session.startTime != 'null'}">
			    					${session.startTime}
					    			<c:if test="${not empty session.endTime && session.endTime != 'null'}">
					    				 - ${session.endTime}
					    			</c:if>
			    				</c:when>
			    				<c:otherwise>
			    					<c:choose>
					    				<c:when test="${not empty session.endTime && session.endTime != 'null'}">
					    					 Ends: ${session.endTime}
					    				</c:when>
					    				<c:otherwise>
					    					TDB
					    				</c:otherwise>
				    				</c:choose>
			    				</c:otherwise>
			    			</c:choose>
		    			</p>
		    			<p class="wrap">Location: 
		    				<c:choose>
			    				<c:when test="${not empty session.location && session.location != 'null'}">
			    					${session.location}
			    				</c:when>
			    				<c:otherwise>
			    					TDB
			    				</c:otherwise>
			    			</c:choose>
		    			</p>
		    			
		    			<%-- <p class="wrap">Description: ${session.description}</p> --%>
		    		</a>
		    	</kme:listItem>            
		    </c:forEach>
		</kme:listView>
	</kme:content>
</kme:page>
