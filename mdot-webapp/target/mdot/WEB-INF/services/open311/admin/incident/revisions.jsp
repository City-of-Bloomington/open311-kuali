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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<kme:page title="Open311 Admin" id="open311" backButton="true" homeButton="true" cssFilename="open311">
    <kme:content>
        <kme:listView id="submissionList" filter="false">
        	<kme:listItem dataRole="list-divider">
        		Revisions
        	</kme:listItem>
            <c:forEach items="${submissions}" var="submission" varStatus="status">
                <kme:listItem>
                	<a href="../details/${submission.id}">
                		<h3>Revision:${submission.revisionNumber} - ${submission.id}</h3>
                    	<p>${submission.type} - <fmt:formatDate value="${submission.archivedDate}" type="both" /></p>
               	 	</a>
                </kme:listItem>
            </c:forEach>
        </kme:listView>    
	</kme:content>
</kme:page>
