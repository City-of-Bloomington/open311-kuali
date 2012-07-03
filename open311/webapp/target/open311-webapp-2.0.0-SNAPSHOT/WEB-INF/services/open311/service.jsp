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

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<kme:page title="Open311 Service" id="service" backButton="true" homeButton="true">
	<kme:content>
	<ul data-role="listview" id="dropdownMenu" data-theme="c" data-inset="false" data-filter="false" data-dividertheme="b">
        	<c:forEach items="${serviceAttributes}" var="attribute" varStatus="index">
            	<li data-theme="c" class="dropdownItem" value="${index.count}">
				${attribute.variable}&nbsp;&nbsp;
				${attribute.datatype}&nbsp;&nbsp;
				${attribute.description}&nbsp;&nbsp;
				1${attribute.values}&nbsp;&nbsp;
				2${attribute.values[0]}&nbsp;&nbsp;
				3${attribute.values[0].name}&nbsp;&nbsp;
				4${attribute.datatypeDescription}&nbsp;&nbsp;
				</li>
            </c:forEach> 
			
        </ul>

		
	</kme:content>
</kme:page>
