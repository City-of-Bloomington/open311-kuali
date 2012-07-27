<%--
  Copyright 2011-2012 The Kuali Foundation Licensed under the Educational Community
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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:message code="news.title" var="title"/>
<spring:message code="news.expand" var="expand"/>
<spring:message code="news.collapse" var="collapse"/>

<kme:page title="${title}" id="news" homeButton="true" backButton="true" backButtonURL="${pageContext.request.contextPath}/home" cssFilename="news" jsFilename="news">
    <kme:content>
		<!-- <ul data-role="listview" data-theme="c" class="news-index"> -->
		<kme:listView dataTheme="c" cssClass="news-index">
			<c:forEach items="${newsSources}" var="source" varStatus="status">
			
				<!-- <li data-role="" class="" data-theme="b" data-icon="listview" > -->
				<kme:listItem dataRole="list-divider" dataTheme="b" dataIcon="listview" cssClass="streamTitle">
					<a href="${pageContext.request.contextPath}/news/${source.id}">${source.title}</a>
				</kme:listItem>
				<!-- </li> --> 
				
				<c:forEach items="${source.articles}" var="article" varStatus="status">
					<c:choose>
						<c:when test="${status.index < sampleSize}">
							<kme:listItem cssClass="sample">
								<a href="${pageContext.request.contextPath}/news/${source.sourceId}?articleId=${article.articleId}&referrer=home">
					        		<!-- <p class="news-title">${article.title}</p>-->
					        		<p class="wrap">${article.title}</p>
					        	</a>
					        </kme:listItem>
						</c:when>
						<c:otherwise>
							<kme:listItem cssClass="extra feed${source.id}">
								<a href="${pageContext.request.contextPath}/news/${source.id}?articleId=${article.articleId}&referrer=home">
					        		<!-- <p class="news-title">${article.title}</p>-->
					        		<p class="wrap">${article.title}</p>
					        	</a>
					        </kme:listItem>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${fn:length(source.articles) > sampleSize && sampleSize > 0}">
					<kme:listItem cssClass="expander feed${source.id} collapsed" dataIcon="arrow-d">
						<!-- These added to support passing of i18n words -->
						<a onclick="javascript:toggleVisibility('feed${source.id}', '${expand}', '${collapse}')">
			        		<p><strong>${expand}</strong></p>
			        	</a>
					</kme:listItem>
				</c:if>
			</c:forEach>
		</kme:listView>
	</kme:content>
</kme:page>