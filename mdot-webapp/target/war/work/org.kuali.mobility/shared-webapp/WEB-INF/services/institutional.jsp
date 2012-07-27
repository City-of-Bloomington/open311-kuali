/*
  Copyright 2011 The Kuali Foundation Licensed under the Educational Community
  License, Version 2.0 (the "License"); you may not use this file except in
  compliance with the License. You may obtain a copy of the License at
  http://www.osedu.org/licenses/ECL-2.0 Unless required by applicable law or
  agreed to in writing, software distributed under the License is distributed
  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
  express or implied. See the License for the specific language governing
  permissions and limitations under the License.
*/
<%@ page contentType="text/css" %>
<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>


<%-- Page --%>

<c:set var="pageBackgroundColor" value="${styles['page.background.color']}" /> 

<%-- Header --%>
<c:set var="headerTextColor" value="${styles['header.text.color']}" />
<c:set var="headerGradientStartColor" value="${styles['header.gradient.start.color']}" /> 
<c:set var="headerGradientStopColor" value="${styles['header.gradient.stop.color']}" />
<c:set var="headerLogoUrl" value="${styles['header.logo.url']}" /> 

<%-- Buttons --%>
<%-- Button A - Header and Submit buttons --%>
<c:set var="buttonATextColor" value="${styles['buttonA.text.color']}" />
<c:set var="buttonAGradientStartColor" value="${styles['buttonA.gradient.start.color']}" />
<c:set var="buttonAGradientStopColor" value="${styles['buttonA.gradient.stop.color']}" />

<c:set var="buttonAHoverGradientStartColor" value="${styles['buttonA.hover.gradient.start.color']}" /> 
<c:set var="buttonAHoverGradientStopColor" value="${styles['buttonA.hover.gradient.stop.color']}" />

<c:set var="buttonADownGradientStartColor" value="${styles['buttonA.down.gradient.start.color']}" /> 
<c:set var="buttonADownGradientStopColor" value="${styles['buttonA.down.gradient.stop.color']}" />

<%-- Button C - Other buttons --%>
<c:set var="buttonCTextColor" value="${styles['buttonC.text.color']}" />
<c:set var="buttonCGradientStartColor" value="${styles['buttonC.gradient.start.color']}" /> 
<c:set var="buttonCGradientStopColor" value="${styles['buttonC.gradient.stop.color']}" />

<c:set var="buttonCHoverGradientStartColor" value="${styles['buttonC.hover.gradient.start.color']}" /> 
<c:set var="buttonCHoverGradientStopColor" value="${styles['buttonC.hover.gradient.stop.color']}" />

<c:set var="buttonCDownGradientStartColor" value="${styles['buttonC.down.gradient.start.color']}" /> 
<c:set var="buttonCDownGradientStopColor" value="${styles['buttonC.down.gradient.stop.color']}" />


<%-- some manipulations
If no stop color is defined for the gradients, set it to the start color.
 --%>

<c:if test="${empty headerGradientStopColor}">
	<c:set var="headerGradientStopColor"><c:out value="${headerGradientStartColor}" /></c:set>
</c:if>

<c:if test="${empty buttonAGradientStopColor}">
	<c:set var="buttonAGradientStopColor"><c:out value="${buttonAGradientStartColor}" /></c:set>
</c:if>
<c:if test="${empty buttonAHoverGradientStopColor}">
	<c:set var="buttonAHoverGradientStopColor"><c:out value="${buttonAHoverGradientStartColor}" /></c:set>
</c:if>
<c:if test="${empty buttonADownGradientStopColor}">
	<c:set var="buttonADownGradientStopColor"><c:out value="${buttonADownGradientStartColor}" /></c:set>
</c:if>

<c:if test="${empty buttonCGradientStopColor}">
	<c:set var="buttonCGradientStopColor"><c:out value="${buttonCGradientStartColor}" /></c:set>
</c:if>
<c:if test="${empty buttonCHoverGradientStopColor}">
	<c:set var="buttonCHoverGradientStopColor"><c:out value="${buttonCHoverGradientStartColor}" /></c:set>
</c:if>
<c:if test="${empty buttonCDownGradientStopColor}">
	<c:set var="buttonCDownGradientStopColor"><c:out value="${buttonCDownGradientStartColor}" /></c:set>
</c:if>

div[data-role="page"], div.ui-body-b {
	background-color:	<c:out value="${pageBackgroundColor}" />;
	background-image: -moz-linear-gradient(top, <c:out value="${pageBackgroundColor}" />, <c:out value="${pageBackgroundColor}" />);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, <c:out value="${pageBackgroundColor}" />), color-stop(1, <c:out value="${pageBackgroundColor}" />));
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr='<c:out value="${pageBackgroundColor}" />', EndColorStr='<c:out value="${pageBackgroundColor}" />')";
}

.ui-bar-a {
	color:				<c:out value="${headerTextColor}" />;
	background-color:	<c:out value="${headerGradientStartColor}" />;
	background-image: -moz-linear-gradient(top, <c:out value="${headerGradientStartColor}" />, <c:out value="${headerGradientStopColor}" />);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, <c:out value="${headerGradientStartColor}" />), color-stop(1, <c:out value="${headerGradientStopColor}" />));
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr='<c:out value="${headerGradientStartColor}" />', EndColorStr='<c:out value="${headerGradientStopColor}" />')";
}

<c:if test="${not empty headerLogoUrl}">
div[data-role="header"] h1 {
   background-image:url('<c:out value="${headerLogoUrl}" />');
   background-repeat:no-repeat;
}
</c:if>

.ui-btn-up-a {
	background: 		<c:out value="${buttonAGradientStartColor}" />;
	color:				<c:out value="${buttonATextColor}" />;
	background-image: -moz-linear-gradient(top, <c:out value="${buttonAGradientStartColor}" />, <c:out value="${buttonAGradientStopColor}" />);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, <c:out value="${buttonAGradientStartColor}" />), color-stop(1, <c:out value="${buttonAGradientStopColor}" />));
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr='<c:out value="${buttonAGradientStartColor}" />', EndColorStr='<c:out value="${buttonAGradientStopColor}" />')";
}

.ui-btn-hover-a {
	background: 		<c:out value="${buttonAHoverGradientStartColor}" />;
	color:				<c:out value="${buttonATextColor}" />;
	background-image: -moz-linear-gradient(top, <c:out value="${buttonAHoverGradientStartColor}" />, <c:out value="${buttonAHoverGradientStopColor}" />);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, <c:out value="${buttonAHoverGradientStartColor}" />), color-stop(1, <c:out value="${buttonAHoverGradientStopColor}" />));
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr='<c:out value="${buttonAHoverGradientStartColor}" />', EndColorStr='<c:out value="${buttonAHoverGradientStopColor}" />')";
}

.ui-btn-down-a {
	background: 		<c:out value="${buttonADownGradientStartColor}" />;
	color:				<c:out value="${buttonATextColor}" />;
	background-image: -moz-linear-gradient(top, <c:out value="${buttonADownGradientStartColor}" />, <c:out value="${buttonADownGradientStopColor}" />);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, <c:out value="${buttonADownGradientStartColor}" />), color-stop(1, <c:out value="${buttonADownGradientStopColor}" />));
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr='<c:out value="${buttonADownGradientStartColor}" />', EndColorStr='<c:out value="${buttonADownGradientStopColor}" />')";
}

.ui-btn-up-c {
	background: 		<c:out value="${buttonCGradientStartColor}" />;
	color:				<c:out value="${buttonCTextColor}" />;
	background-image: -moz-linear-gradient(top, <c:out value="${buttonCGradientStartColor}" />, <c:out value="${buttonCGradientStopColor}" />);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, <c:out value="${buttonCGradientStartColor}" />), color-stop(1, <c:out value="${buttonCGradientStopColor}" />));
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr='<c:out value="${buttonCGradientStartColor}" />', EndColorStr='<c:out value="${buttonCGradientStopColor}" />')";
}

.ui-btn-hover-c {
	background: 		<c:out value="${buttonCHoverGradientStartColor}" />;
	color:				<c:out value="${buttonCTextColor}" />;
	background-image: -moz-linear-gradient(top, <c:out value="${buttonCHoverGradientStartColor}" />, <c:out value="${buttonCHoverGradientStopColor}" />);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, <c:out value="${buttonCHoverGradientStartColor}" />), color-stop(1, <c:out value="${buttonCHoverGradientStopColor}" />));
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr='<c:out value="${buttonCHoverGradientStartColor}" />', EndColorStr='<c:out value="${buttonCHoverGradientStopColor}" />')";
}

.ui-btn-down-c {
	background: 		<c:out value="${buttonCDownGradientStartColor}" />;
	color:				<c:out value="${buttonCTextColor}" />;
	background-image: -moz-linear-gradient(top, <c:out value="${buttonCDownGradientStartColor}" />, <c:out value="${buttonCDownGradientStopColor}" />);
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, <c:out value="${buttonCDownGradientStartColor}" />), color-stop(1, <c:out value="${buttonCDownGradientStopColor}" />));
	-ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr='<c:out value="${buttonCDownGradientStartColor}" />', EndColorStr='<c:out value="${buttonCDownGradientStopColor}" />')";
}