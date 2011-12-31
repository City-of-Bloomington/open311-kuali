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
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:message code="emergencyinfo.title" var="title"/>
<spring:message code="emergencyinfo.true" var="trueemer"/>
<spring:message code="emergencyinfo.phonenumbers" var="phone"/>

<kme:page title="${title}" id="emergencyinfo" backButton="true" homeButton="true" cssFilename="emergencyinfo">
    <kme:content>
        <kme:listView id="emergencylist" filter="false">
        	<kme:listItem cssClass="link-phone">
	        	<a href="tel:911">
	        		<h3>${trueemer}: <span style="color:red;">911</span></h3>
	        	</a>
        	</kme:listItem>
        	<kme:listItem dataTheme="b" dataRole="list-divider">${phone}</kme:listItem>
        </kme:listView>
        <kme:listView id="emergencylistdata" filter="false">
            <script type="text/javascript">
				$('[data-role=page][id=emergencyinfo]').live('pagebeforeshow', function(event, ui) {
					$('#emergencyListTemplate').template('emergencyListTemplate');
					refreshTemplate('emergencycontacts', '#emergencylistdata', 'emergencyListTemplate', '<li>No Available Contacts</li>', function() {$('#emergencylistdata').listview('refresh');});
				});
			</script>
			<script id="emergencyListTemplate" type="text/x-jquery-tmpl">
				<li class="link-phone">
					<a href="tel:\${link}">
                		<h3>\${title}</h3>
                    	<p>\${link}</p>
               	 	</a>
				</li>
			</script>
            <%--
            <c:forEach items="${emergencyinfos}" var="emergencyinfo" varStatus="status">
                <kme:listItem cssClass="link-phone">
                	<a href="tel:${emergencyinfo.link}">
                		<h3>${emergencyinfo.title}</h3>
                    	<p>${emergencyinfo.link}</p>
               	 	</a>
                </kme:listItem>
            </c:forEach>
            --%>
        </kme:listView>
    </kme:content>
</kme:page>
