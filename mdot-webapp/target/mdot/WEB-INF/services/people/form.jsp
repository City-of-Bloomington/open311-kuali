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

<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme"  uri="http://kuali.org/mobility" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<kme:page title="People" id="people" backButton="true" homeButton="true" cssFilename="people" backButtonURL="${pageContext.request.contextPath}/home" appcacheFilename="iumobile.appcache">
	<kme:content>
		<form:form action="${pageContext.request.contextPath}/people" commandName="search" data-ajax="false" method="post">
			<div data-role="fieldcontain">
				<label for="lastName">Last Name:</label>
                <form:input path="lastName" type="text" value="" />
                <div class="error"><form:errors path="lastName"/></div>
                
                <div id="peopleSlider">
                <fieldset data-theme="c" data-role="controlgroup" data-type="horizontal" >
	                <label for="slider"></label>
	                <form:select data-theme="c" path="exactness" id="slider" data-role="slider">
						<form:option data-theme="c" value="starts" label="starts with" />
				        <form:option data-theme="c" value="exact" label="is exactly" />
	                </form:select>
                </fieldset>
                </div>
                
				<label for="firstName">First Name:</label>
	            <form:input path="firstName" type="text" value=""  />
			</div>
			
			<div data-role="fieldcontain">
                <form:select data-theme="c" path="status" multiple="false" items="${statusTypes}" />
                <form:select data-theme="c" path="location" multiple="false" items="${locations}" />
			</div> 
               
			<div data-role="fieldcontain">
                <label for="userName">User Name:</label>
                <form:input path="userName" type="text" value="" />
			</div>
            
			<div data-inline="true">
                <div class="ui-grid-a">
                    <div class="ui-block-a"><a data-theme="c"  href="${pageContext.request.contextPath}" data-role="button">Cancel</a></div>
                    <div class="ui-block-b">
                        <input data-theme="a" class="submit" type="submit" value="Search" />
                    </div>
                </div>
            </div>
        </form:form>
	</kme:content>
</kme:page>
