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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:message code="news.noarticles" var="noarticles"/>

<kme:page title="${feed.title}" id="news" homeButton="true" backButton="true" cssFilename="news">
    <kme:content>
		<ul data-role="listview" data-theme="c" class="news-index">
			<c:choose>
				<c:when test="${not empty feed.articles}">
					<c:forEach items="${feed.articles}" var="article" varStatus="status">
						<li>
							<a href="${pageContext.request.contextPath}/news/${feed.sourceId}?articleId=${article.articleId}&referrer=stream">
								<p class="news-title">${article.title}</p>
							</a>
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<kme:listItem>${noarticles}</kme:listItem>
				</c:otherwise>
			</c:choose>	
		</ul>
	</kme:content>
</kme:page>