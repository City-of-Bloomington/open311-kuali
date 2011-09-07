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

<kme:page title="${campus} Classifieds" id="classifieds" cssFilename="classifieds" backButton="true" homeButton="true" backButtonURL="${pageContext.request.contextPath}/classifieds/options">
	<kme:content>
		<c:choose>
			<c:when test="${not empty adWatchList}">
				<kme:listView id="adWatchList" dataTheme="g">
					<c:forEach var="ad" items="${adWatchList}">
						<kme:listItem>
							<c:url var="adURL" value="/classifieds/watch">
								<c:param name="adId" value="${ad.adId}" />
							</c:url>
							<%-- <a href="${adURL}"> <c:out value="${ad.title}" /> </a>--%><c:out value="${ad.title}" />
						</kme:listItem>
					</c:forEach>
				</kme:listView>
			</c:when>
			<c:otherwise>There are no ads in your watch list.</c:otherwise>
		</c:choose>
	</kme:content>
</kme:page>

