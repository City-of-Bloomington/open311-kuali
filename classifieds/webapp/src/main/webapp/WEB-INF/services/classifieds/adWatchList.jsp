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

<kme:page title="${campus} Classifieds" id="classifieds" cssFilename="classifieds" backButton="true" homeButton="true" jsFilename="classifieds-show-tab4" backButtonURL="${pageContext.request.contextPath}/home">
	<kme:content>
	
	
	 <div class="tabs-tabcontainer">
      <div class="container_12">
        <div class="grid_3"> <a style="width:100%; " class="tabs-tab1" name="tabs-tab1" href="${pageContext.request.contextPath}/classifieds">Browse</a> </div>
        <div class="grid_3"><a style="width:100%; " class="tabs-tab2" name="tabs-tab2" href="${pageContext.request.contextPath}/classifieds?selectedTab=tab2">Search</a></div>
        <div class="grid_3"> <a style="width:100%; " class="tabs-tab3" name="tabs-tab3" href="${pageContext.request.contextPath}/classifieds/myAds">My Ads</a></div>
        <div class="grid_3"> <a style="width:100%; " class="tabs-tab4 selected" name="tabs-tab4" href="#">Watching</a></div>
      </div>
    </div>
	
	
	

		
		    <div style=" margin-top:36px">
		<h3>Watch List</h3>
		<c:choose>
			<c:when test="${not empty adWatchList}">
				<kme:listView id="adWatchList" dataTheme="g">
					<c:forEach var="ad" items="${adWatchList}">
						<kme:listItem>
							<c:url var="adURL" value="/classifieds/ad">
								<c:param name="adId" value="${ad.adId}" />
								<c:param name="watch" value="y" />
							</c:url>
							<a href="${adURL}"> <c:out value="${ad.title}" /> </a>
						</kme:listItem>
					</c:forEach>
				</kme:listView>
			</c:when>
			<c:otherwise>There are no ads in your watch list.</c:otherwise>
		</c:choose>
		</div>
	</kme:content>
</kme:page>


