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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<spring:message code="feedback.title" 	var="title"/>
<spring:message code="feedback.subject" var="subject"/>
<spring:message code="feedback.select" var="select"/>
<spring:message code="feedback.general" var="general"/>
<spring:message code="feedback.bus" var="bus"/>
<spring:message code="feedback.alerts" var="alerts"/>
<spring:message code="feedback.maps" var="maps"/>
<spring:message code="feedback.news" var="news"/>
<spring:message code="feedback.emergency" var="emergency"/>
<spring:message code="feedback.events" var="events"/>
<spring:message code="feedback.notices" var="notices"/>
<spring:message code="feedback.oncourse" var="oncourse"/>
<spring:message code="feedback.people" var="people"/>
<spring:message code="feedback.searchiu" var="search"/>
<spring:message code="feedback.computerlabs" var="labs"/>
<spring:message code="feedback.devicetype" var="devicetype"/>
<spring:message code="feedback.message" var="message"/>
<spring:message code="feedback.youremail" var="youremail"/>
<spring:message code="feedback.cancel" var="cancel"/>
<spring:message code="feedback.submit" var="submit"/>
<spring:message code="feedback.greeting" var="greeting"/>

<c:if test="${fn:contains(header['User-Agent'],'iPhone') || fn:contains(header['User-Agent'],'iPad') || fn:contains(header['User-Agent'],'iPod') || fn:contains(header['User-Agent'],'Macintosh')}">
	<c:set var="platform" value="iOS"/>
</c:if>
<c:if test="${fn:contains(header['User-Agent'],'Android')}">
	<c:set var="platform" value="Android"/>
</c:if>

<c:set var="phonegap" value="${cookie.phonegap.value}"/>

<kme:page title="${title}" id="feedback_page" backButton="true" homeButton="true" cssFilename="feedback" appcacheFilename="iumobile.appcache" platform="${platform}" phonegap="${phonegap}" onBodyLoad="onBodyLoad()">

<script type="text/javascript">

    var IsiPhone 		= navigator.userAgent.indexOf("iPhone") != -1 ;
    var IsiPod 			= navigator.userAgent.indexOf("iPod") != -1 ;
    var IsiPad 			= navigator.userAgent.indexOf("iPad") != -1 ;
    var IsAndroid 		= navigator.userAgent.indexOf("Android") != -1 ;
    var IsBlackberry 	= navigator.userAgent.indexOf("Blackberry") != -1 ;
    var IsMac 			= navigator.userAgent.indexOf("Macintosh") != -1 ;
    var IsWindows 		= navigator.userAgent.indexOf("Windows") != -1 ;
	var IsDesktop		= IsMac || IsWindows;
	var IsTablet 		= ""; // Resolution based?
	var IsWindowsMobile = navigator.userAgent.indexOf("IEMobile") != -1 ;
    var IsOpera 		= navigator.userAgent.indexOf("Opera") != -1 ;
    var IsOther 		= (navigator.userAgent.indexOf("Symbian") != -1) || (navigator.userAgent.indexOf("Nokia") != -1) || (navigator.userAgent.indexOf("webOS") != -1) ;
    var which = "";

    
    // Only an other smart phone if it is running opera, or on a Symbian, WebOS or Nokia Phone, and is not overruled by any of the following conditions. 
    // Nothing defaults to "other" device, perhaps that should be the "value" of the "Select a Device" option.  
    
    // Probably over did this, in that I made it perhaps entirely too thorough for our current needs. -Mitch
    
    if(IsOpera || IsOther){
        which = "sp";		
    }
    // Checks if it is one of the iOS devices. 
    if(IsiPhone || IsiPad || IsiPod){
        which = "ip";
		// Also Change Visible text in the Select Options to reflect actual device. (It's the little things...)
        if(IsiPad){
            $(function(){    			
                $("#deviceType option[value=" + which + "]").text('iPad');        
            });	
        }
        if(IsiPod){
            $(function(){    			
                $("#deviceType option[value=" + which + "]").text('iPod');        
            });	
        }
    }
    // This will mark a phone as Android, even if the user is running Opera. 
    if(IsAndroid){
        which = "an";
    }
    // Guess what device this script doesn't work on?! Not in Simulator anyway. 
    if(IsBlackberry){
        which = "bb";
    }
	// This is only coded from spec/doc. Not tested on device. 
    if(IsWindowsMobile){
        which = "ie";
    }
	// Doesn't test for Linux, as most android devices show up as Linux in UA Strings. 
	if(IsDesktop){
        which = "computer";
    }    			
    // Must call after DOM is ready. 
    // This is shortcut for $(document).ready(...);
    $(function(){    			
        $("#deviceType option[value=" + which + "]").attr('selected', 'selected');        
    });
        
</script>  

    <kme:content>
        <form:form action="${pageContext.request.contextPath}/feedback" commandName="feedback" data-ajax="false" method="post"> ${greeting}
            <%--hidden fields: <form:hidden path="eventId"/>--%>


			<%--
				This hidden field submits the entire userAgent string along with their feedback. 
				There were already feilds/columns in Feedback.java for storing userAgent, But I didn't
				see where it was being submitted or saved. 
				
				TODO: Check with legal regarding if this is ok regarding the Privacy Policy.
			 --%>
			<form:hidden path="userAgent" value="${header['User-Agent']}"/>
			
            <fieldset>
                <div data-role="fieldcontain">
                    <label for="service" class="select">${subject}:</label>
                    <form:select data-theme="c" path="service">
                        <%--<option value="N/A" selected="selected"> Select type:</option> --%>
                        <form:option value="General Feedback" selected="selected">${general}</form:option>
                        <form:option value="Bus Schedules">${bus}s</form:option>
                        <form:option value="Campus Alerts">${alerts}</form:option>
                        <form:option value="Campus Maps">${maps}</form:option>
                        <form:option value="Emergency Contacts">${emergency}</form:option>
                        <form:option value="Events">${events}</form:option>
                        <form:option value="IT Notices">${notices}</form:option>
                        <form:option value="News">${news}</form:option>
                        <form:option value="Oncourse">${oncourse}</form:option>
                        <form:option value="People">${people}</form:option>
                        <form:option value="Search IU">${search}</form:option>
                        <form:option value="Computer Labs">${labs}</form:option>
                    </form:select>
                </div>

                <div data-role="fieldcontain">
                    <label for="deviceType" class="select">${devicetype}:</label>
                    <form:select data-theme="c" path="deviceType" multiple="false" items="${deviceTypes}" class="required"/>
                    <form:errors path="deviceType"/>
                </div> 
                <div data-role="fieldcontain">
                    <label for="noteText">${message}:</label>
                    <form:textarea path="noteText" cols="40" rows="8" class="required" />
                    <form:errors path="noteText"/>
                </div>
                <div data-role="fieldcontain">
                    <label for="email">${youremail}:</label>
                    <form:input path="email" type="text" value="" class="email"  />
                </div>

            </fieldset>
            
            <div data-inline="true">
                <div class="ui-grid-a">
                    <div class="ui-block-a"><a href="${pageContext.request.contextPath}/home" data-theme="c"  data-role="button">${cancel}</a></div>
                    <div class="ui-block-b">
                        <input data-theme="a" class="submit" type="submit" value="${submit}" />
                    </div>
                </div>
            </div>
        </form:form>
    </kme:content>
</kme:page>
