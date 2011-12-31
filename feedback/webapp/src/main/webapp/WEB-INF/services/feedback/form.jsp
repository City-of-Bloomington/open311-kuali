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


<kme:page title="${title}" id="feedback_page" backButton="true" homeButton="true" cssFilename="feedback">
    <kme:content>
        <form:form action="${pageContext.request.contextPath}/feedback" commandName="feedback" data-ajax="false" method="post"> ${greeting}
            <%--hidden fields: <form:hidden path="eventId"/>--%>
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
