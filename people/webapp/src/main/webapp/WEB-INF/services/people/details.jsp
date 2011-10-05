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

<%@ taglib prefix="c"    uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme"  uri="http://kuali.org/mobility" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<kme:page title="Search Results" id="people" backButton="true" homeButton="true" cssFilename="people">
	<kme:content>
		<kme:listView id="detailsList" filter="false" dataTheme="c" dataInset="false">
			<script type="text/javascript">			
				$('[data-role=page][id=people]').live('pagebeforeshow', function(event, ui) {
					$('#detailsTemplate').template('detailsTemplate');
					refreshTemplate('${pageContext.request.contextPath}/people/details', '#detailsList', 'detailsTemplate', '<li>The person was not found.</li>', function() {$('#detailsList').listview('refresh');});
				});
			</script>
			<script id="detailsTemplate" type="text/x-jquery-tmpl">			
				{{if person}}
					<li data-role="list-divider">\${person.lastName}, \${person.firstName}</li>
      				<li>
						{{if person.locations && person.locations.length > 0}}
							<h3 class="wrap">Campus:
						      	<span style="font-weight:normal;">
									{{each(i,location) person.locations}}
										\${location}{{if i+1 < person.locations.length}}, {{/if}}
									{{/each}}
								</span>
							</h3>
						{{/if}}
						{{if person.departments && person.departments.length > 0}}
							<h3 class="wrap">Department:
						      	<span style="font-weight:normal;">
									{{each(i,department) person.departments}}
										\${department}{{if i+1 < person.departments.length}}, {{/if}}
									{{/each}}
								</span>
							</h3>
						{{/if}}
						{{if person.affiliations && person.affiliations.length > 0}}
							<h3 class="wrap">Affiliation:
						      	<span style="font-weight:normal;">
									{{each(i,affiliation) person.affiliations}}
										\${affiliation}{{if i+1 < person.affiliations.length}}, {{/if}}
									{{/each}}									
								</span>
							</h3>
						{{/if}}
						{{if person.address}}
							<h3 class="wrap">Address: <span style="font-weight:normal;">\${person.address}</span></h3>
						{{/if}}
					</li>
					{{if person.email}}
						<li class="link-email">
							<a href="mailto:\${person.email}" >\${person.email}</a>
						</li>
					{{/if}}
					
					{{if person.phone}}
						<li class="link-phone"><a href="tel:\${person.phone}">\${person.phone}</a></li>
					{{/if}}				
				{{else}}
					<li>The person was not found.</li>
				{{/if}}
			</script>
		</kme:listView>
		
		<%-- 
			{{if person.email}}
				<li class="link-email">
					{{if loggedIn}}
						<a href="mailto:\${person.email}" >\${person.email}</a>
					{{else}}
						<img src="${pageContext.request.contextPath}/people/image/\${imageKey}" alt="email" />
					{{/if}}
				</li>
			{{/if}}
		--%>
		
		<%-- <c:choose>
			<c:when test="${person != null}">
				<kme:listView id="peopleList" filter="false" dataTheme="c" dataInset="false">
					<li data-role="list-divider"><c:out value="${person.lastName}" />, <c:out value="${person.firstName}" /></li>
					<li>
						<c:if test="${not empty person.locations}" >
							<h3 class="wrap">Campus:
						      		<span style="font-weight:normal;"><c:forEach items="${person.locations}" var="location" varStatus="status">
						      			<c:out value="${location}" /><c:if test="${not status.last}">, </c:if>
						      		</c:forEach></span>
							</h3>
						</c:if>
						<c:if test="${not empty person.departments}" >
							<h3 class="wrap">Department:
						      		<span style="font-weight:normal;"><c:forEach items="${person.departments}" var="department" varStatus="status">
						      			<c:out value="${department}" /><c:if test="${not status.last}">, </c:if>
						      		</c:forEach></span>
							</h3>
						</c:if>
						<c:if test="${not empty person.affiliations}" >
							<h3 class="wrap">Affiliation:
						      		<span style="font-weight:normal;"><c:forEach items="${person.affiliations}" var="affiliation" varStatus="status">
						      			<c:out value="${affiliation}" /><c:if test="${not status.last}">, </c:if>
						      		</c:forEach></span>
							</h3>
						</c:if>
						<c:if test="${not empty person.address}" >
							<h3 class="wrap">Address: <span style="font-weight:normal;"><c:out value="${person.address}" /></span></h3>
						</c:if>
					</li>
					<c:if test="${not empty person.email}" >
						<li class="link-email">
							<c:choose>
								<c:when test="${loggedIn == true}">
									<c:set var="email"><c:out value="${person.email}" /></c:set>
									<a href="mailto:${email}" ><c:out value="${email}" /></a>
								</c:when>
								<c:otherwise>
									<img src="${pageContext.request.contextPath}/people/image/${imageKey}" alt="email" />
								</c:otherwise>
							</c:choose>
						</li>
					</c:if>
					
					<c:if test="${not empty person.phone}" >
						<c:set var="phone"><c:out value="${person.phone}" /></c:set>
						<li class="link-phone"><a href="tel:${phone}"><c:out value="${phone}" /></a></li>
					</c:if>
				</kme:listView>
			</c:when>
			<c:otherwise>
				The person was not found.
			</c:otherwise>
		</c:choose>--%>
	</kme:content>
</kme:page>
