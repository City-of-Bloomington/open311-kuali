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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility"%>
<c:choose>
	<c:when test="${selectedTab eq 'tab3'}">
		<c:url var="backUrl" value="/athletics">
			<c:param name="selectedTab" value="tab3" />
		</c:url>
	</c:when>
	<c:otherwise>
		<c:url var="backUrl" value="/athletics/viewSport">
			<c:param name="sportId" value="${sportId}" />
		</c:url>
	</c:otherwise>
</c:choose>
<kme:page title="News" id="athletics-story" backButton="true" homeButton="true" cssFilename="athletics" backButtonURL="${backUrl}">
	<kme:content>555
		<h3>
			<c:out value="${newsArticle.title}" />
		</h3>
		<div class="focal">
			<c:choose>
				<c:when test="${not empty newsArticle.description}">
					<c:out value="${newsArticle.description}" escapeXml="false" />
				</c:when>
				<c:otherwise>
					This story is not currently available.
				</c:otherwise>
			</c:choose>
		</div>
	</kme:content>
</kme:page>
