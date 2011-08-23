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

<kme:page title="My Classes" id="myclasses" homeButton="true" backButton="true"  backButtonURL="${pageContext.request.contextPath}/home" cssFilename="sakai">
    <kme:content>
    
	    <style type="text/css">
	        div.ui-body-b { background: none; }
	        div.ui-body-b { background-color: #E6E6E6 !important; }
	        div#classesPanel, div#projectsPanel, div#otherPanel, div#todayPanel { margin-top: -20px; } 
	        h2.todaysDate { text-align: center; }
	        a.arrowButtonLeft, a.arrowButtonRight { width:28px; height:28px; display:block; text-indent:-9999px; background-position:6px 6px; background-repeat:no-repeat; }
	        a.arrowButtonLeft { float:left; background-image:url('images/arrow-left.png'); }
	        a.arrowButtonRight { float:right; background-image:url('images/arrow-right.png'); }
	    </style>
    
        <c:if test="${tabCount gt 1}">
		   
		      <div class="tabs-tabcontainer">
		        <c:if test="${!empty home.courses && home.showTodayTab}">
                    <a style="width:${(100/tabCount)-.6}%"  class="tabs-tab1" name="tabs-tab1" href="#">Today</a>
                </c:if>
			    <c:if test="${!empty home.courses}">
	                <a style="width:${(100/tabCount)-.6}%"  class="tabs-tab2" name="tabs-tab2" href="#">Classes</a>
	            </c:if>
	            <c:if test="${!empty home.projects}">
	                <a style="width:${(100/tabCount)-.6}%"  class="tabs-tab3" name="tabs-tab3" href="#">Projects</a>
	            </c:if>
	            <c:if test="${!empty home.other}">
	                <a style="width:${(100/tabCount)-.6}%"  class="tabs-tab4" name="tabs-tab4" href="#">Other</a>
	            </c:if> 
            </div>
            
            <c:choose>
                <c:when test="${!empty home.courses && home.showTodayTab}">
                    <script type="text/javascript">
                    $(window).load(function () {
                        $('.tabs-tab1').addClass('selected');
                        $('.tabs-panel1').show();
                    });
                    </script>
                </c:when>
                <c:when test="${!empty home.courses}">
                    <script type="text/javascript">
                    $(window).load(function () {
                        $('.tabs-tab2').addClass('selected');
                        $('.tabs-panel2').show();
                    });
                    </script>
                </c:when>
                <c:when test="${!empty home.projects}">
                    <script type="text/javascript">
                    $(window).load(function () {
                        $('.tabs-tab3').addClass('selected');
                        $('.tabs-panel3').show();
                    });
                    </script>
                </c:when>
                <c:otherwise>
                    <script type="text/javascript">
                    $(window).load(function () {
                        $('.tabs-tab4').addClass('selected');
                        $('.tabs-panel4').show();
                    });
                    </script>
                </c:otherwise>
                
            </c:choose>
            
            <style type="text/css">
			    <!--
			    div.tabs-panel1, div.tabs-panel2, div.tabs-panel3, div.tabs-panel4, { margin-top:; }			    
			    -->
		    </style>
        </c:if>
        
        <c:if test="${!empty home.courses}">
	         <div class="tabs-panel1" name="tabs-panel1">
	        	<c:if test="${tabCount eq 1}"><h2>Classes</h2></c:if>
	            <c:forEach items="${home.courses}" var="termItem" varStatus="status">
	                <c:if test="${not empty termItem.term}"><h3>${termItem.term}</h3></c:if>
	                <c:if test="${empty termItem.term}"><h3>No Term</h3></c:if>
	                <kme:listView dataTheme="c">
	                    <c:forEach items="${termItem.courses}" var="item" varStatus="status">
	                        <kme:listItem>
	                            <a href="${pageContext.request.contextPath}/myclasses/${item.id}">
	                                <h3>${item.title}</h3>
	                                <c:if test="${not empty item.description && item.description != 'null'}">
	                                    <p class="wrap">${item.description}</p>
	                                </c:if>
	                            </a>
	                        </kme:listItem>
	                    </c:forEach>
	                </kme:listView>
	            </c:forEach>
	        </div>
        </c:if>
        
        <c:if test="${!empty home.projects}">
            <div class="tabs-panel2" name="tabs-panel2">
            <c:if test="${tabCount eq 1}"><h2>Projects</h2></c:if>
            <kme:listView dataTheme="c">
                <c:forEach items="${home.projects}" var="item" varStatus="status">
                    <kme:listItem>
                        <a href="${pageContext.request.contextPath}/myclasses/${item.id}">
                            <h3>${item.title}</h3>
                            <c:if test="${not empty item.description && item.description != 'null'}">
                                <p class="wrap">${item.description}</p>
                            </c:if>
                        </a>
                    </kme:listItem>
                </c:forEach>
            </kme:listView>
            </div>
        </c:if>
        
             
        <c:if test="${!empty home.other}">
             <div class="tabs-panel3" name="tabs-panel3">
	            <c:if test="${tabCount eq 1}"><h2>Other</h2></c:if>
	            <kme:listView dataTheme="c">
	                <c:forEach items="${home.other}" var="item" varStatus="status">
	                    <kme:listItem>
	                        <a href="${pageContext.request.contextPath}/myclasses/${item.id}">
	                            <h3>${item.title}</h3>
	                            <c:if test="${not empty item.description && item.description != 'null'}">
	                                <p class="wrap">${item.description}</p>
	                            </c:if>
	                        </a>
	                    </kme:listItem>
	                </c:forEach>
	            </kme:listView>
            </div>
        </c:if>
     
        <c:if test="${!empty home.courses && home.showTodayTab}">
             <div class="tabs-panel4" name="tabs-panel4">
                <!-- 
                <div data-inline="true">
                    <div class="ui-grid-a">
                        <div class="ui-block-a"><a data-role="button" data-theme="c" href="${pageContext.request.contextPath}/myclasses?date=${yesterday}">&laquo; ${yesterdayButton}</a></div>
                        <div class="ui-block-b"><a data-role="button" data-theme="c" href="${pageContext.request.contextPath}/myclasses?date=${tomorrow}">${tomorrowButton} &raquo; </a></div>
                    </div>
                </div>
                -->       
                <a class="arrowButtonLeft" href="${pageContext.request.contextPath}/myclasses?date=${yesterday}">${yesterdayButton}</a>
                <a class="arrowButtonRight" href="${pageContext.request.contextPath}/myclasses?date=${tomorrow}">${tomorrowButton}</a>
                <h2 class="todaysDate">${todayDisplay}</h2>
	            <!--<c:if test="${tabCount eq 1}"><h2>Today</h2></c:if>-->
	            <c:if test="${!empty home.todaysCourses}">
	            <kme:listView dataTheme="c">
	                <c:forEach items="${home.todaysCourses}" var="item" varStatus="status">
	                    <kme:listItem>
	                        <a href="${pageContext.request.contextPath}/myclasses/${item.id}">
	                            <h3>${item.title}</h3>
	                            <c:if test="${not empty item.description && item.description != 'null'}">
	                                <p class="wrap">${item.description}</p>
	                            </c:if>
	                        </a>
	                    </kme:listItem>
	                </c:forEach>
	            </kme:listView>
	            </c:if>      
                <c:if test="${empty home.todaysCourses}">
                    <p>No classes today.</p>
                </c:if>
            </div>
        </c:if>  




 
    </kme:content>
</kme:page>