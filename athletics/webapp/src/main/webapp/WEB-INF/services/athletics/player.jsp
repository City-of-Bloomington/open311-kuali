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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility"%>

<kme:page title="Player" id="athletics-roster" backButton="true" homeButton="true" cssFilename="athletics">
	<kme:content>
		  <ul data-role="listview" data-theme="c"  data-dividertheme="b"  data-inset="">
			<li>
				<h3 class="playername wrap">
					<c:if test="${not empty player.number}">
						<c:out value="${player.number}" escapeXml="true" />
						<c:if test="${not empty player.name}"> - </c:if>
					</c:if>
					<c:if test="${not empty player.name}">
						<c:out value="${player.name}" escapeXml="true" />
					</c:if>
				</h3>
				<div class="contentwrapper">
					<div class="contentcolumn">
						<c:if test="${not empty player.position}">
							<p class="wrap">
							<span class="roster-label">Position: </span>
							<c:out value="${player.position}" escapeXml="true" />
							</p>
						</c:if>
						<c:if test="${not empty player.classStanding}">
							<p class="wrap">
							<span class="roster-label">Year: </span>
							<c:out value="${player.classStanding}" escapeXml="true" />
							</p>
						</c:if>
						<c:if test="${not empty player.height}">
							<p class="wrap">
							<span class="roster-label">Height: </span>
							<c:out value="${player.height}" escapeXml="true" />
							</p>
						</c:if>
						<c:if test="${not empty player.weight}">
							<p class="wrap">
							<span class="roster-label">Weight: </span>
							<c:out value="${player.weight}" escapeXml="true" />
							</p>
						</c:if>
						<c:if test="${not empty player.homeCity}">
							<p class="wrap">
							<span class="roster-label">City: </span>
							<c:out value="${player.homeCity}" escapeXml="true" />
							</p>
						</c:if>
						<c:if test="${not empty player.homeState}">
							<p class="wrap">
							<span class="roster-label">State: </span>
							<c:out value="${player.homeState}" escapeXml="true" />
							</p>
						</c:if>
						<c:if test="${not empty player.highSchool}">
							<p class="wrap">
							<span class="roster-label">High School: </span>
							<c:out value="${player.highSchool}" escapeXml="true" />
							</p>
						</c:if>
					</div>
				</div>
				<div class="leftcolumn">
					<img class="rowicon-player" src="<c:out value="${player.thumbnailMedium}" escapeXml="true" />" />
				</div>
				<div style="clear: both"></div>
			</li>
		</ul>
	</kme:content>
</kme:page>
