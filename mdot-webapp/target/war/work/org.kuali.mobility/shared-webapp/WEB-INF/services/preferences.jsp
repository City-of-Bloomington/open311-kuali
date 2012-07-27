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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:set var="localeCode" value="${pageContext.response.locale}" />
<c:set var="myCampusCode" value="${cookie.campusSelection.value}" />
<c:set var="isNative" value="${cookie.native.value}" />

<spring:message code="preferences.title" var="title"/>
<spring:message code="preferences.selectcampus" var="selectcampus"/>
<spring:message code="preferences.languages" var="languages"/>
<spring:message code="preferences.nativeapp" var="nativeapp"/>
<spring:message code="preferences.installnativeapp" var="installnative"/>

<kme:page title="${title}" id="preferences" backButton="true" homeButton="true">
	<kme:content>
		<kme:listView id="campuslist" dataTheme="c" dataDividerTheme="b">
			<kme:listItem dataRole="list-divider" dataTheme="b">
				${selectcampus}
			</kme:listItem>
			<c:forEach items="${campuses}" var="campus" varStatus="status">
				<kme:listItem>
					<c:url var="campusUrl" value="/campus/select">
						<c:param name="toolName" value="${toolName}" />
						<c:param name="campus" value="${campus.code}" />
					</c:url>
					<a href="${campusUrl}"> <c:out value="${campus.name}" />
					<c:if test="${campus.code == myCampusCode }">
						 <span class="ui-check-char">✓</span>
					</c:if>					
					</a>
				</kme:listItem>
			</c:forEach>
			<kme:listItem dataRole="list-divider" dataTheme="b">
				${languages}
			</kme:listItem>
			<kme:listItem >
				<a href="?lang=en">
					English
					<c:if test="${localeCode == 'en'}">
						 <span class="ui-check-char">✓</span>
					</c:if>
				</a>
			</kme:listItem>
			<kme:listItem >
				<a href="?lang=zh_CN">
					中文（简体）
					<c:if test="${localeCode == 'zh_CN'}">
						 <span class="ui-check-char">✓	</span>
					</c:if>
				</a>
			</kme:listItem>
			<kme:listItem >
				<a href="?lang=zh_TW">
					中文（繁體）
					<c:if test="${localeCode == 'zh_TW'}">
						 <span class="ui-check-char">✓	</span>
					</c:if>
				</a>
			</kme:listItem>
			<c:if test="${cookie.native.value != 'yes'}">
				<kme:listItem dataRole="list-divider" dataTheme="b" cssClass="installLink">
					${nativeapp}
				</kme:listItem>
				<kme:listItem cssClass="installLink">
					<a id="install" href="#">${installnative}</a> 
					<div class="result"></div>
					<div class="log"></div>
				</kme:listItem>
			<script type="text/javascript">			
					$('#install').click(function(){
						var apps = "";
						$.get("${pageContext.request.contextPath}/testdata/nativeMobileAppURL.json", function(jdata) {	
							apps = jQuery.parseJSON(jdata);
							if (/iPhone|iPad|iPod|Apple/i.test(navigator.userAgent)){
    							window.location = apps.iPhoneURL;
    						}else if (/Blackberry|RIM\sTablet/i.test(navigator.userAgent)){		
    							window.location = apps.BlackberryURL;
    						}else if (/Android/i.test(navigator.userAgent)){
    							window.location = apps.AndroidURL;
	   						}
						});
					});	
			</script>
			</c:if>			
		</kme:listView>
	</kme:content>
</kme:page>


