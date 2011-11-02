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

<kme:page title="${campus} Classifieds" id="classifieds" cssFilename="classifieds" backButton="true" homeButton="true" jsFilename="classifieds-show-tab3" backButtonURL="${pageContext.request.contextPath}/home">
	<kme:content>
	<div class="tabs-tabcontainer">
          <div class="container_12">
            <div class="grid_3"> <a style="width:100%; " class="tabs-tab1" name="tabs-tab1" href="${pageContext.request.contextPath}/classifieds">Browse</a> </div>
            <div class="grid_3"><a style="width:100%; " class="tabs-tab2" name="tabs-tab2" href="${pageContext.request.contextPath}/classifieds?selectedTab=tab2">Search</a> </div>
            <div class="grid_3"> <a style="width:100%; " class="tabs-tab3 selected" name="tabs-tab3"  href="#">My Ads</a></div>
            <div class="grid_3"> <a style="width:100%; " class="tabs-tab4" name="tabs-tab4" href="${pageContext.request.contextPath}/classifieds/adWatchList">Watching</a>
          </div>
        </div>
	</div>
	    <div class="" name="tabs-panel1" style=" margin-top:36px">
     <c:if test="${not empty ads}">
      <h3>My Current Ads</h3>
			<kme:listView id="ads" dataTheme="c">
				<c:forEach var="ad" items="${ads}">
				<li class="link-edit">
						<c:url var="adURL" value="/classifieds/policy">
							<c:param name="adId" value="${ad.adId}" />
						</c:url>
						<a href="${adURL}"> <c:out value="${ad.title}" /> </a>
					</li>
				</c:forEach>
			</kme:listView>
	</c:if>
      <div class="container_12" style="margin-top:26px">
        <div class="prev grid_6"> <a href="${pageContext.request.contextPath}/classifieds/policy" data-role="button" data-icon="plus" data-theme="a">Post Ad</a></div>
        <div class="prev grid_6"> </div>
      </div>
      <p>
      <c:url var="policyUrl" value="/classifieds/policy">
		<c:param name="refer" value="policy" />
	</c:url>
      <a href="${policyUrl}">Classifieds Policy</a></p>		
    </div>
		<div class="genericpanel">
		</div>
	</kme:content>
</kme:page>