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
<spring:message code="news.fullarticle" var="fullarticle"/>

<kme:page title="${feedTitle}" id="news" homeButton="true" backButton="true" cssFilename="news">
    <kme:content cssClass="news-story">
		<h3>${article.title}</h3>
		<h4>${article.publishDateDisplay}</h4>
		<p>
			<%-- <c:if test="${!empty topArticle.thumbnailImageUrl}">
				<div class="image-caption">
			    	<img src="${newsArticle.thumbnailImageUrl}" alt="pic">
			    	<p><!-- Caption--!></p>
			   	</div>
		   	</c:if>--%>
			${article.description}
		</p>
		<p><a href="${article.link}">${fullarticle}</a></p>
	</kme:content>
</kme:page>