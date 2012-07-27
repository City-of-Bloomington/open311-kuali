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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="campus.title" var="title"/>
<kme:page title="${title}" id="campus" backButton="true" homeButton="true">
	<kme:content>
		<kme:listView id="campuslist" filter="false">
			<c:forEach items="${campuses}" var="campus" varStatus="status">
				<kme:listItem>
					<c:url var="campusUrl" value="/campus/select">
						<c:param name="toolName" value="${toolName}" />
						<c:param name="campus" value="${campus.code}" />
					</c:url>
					<a href="${campusUrl}"> <c:out value="${campus.name}" />
					</a>
				</kme:listItem>
			</c:forEach>
		</kme:listView>
	</kme:content>
</kme:page>


