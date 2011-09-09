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

<kme:page title="Session Details" id="sessiondetails" homeButton="true" backButton="true" cssFilename="conference">
	<kme:content>
	    <kme:listView id="sessionList" filter="false" dataTheme="c" dataInset="false">
			<kme:listItem dataRole="list-divider">
	        	${session.title}
	        </kme:listItem>
	        <kme:listItem>
	        	<p>
	        	<c:if test="${not empty session.description}" ><h3>About this Session:</h3><p>${session.description}</p></c:if>
	        	</p>
	        </kme:listItem>
	        
	        <kme:listItem dataRole="list-divider">Speakers</kme:listItem>   
	        <c:forEach items="${session.speakers}" var="speaker" varStatus="status">
		    	<kme:listItem>
		    		<a href="sessionSpeakerDetails?id=${status.index}">
		    			<h3 class="wrap">
		    				${speaker.firstName} ${speaker.lastName}
		    			</h3>
		    			<p class="wrap">${speaker.email}</p>
		    		</a>
		    	</kme:listItem>            
		    </c:forEach>
	        
		</kme:listView>
	</kme:content>
</kme:page>