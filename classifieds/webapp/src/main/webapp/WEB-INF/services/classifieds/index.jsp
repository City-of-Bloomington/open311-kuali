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

<c:choose>
	<c:when test="${selectedTab eq 'tab2'}">
		<c:set var="jsFilename" value="show-tab2" />
	</c:when>
	<c:otherwise>
		<c:set var="jsFilename" value="show-tab1" />
	</c:otherwise>
</c:choose>

<kme:page title="${campus} Classifieds" id="classifieds" cssFilename="classifieds" backButton="true" homeButton="true" jsFilename="${jsFilename}" backButtonURL="${pageContext.request.contextPath}/home">
	<kme:content>
		<div class="tabs-tabcontainer container_12">
			<div class="grid_6">
				<a class="tabs-tab1" name="tabs-tab1" href="#">Browse</a>
			</div>
			<div class="grid_6">
				<a class="tabs-tab2" name="tabs-tab2" href="#">Search</a>
			</div>
		</div>
		<div class="tabs-panel1" name="tabs-panel1">
			<kme:listView id="ad" dataTheme="g">
				<kme:listItem>
					<a href="${pageContext.request.contextPath}/classifieds/ads"> View All Ads</a>
				</kme:listItem>
				<c:forEach var="category" items="${categories}">
					<kme:listItem>
						<c:url var="categoryURL" value="/classifieds/ads">
							<c:param name="categoryId" value="${category.categoryId}" />
						</c:url>
						<a href="${categoryURL}"> <c:out value="${category.title}" /> </a>
					</kme:listItem>
				</c:forEach>
			</kme:listView>
		</div>

		<div class="tabs-panel2" name="tabs-panel2">
			<form:form method="post" action="${pageContext.request.contextPath}/classifieds/search" commandName="search" id="form">
				<label for="name">Search for:</label>
				<form:input path="text" />
				<form:errors path="text" cssClass="error" />
				<input type="submit" value="search" />
			</form:form>
		</div>
	</kme:content>
</kme:page>
</body>
</html>