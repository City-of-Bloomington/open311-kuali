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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<kme:page title="Session Details" id="sessiondetails" homeButton="true" backButton="true" cssFilename="conference">
	<kme:content>
	    <kme:listView id="sessionList" filter="false" dataTheme="c" dataInset="false">
			<kme:listItem dataRole="list-divider">
	        	${session.title}
	        </kme:listItem>
	        
        	<c:if test="${not empty session.description}" >
        		<kme:listItem cssClass="tightPadding">
        			<h3>About this Session:</h3>
        			<p class="wrap tightPadding">${session.description}</p>
        		</kme:listItem>
        	</c:if>

	        <c:if test="${not empty session.latitude}" >
	        	<kme:listItem cssClass="link-gps">
	        		<a href="${pageContext.request.contextPath}/maps/location?latitude=${session.latitude}&longitude=${session.longitude}"><p class="wrap">Location: <strong>${session.location}</strong></p></a>
	        	</kme:listItem>
	        </c:if>
	        
	        
	        <c:if test="${!empty session.speakers}">
		        <kme:listItem dataRole="list-divider">Speakers</kme:listItem>   
		        <c:forEach items="${session.speakers}" var="speaker" varStatus="status">
			    	<kme:listItem>
			    		<%--<a href="sessionSpeakerDetails?id=${status.index}"> --%>
			    			<h3 class="wrap">
			    				${speaker.firstName} ${speaker.lastName}
			    			</h3>
			    			<p class="wrap">${speaker.email}</p>
			    		<%-- </a> --%>
			    	</kme:listItem>            
			    </c:forEach>
		    </c:if>
	        
	        <kme:listItem dataRole="list-divider">Rate this Session:</kme:listItem>
	        <kme:listItem>
        		
        		
        		
        	 	
				<form:form action="${pageContext.request.contextPath}/conference/sessionFeedback" commandName="sessionFeedback" data-ajax="false" method="post">
				
					<%--<div class="ratingStar" id="star1" onclick="this.form.elements[\"rating\"].value = '1';">1</div>
	        		<div class="ratingStar" id="star2" onclick="this.form.elements[\"rating\"].value = '2';">2</div>
	        		<div class="ratingStar" id="star3" onclick="this.form.elements[\"rating\"].value = '3';">3</div>
	        		<div class="ratingStar" id="star4" onclick="this.form.elements[\"rating\"].value = '4';">4</div>
	        		<div class="ratingStar" id="star5" onclick="this.form.elements[\"rating\"].value = '5';">5</div>
		            --%>
		            
		            <fieldset>
			            <form:radiobutton path="rating" value="1" label="1 Star" />
			            <form:radiobutton path="rating" value="2" label="2 Stars" />
			            <form:radiobutton path="rating" value="3" label="3 Stars" />
			            <form:radiobutton path="rating" value="4" label="4 Stars" />
			            <form:radiobutton path="rating" value="5" label="5 Stars" />
		            </fieldset>
		            
		            <%-- <form:hidden path="rating"/>  --%>
		            
		            <fieldset>
		               
		                    <label for="comments">Comments:</label>
		                    <form:textarea path="comments" cols="40" rows="8" />
		               
		            </fieldset>
		            
		            <div data-inline="true">
		                <div class="ui-grid-a">
		                    <div class="ui-block-b">
		                        <input data-theme="a" class="submit" type="submit" value="Submit" />
		                    </div>
		                </div>
		            </div>
		        </form:form>
		        		
        		
        			
        	</kme:listItem>
	        
	        <c:if test="${not empty session.link}" >
	        	<kme:listItem dataRole="list-divider">Full Site URL</kme:listItem>   
	        	<kme:listItem>
	        		<a href="${session.link}"><p>${session.link}</p></a>
	        	</kme:listItem>
	        </c:if>
		</kme:listView>
	</kme:content>
</kme:page>