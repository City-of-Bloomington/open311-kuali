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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<kme:page title="Campus 411" id="campus411" backButton="true" homeButton="true" cssFilename="campus411">
	<kme:content>
		<kme:listView id="campus411listdata" filter="false">
			<c:if test="${not empty title}">
				<kme:listItem>
					Live Operators to help you with phone numbers, building codes, campus directions, and more!
				</kme:listItem>
				<kme:listItem cssClass="link-phone">
					<a href="tel:${phoneNumber}">
						${title}
					</a>
				</kme:listItem>
			</c:if>
			<c:if test="${empty title}">
				<kme:listItem>
					Campus 411 not supported on this campus.
				</kme:listItem>
			</c:if>
		</kme:listView>
	</kme:content>
</kme:page>
