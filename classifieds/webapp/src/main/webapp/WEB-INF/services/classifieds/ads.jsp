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
	<c:when test="${selectedTab eq 'tab2' or not empty searched}">
		<c:set var="jsFilename" value="classifieds-show-tab2" />
	</c:when>
	<c:otherwise>
		<c:set var="jsFilename" value="classifieds-show-tab1" />
	</c:otherwise>
</c:choose>
<c:choose>
	<c:when test="${not empty searched}">
		<c:url var="backURL" value="/classifieds?selectedTab=tab2" />
	</c:when>
	<c:otherwise>
		<c:url var="backURL" value="/classifieds" />
	</c:otherwise>
</c:choose>

<kme:page title="${campus} Classifieds" id="classifieds" cssFilename="classifieds" backButton="true" homeButton="true" jsFilename="${jsFilename}" backButtonURL="${backURL}">
	<kme:content>
		<div class="tabs-tabcontainer">
			<c:choose>
				<c:when test="${not empty searched}">
					<a style="width: 24.5%;" class="tabs-tab1" name="tabs-tab1" href="${pageContext.request.contextPath}/classifieds">Browse</a>
					<a style="width: 24.5%;" class="tabs-tab2" name="tabs-tab2" href="#">Search</a>
				</c:when>
				<c:otherwise>
					<a style="width: 24.5%;" class="tabs-tab1" name="tabs-tab1" href="#">Browse</a>
					<a style="width: 24.5%;" class="tabs-tab2" name="tabs-tab2" href="${pageContext.request.contextPath}/classifieds?selectedTab=tab2">Search</a>
				</c:otherwise>
			</c:choose>
			<a style="width:24.5%;" class="tabs-tab3" name="tabs-tab3" href="${pageContext.request.contextPath}/classifieds/myAds">Maintain Ads</a>
			<a style="width: 24.5%;" class="tabs-tab4" name="tabs-tab4" href="${pageContext.request.contextPath}/classifieds/options">Options</a>
		</div>
		<div class="genericpanel">
			<h3 class="title">
				<c:choose>
					<c:when test="${not empty searched}">
	            Search Results: <c:out value="${searched}" />
					</c:when>
					<c:when test="${not empty categoryId and categoryId > 0}">
						<c:out value="${categoryName}" />
					</c:when>
					<c:otherwise>
                All Ads
            </c:otherwise>
				</c:choose>
			</h3>

			<kme:listView id="ads" dataTheme="g">
				<c:forEach var="ad" items="${ads}">
					<kme:listItem>
						<c:url var="adURL" value="/classifieds/ad">
							<c:param name="adId" value="${ad.adId}" />
							<c:param name="categoryId" value="${categoryId}" />
							<c:param name="searched" value="${searched}" />
							<c:param name="pageNumber" value="${pageNumber}" />
						</c:url>
						<a href="${adURL}"> <c:out value="${ad.title}" /> </a>
					</kme:listItem>
				</c:forEach>
			</kme:listView>

			<div class="pagination container_12">
				<div class="grid_12" align="center">
					Page
					<c:if test="${not empty pageNumber}">${pageNumber} of ${maxPageNumber}</c:if>
					<c:if test="${empty pageNumber}">1 of ${maxPageNumber}</c:if>
				</div>

				<div class="prev grid_6">
					<c:if test="${not empty previousPage}">
						<c:url var="adsURL" value="/classifieds/ads">
							<c:param name="pageNumber" value="${previousPage}" />
							<c:param name="categoryId" value="${categoryId}" />
							<c:param name="searched" value="${searched}" />
						</c:url>
						<a href="${adsURL}" data-role="button" data-icon="arrow-l" data-theme="c" data-iconpos="">prev</a>
					</c:if>
				</div>
				<div class="prev grid_6">
					<c:if test="${not empty nextPage}">
						<c:url var="adsURL" value="/classifieds/ads">
							<c:param name="pageNumber" value="${nextPage}" />
							<c:param name="categoryId" value="${categoryId}" />
							<c:param name="searched" value="${searched}" />
						</c:url>
						<a href="${adsURL}" data-role="button" data-icon="arrow-r" data-theme="c" data-iconpos="right">next</a>
					</c:if>
				</div>
			</div>
			<c:if test="${empty ads}">
				<div align="center">There are currently no ads to display.</div>
			</c:if>
		</div>
	</kme:content>
</kme:page>

