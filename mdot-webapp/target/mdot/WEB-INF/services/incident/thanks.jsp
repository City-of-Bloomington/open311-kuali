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

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<kme:page title="Thank you" id="incident" backButton="true" homeButton="true" cssFilename="incident">
	<kme:content>
		<%-- <h3>Thank you for reporting this incident</h3> --%>
    	<kme:listView>
		    <kme:listItem cssClass="link-phone">
			    <a href="tel:911">
			    	<h3>Call (812) 855-5711 (Option 1)</h3>
			    	<p class="wrap">If you need immediate support.</p>
			    </a>
		    </kme:listItem>
		</kme:listView>
	</kme:content>
</kme:page>
