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

<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme"  uri="http://kuali.org/mobility" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="logout.title" var="title"/>
<spring:message code="logout.areyousure" var="areyousure"/>
<spring:message code="logout.cancel" var="cancel"/>
<spring:message code="logout.yes" var="yes"/>

<kme:page title="${logout}" id="logout" backButton="true" homeButton="true" backButtonURL="home">
	<kme:content>
		<p>${areyousure}</p>
        <div data-inline="true">
	        <div class="ui-grid-a">
	          <div class="ui-block-a"><a href="${pageContext.request.contextPath}/home" data-theme="c" data-role="button">${cancel}</a></div>
	          <div class="ui-block-b"><a href="${pageContext.request.contextPath}/yesLogout" data-theme="a"  data-role="button">${yes}</a></div>
	        </div>
        </div>
	</kme:content>
</kme:page>
