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
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<kme:page title="${campus} Classifieds" id="classifieds" cssFilename="classifieds" backButton="true" homeButton="true" backButtonURL="${pageContext.request.contextPath}/classifieds/myAds">
	<kme:content>
		<c:out value="${policy}" escapeXml="false" />
		<br />
		<br />
		<c:url var="adURL" value="/classifieds/maintainAd">
			<c:if test="${not empty adId}">
				<c:param name="adId" value="${adId}" />
			</c:if>
		</c:url>
	</kme:content>
		<div data-role="footer" data-id="events-footer" data-position="fixed" role="contentinfo" data-theme="b" class="ui-bar">
			<div data-role="controlgroup" data-type="horizontal">
				<a href="${adURL}" data-role="button" data-ajax="false"> I Agree</a>
			</div>
		</div>
	
</kme:page>
