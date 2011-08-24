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

<kme:page title="IUB Campus Bus" id="bus" backButton="true" homeButton="true" cssFilename="bus">
	<kme:content>
		<kme:listView>
			<kme:listItem><a class="icon-PDF" href="http://iubus/campus_bus/schedules/Fall%202011/A%20Fall%202011%20Mon-Thur%20box%20copy.pdf">A Route Mon-Thu</a></kme:listItem>
       		<kme:listItem><a class="icon-PDF" href="http://iubus/campus_bus/schedules/Fall%202011/A%20Fall%202011%20Fri-Sun%20box%20copy.pdf">A Route Fri-Sun</a></kme:listItem>
        	<kme:listItem><a class="icon-PDF" href="http://iubus/campus_bus/schedules/Fall%202011/B%20Fall%202011%20box%20copy.pdf">B Route</a></kme:listItem>
        	<kme:listItem><a class="icon-PDF" href="http://iubus/campus_bus/schedules/Fall%202011/D%20Fall%202011%20box%20copy.pdf">D Route</a></kme:listItem>
        	<kme:listItem><a class="icon-PDF" href="http://iubus/campus_bus/schedules/Fall%202011/E%20Fall%202011%20box%20copy.pdf">E Route</a></kme:listItem>
        	<kme:listItem><a class="icon-PDF" href="http://iubus/campus_bus/schedules/Fall%202011/X%20Fall%202011%20box%20copy.pdf">X Route</a></kme:listItem>	
		</kme:listView>
	</kme:content>
</kme:page>