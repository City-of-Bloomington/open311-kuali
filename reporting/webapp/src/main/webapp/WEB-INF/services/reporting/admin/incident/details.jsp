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

<kme:page title="Reporting Admin" id="reporting" backButton="true" homeButton="true" cssFilename="reporting">
    <kme:content>
        <kme:listView id="submissionDetails" filter="false">
        	<kme:listItem dataRole="list-divider">
        		Base Details
        	</kme:listItem>
        	<kme:listItem>
        		<kme:labeledRow fieldLabel="ID" fieldValue="${submission.id}" />        		
        	</kme:listItem>
        	<kme:listItem>
        		<kme:labeledRow fieldLabel="Post Date" fieldValue="${submission.postDate}" />
        	</kme:listItem>
        	<kme:listItem>
        		<kme:labeledRow fieldLabel="Submission Type" fieldValue="${submission.type}" />
        	</kme:listItem>
        	<kme:listItem dataRole="list-divider">
        		Summary
        	</kme:listItem>
        	<kme:listItem>
        		${summary}
        	</kme:listItem>
        	<c:if test="${affiliations}">
	        	<kme:listItem dataRole="list-divider">
	        		Affiliations
	        	</kme:listItem>
	        	<c:if test="${not empty affiliationStudent}">
	        		<kme:listItem>
	        			${affiliationStudent}
	        		</kme:listItem>
	        	</c:if>
	        	<c:if test="${not empty affiliationFaculty}">
	        		<kme:listItem>
	        			${affiliationFaculty}
	        		</kme:listItem>
	        	</c:if>
	        	<c:if test="${not empty affiliationStaff}">
	        		<kme:listItem>
	        			${affiliationStaff}
	        		</kme:listItem>
	        	</c:if>
	        	<c:if test="${not empty affiliationOther}">
	        		<kme:listItem>
	        			${affiliationOther}
	        		</kme:listItem>
	        	</c:if>
	        </c:if>
        	<kme:listItem dataRole="list-divider">
        		User Details
        	</kme:listItem>
        	<kme:listItem>
        		<kme:labeledRow fieldLabel="Email" fieldValue="${email}" />
        	</kme:listItem>
        	<kme:listItem>
        		<kme:labeledRow fieldLabel="Contact Me" fieldValue="${contactMeText}" />
        	</kme:listItem>
        	<kme:listItem>
        		<kme:labeledRow fieldLabel="IP Address" fieldValue="${submission.ipAddress}" />
        	</kme:listItem>
        	<kme:listItem>
        		<kme:labeledRow fieldLabel="User Agent" fieldValue="${submission.userAgent}" />		
        	</kme:listItem>
        	<kme:listItem>
        		<kme:labeledRow fieldLabel="Network ID" fieldValue="${submission.userId}" />		
        	</kme:listItem>
        	<kme:listItem dataRole="list-divider">
        		Revision Details
        	</kme:listItem>
        	<kme:listItem>
        		<kme:labeledRow fieldLabel="Revision Number" fieldValue="${submission.revisionNumber}" />
        	</kme:listItem>
        	<kme:listItem>
        		<kme:labeledRow fieldLabel="Revision User" fieldValue="${submission.revisionUserId}" />
        	</kme:listItem>
        	<kme:listItem>
        		<kme:labeledRow fieldLabel="Active" fieldValue="${activeText}" />
        	</kme:listItem>
        	<c:if test="${activeText eq 'No'}">
	        	<kme:listItem>
	        		<kme:labeledRow fieldLabel="Archived Date" fieldValue="${submission.archivedDate}" />
	        	</kme:listItem>
        	</c:if>
        	<kme:listItem dataRole="list-divider">
        		Comments
        	</kme:listItem>
        	<c:if test="${empty comments}">
        		<kme:listItem>No commemnts</kme:listItem>
        	</c:if>
        	<c:forEach items="${comments}" var="comment" varStatus="status">
	        	<kme:listItem>
					${comment.valueLargeText}
				</kme:listItem>        	
        	</c:forEach>
        	<kme:listItem dataRole="list-divider">
        		Attachments
        	</kme:listItem>
        	<c:if test="${empty attachments}">
        		<kme:listItem>No attachments</kme:listItem>
        	</c:if>
        	<c:forEach items="${attachments}" var="attachment" varStatus="status">
	        	<kme:listItem>
					${attachment.fileName}
				</kme:listItem>        	
        	</c:forEach>
        	<c:if test="${submission.active eq 1}">
        	<kme:listItem dataRole="list-divider">
        		Administrative Functions
        	</kme:listItem>
        	<kme:listItem>
        		<a href="../edit/${submission.id}">Edit</a>
        	</kme:listItem>
        	<kme:listItem>
        		<a href="../revisions/${submission.id}">Revision History</a>
        	</kme:listItem>
        	</c:if>
        </kme:listView>    
	</kme:content>
</kme:page>
