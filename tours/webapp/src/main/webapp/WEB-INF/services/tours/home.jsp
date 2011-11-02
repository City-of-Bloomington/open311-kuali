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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>

<kme:page title="Tours" id="tours" backButton="true" homeButton="true" cssFilename="tours">
    <kme:content>
        <kme:listView filter="false">
            <c:forEach items="${tours}" var="tour" varStatus="status">
                <kme:listItem>
                	<a href="${pageContext.request.contextPath}/tours/view/${tour.tourId}">
                		<h3>${tour.name}</h3>
                		<p class="wrap">${tour.description}</p>
               	 	</a>
                </kme:listItem>
            </c:forEach>
        </kme:listView>
    </kme:content>
</kme:page>
