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

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="kme" uri="http://kuali.org/mobility" %>

<kme:page title="Statewide IT 2011" id="conference" backButton="true" homeButton="true">
	<kme:content>
		<kme:listView>
			<kme:listItem>
	    		<a href="conference/welcome">
	    			<h3 class="wrap">
	    				Welcome
	    			</h3>
	    			<p>A message from Brad Wheeler</p>
	    		</a>
	    	</kme:listItem>
			<kme:listItem>
	    		<a href="conference/sessions">
	    			<h3 class="wrap">
	    				Sessions
	    			</h3>
	    			<p>Keynotes and breakout sessions</p>
	    		</a>
	    	</kme:listItem>  
	    	<kme:listItem>
	    		<a href="conference/attendees">
	    			<h3 class="wrap">
	    				Attendee List
	    			</h3>
	    			<p>Who is attending Statewide IT</p>
	    		</a>
	    	</kme:listItem>
		</kme:listView>
	</kme:content>
</kme:page>
