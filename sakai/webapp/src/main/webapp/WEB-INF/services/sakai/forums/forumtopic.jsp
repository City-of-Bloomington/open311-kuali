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
	
<kme:page title="${topic.title}" id="forum" cssFilename="sakai" backButton="true" homeButton="true" backButtonURL="${pageContext.request.contextPath}/myclasses/${siteId}/forums/${topic.forumId}">
	<kme:content>
		<ul data-role="listview">
			<c:if test="${not empty topic.description}">
				<kme:listItem>
	            	${topic.description}
				</kme:listItem>
			</c:if>
			<c:if test="${not empty topic.attachments}">
				<kme:listItem dataTheme="b" dataRole="list-divider">Attachments</kme:listItem>
				<c:forEach items="${topic.attachments}" var="attachment" varStatus="status">
					<kme:listItem cssClass="link-view">
                        <a href="${pageContext.request.contextPath}/myclasses/${siteId}/attachment?attachmentId=${attachment.url}&type=${attachment.mimeType}" class="attachment icon-${attachment.fileType}" >
							${attachment.title}
						</a>
                    </kme:listItem>
				</c:forEach>
			</c:if>
			<kme:listItem dataTheme="b" dataRole="list-divider">Threads</kme:listItem>
			<c:choose>
				<c:when test="${not empty topic.threads}">
					<c:forEach items="${topic.threads}" var="thread" varStatus="status">
						<li>
							<a href="${pageContext.request.contextPath}/myclasses/${siteId}/forums/${topic.forumId}/${topic.id}/${thread.id}?topicTitle=${topic.title}">
								${thread.title}
								<c:if test="${thread.unreadCount > 0}">
									<span class="ui-li-count">${thread.unreadCount}</span>
								</c:if>
							</a>
						</li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<li>
						No threads
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</kme:content>
</kme:page>