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
<%@ taglib prefix="kme" uri="http://kuali.org/mobility"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<spring:message code="editlayout.title" var="title"/>
<kme:page title="${title}" id="editlayout" backButton="true" homeButton="true" cssFilename="publishing">
	<kme:content>
		<fieldset>
			<form:form action="${pageContext.request.contextPath}/publishing/layout/edit" commandName="layout" data-ajax="false" method="post" id="fm" name="fm">
				<form:hidden path="homeScreenId"/>
				<form:hidden path="versionNumber"/>
				<c:forEach items="${layout.homeTools}" var="homeTool" varStatus="status">
					<form:hidden path="homeTools[${status.index}].toolId"/>
					<form:hidden path="homeTools[${status.index}].tool.toolId"/>
					<form:hidden path="homeTools[${status.index}].tool.title"/>
					<form:hidden path="homeTools[${status.index}].tool.url"/>
					<form:hidden path="homeTools[${status.index}].tool.description"/>
					<form:hidden path="homeTools[${status.index}].tool.iconUrl"/>
					<form:hidden path="homeTools[${status.index}].tool.versionNumber"/>
					<form:hidden path="homeTools[${status.index}].homeScreenId"/>
					<form:hidden path="homeTools[${status.index}].order"/>
					<form:hidden path="homeTools[${status.index}].versionNumber"/>
				</c:forEach>
				<label for="alias"><spring:message code="editlayout.layout.alias"/>:</label>
				<form:input path="alias" />
				<form:errors path="alias" />
				<label for="title"><spring:message code="editlayout.layout.title"/>:</label>
				<form:input path="title" />
				<form:errors path="title" />
				
				<div data-role="fieldcontain">
					<label><spring:message code="editlayout.addtools"/></label><br/>
					<select data-theme="c" id="toolToAdd" name="toolToAdd">
					   	<c:forEach items="${availableTools}" var="tool" varStatus="status">
							<option value="${tool.toolId}"><c:out value="${tool.title}"/></option>
						</c:forEach>
					</select>
					<input name="add" value="<spring:message code="common.add"/>" type="submit" alt="add tool"/>
				</div>
				

				<label><spring:message code="editlayout.selectedtools"/></label>
				<ul data-role='listview'>
					<input type="hidden" id="removeId" name="removeId" value="${homeTool.toolId}" />
					<c:forEach items="${layout.homeTools}" var="homeTool" varStatus="status">
						<li>
							<h3 style="display:inline-block;" class="wrap"><c:out value="${homeTool.tool.title}"/></h3>
							<div data-role="controlgroup" data-type="horizontal">
								<input name="up" type="submit" data-icon="arrow-u" data-role="button" onclick="javascript:document.forms['fm'].elements['removeId'].value = '${homeTool.toolId}';" />
								<input name="down" type="submit" data-icon="arrow-d" data-role="button" onclick="javascript:document.forms['fm'].elements['removeId'].value = '${homeTool.toolId}';" />
								<input name="remove" type="submit" data-icon="delete" data-role="button" onclick="javascript:document.forms['fm'].elements['removeId'].value = '${homeTool.toolId}';" />
							</div>
						</li>
					</c:forEach>
				</ul>

				<br />
				<div data-inline="true">
                	<div class="ui-grid-a">
                    	<div class="ui-block-a">
                    		<input data-theme="a" class="submit" type="submit" id="saveButton" value="Save" />
                    	</div>
                    	<div class="ui-block-b">
                    		<a data-theme="c"  href="${pageContext.request.contextPath}/publishing/tool" data-role="button">Cancel</a>
                    	</div>
                	</div>
            	</div>
			</form:form>
		</fieldset>
	</kme:content>
</kme:page>