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
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<kme:page title="Campus Maps" id="maps" backButton="true" homeButton="true">
	<kme:content>
		<form:form action="${pageContext.request.contextPath}/maps/building/search" commandName="mapsearchform" data-ajax="false">
			<fieldset>
            <label for="searchText">Search</label>
			<form:input path="searchText" cssClass="text ui-widget-content ui-corner-all" />
			<form:errors path="searchText" />
			<label for="searchCampus">Campus</label>
			<form:select path="searchCampus" multiple="false">
				<form:option value="BL" label="Bloomington" data-placeholder="true"/>
				<form:option value="IN" label="Indianapolis" data-placeholder="true"/>
			</form:select>
			</fieldset>
		</form:form>
	    <kme:definitionListView id="mapsearchresults">
			<div id="searchresults">
	        <c:forEach items="${container.results}" var="item" varStatus="status">
	            <kme:definitionListTerm>
					<a href="${pageContext.request.contextPath}/maps/building/${item.code}">${item.name}</a>
	            </kme:definitionListTerm>
	        </c:forEach>
	        </div>
	    </kme:definitionListView>
	</kme:content>
</kme:page>
