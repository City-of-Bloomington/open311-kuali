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

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility"%>

<kme:page title="${stop.name}" id="bus-webapp" backButton="true" homeButton="true" cssFilename="bus" jsFilename="bus">
	<kme:content>
		<kme:listView id="busStop" dataTheme="c" dataDividerTheme="b" filter="false">
			<c:forEach items="${stop.schedule}" var="bus">
				<kme:listItem>
					<fmt:formatNumber value="${bus.key / 60}" maxFractionDigits="0" /> min -
					<c:out value="${bus.value}" />
				</kme:listItem>
			</c:forEach>
		</kme:listView>

	</kme:content>
</kme:page>
