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

<spring:message code="publishing.title" var="title"/>
<kme:page title="${title}" id="publishing" backButton="true" homeButton="true">
	<kme:content>
		<kme:listView id="publishinglist" dataTheme="c" dataDividerTheme="b" filter="false">
			<kme:listItem>
				<a href="tool">
					<h3 class="wrap">
						<spring:message code="tools.title"/>
					</h3>
				</a>
			</kme:listItem>
			<kme:listItem>
				<a href="layout">
					<h3 class="wrap">
						<spring:message code="layout.title"/>
					</h3>
				</a>
			</kme:listItem>
		</kme:listView>
	</kme:content>
</kme:page>