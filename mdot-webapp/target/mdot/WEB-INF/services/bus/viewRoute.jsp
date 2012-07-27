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

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>

<kme:page title="${route.name}" id="bus-webapp" backButton="true" homeButton="true" cssFilename="bus" jsFilename="bus">
	<kme:content>
		<kme:listView id="busRoute" dataTheme="c" dataDividerTheme="b" filter="false">
			<c:forEach items="${route.stops}" var="stop">
				<kme:listItem>
					<c:url var="url" value="/bus/viewStop">
						<c:param name="stopId" value="${stop.id}"></c:param>
						<c:param name="campus" value="${campus}"></c:param>
					</c:url>
					<a href="${url}">
						<h3>
							<c:out value="${stop.name}" />
						</h3></a>
				</kme:listItem>
			</c:forEach>
		</kme:listView>

	</kme:content>
</kme:page>
