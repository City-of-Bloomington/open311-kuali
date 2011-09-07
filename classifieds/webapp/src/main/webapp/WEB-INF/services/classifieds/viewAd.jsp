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

<c:url var="backURL" value="ads">
	<c:param name="categoryId" value="${categoryId}" />
	<c:param name="searched" value="${searched}" />
	<c:param name="pageNumber" value="${pageNumber}" />
</c:url>

<kme:page title="Ad" id="Classified-ad" backButton="true" homeButton="true" cssFilename="classifieds" backButtonURL="${backURL}">
	<kme:content>
		<ul data-role="listview" data-theme="g">
			<li>
				<h3>Title:</h3>
				<p class="wrap">
					<c:out value="${ad.title}" />
				</p>
			</li>
			<li>
				<h3>Price:</h3>
				<p class="wrap">
					<c:choose>
						<c:when test="${not empty ad.price}">
							<c:out value="${ad.price}" />
						</c:when>
						<c:otherwise> --- </c:otherwise>
					</c:choose>
				</p></li>
			<li>
				<h3>Description:</h3>
				<p class="wrap">
					<c:out value="${ad.description}" escapeXml="false" />
				</p>
			</li>
			<li>
				<h3>Contact:</h3>
				<p class="wrap">
					<c:choose>
						<c:when test="${not empty ad.contact}">
							<c:out value="${ad.contact}" />
						</c:when>
						<c:otherwise> --- </c:otherwise>
					</c:choose>
				</p></li>
			<li>
				<h3>Category:</h3>
				<p class="wrap">
					<c:choose>
						<c:when test="${not empty ad.categoryName}">
							<c:out value="${ad.categoryName}" />
						</c:when>
						<c:otherwise> --- </c:otherwise>
					</c:choose>
				</p></li>
			<li>
				<h3>Email:</h3>
				<p class="wrap">
					<c:choose>
						<c:when test="${not empty ad.email}">
							<c:out value="${ad.email}" />
						</c:when>
						<c:otherwise> --- </c:otherwise>
					</c:choose>
				</p>
			</li>
			<li>
				<h3>Posted:</h3>
				<p class="wrap">
					<c:out value="${ad.postDate}" />
				</p></li>
		</ul>
	</kme:content>
</kme:page>