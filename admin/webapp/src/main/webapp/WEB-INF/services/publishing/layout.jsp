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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="layout.title" var="title"/>
<kme:page title="${title}" id="layout" backButton="true" homeButton="true">
	<kme:content>
		<kme:listView id="layoutlist" dataTheme="c" dataDividerTheme="b" filter="false">
			<kme:listItem>
				<h3 class="wrap">
					<spring:message code="layout.new.title"/>
				</h3>
				<p class="wrap">
					<a href="${pageContext.request.contextPath}/publishing/layout/new"><spring:message code="common.new"/></a>
				</p>
			</kme:listItem>
			<c:forEach items="${layouts}" var="layout" varStatus="status">
				<kme:listItem>
					<h3 class="wrap">
						${layout.title}
					</h3>
					<p class="wrap">
						<a href="${pageContext.request.contextPath}/publishing/layout/edit/${layout.homeScreenId}"><spring:message code="common.edit"/></a> <a href="${pageContext.request.contextPath}/publishing/layout/delete/${layout.homeScreenId}"><spring:message code="common.delete"/></a>
					</p>
				</kme:listItem>
			</c:forEach>
		</kme:listView>
	</kme:content>
</kme:page>