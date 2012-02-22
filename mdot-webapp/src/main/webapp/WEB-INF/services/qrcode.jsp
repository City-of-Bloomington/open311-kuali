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

<spring:message code="mdot.name" 			var="mdotName"/>
<spring:message code="qrcode.mdotLink" 		var="mdotLink"/>
<spring:message code="qrcode.email" 		var="email"/>
<spring:message code="qrcode.emailsubject" 	var="emailsubject"/>
<spring:message code="qrcode.url" 			var="url"/>
<spring:message code="qrcode.phonenumber" 	var="phonenumber"/>
<spring:message code="qrcode.scan" 			var="scan"/>
<spring:message code="qrcode.scanningfailed" 		var="scanningfailed"/>
<spring:message code="qrcode.scanner" 		var="scanner"/>
<spring:message code="qrcode.clear" 		var="clear"/>
<spring:message code="qrcode.scanned" 		var="scanned"/>
<spring:message code="qrcode.recipient" 	var="recipient"/>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${fn:contains(header['User-Agent'],'iPhone') || fn:contains(header['User-Agent'],'iPad') || fn:contains(header['User-Agent'],'iPod') }">
	<c:set var="platform" value="iOS"/>
</c:if>
<c:if test="${fn:contains(header['User-Agent'],'Android')  || fn:contains(header['User-Agent'],'Macintosh')}">
	<c:set var="platform" value="Android"/>
</c:if>

<c:set var="phonegap" value="${cookie.phonegap.value}"/>




<kme:page title="${scanner}" id="qrcodes" backButton="true" homeButton="true" cssFilename="alerts" backButtonURL="${pageContext.request.contextPath}/home" platform="${platform}" phonegap="${phonegap}">
			<script type="text/javascript" charset="utf-8">
						
			var IsiPhone 		= navigator.userAgent.indexOf("iPhone") != -1 ;
			var IsiPod 			= navigator.userAgent.indexOf("iPod") != -1 ;
			var IsiPad 			= navigator.userAgent.indexOf("iPad") != -1 ;
		    var IsAndroid 		= navigator.userAgent.indexOf("Android") != -1 ;
			var IsiOS			= IsiPhone || IsiPod || IsiPad;

			var childBrowser;
			var root = this;
			
			// Must call after DOM is ready. 
		    // This is shortcut for $(document).ready(...);
		    $(function(){								
		    	document.addEventListener("deviceready",onDeviceReady,false);	
		    });
				
			function onDeviceReady(){
				//navigator.notification.alert("PhoneGap: onDeviceReady()", function(){}, "Kuali Mobile", 'OK');
				var cb;
				if(IsiOS){
					cb = ChildBrowser.install();
					if(cb != null){
			            cb.onLocationChange = function(loc){ root.locChanged(loc); }; 
		    	        cb.onClose          = function(){root.onCloseBrowser();}; 
		        	    cb.onOpenExternal   = function(){root.onOpenExternal();}; 
					}
				}
			}
		    
		    function displayURL(myurl){
				// Saving past scans would be easy. 
				// + local storage to persist
//				var html = $("#list").html();	
				
				
				var html = "";	
				var url = myurl;
				var title = "";
				var parsedURL = parseURL(url);

				
				if(parsedURL.protocol == "kuali"  || parsedURL.protocol == "kualis"){
					title = "${mdotLink}";
					html += '<li data-theme="c" data-role="list-divider" id="list-divider">${scanned}</li>';
					html += '<li data-theme="c"><a href="#" onclick="safeLink(\''+ url +'\')"><h3 style="white-space:normal">' + title + '</h3><p>' + url + '</p></a></li>';					
					$("#list").html(html).listview("refresh");
					$("[data-role='page']").page('refresh');				
				}
				
				if(parsedURL.protocol == "http" || parsedURL.protocol == "https"){
					/*
					Doesn't work in Android version for some reason. 
					Can Make iPhone version match this. 
					$.get(url, function(response){
						var start = response.toLowerCase().indexOf("<title>");
						var end = response.toLowerCase().indexOf("</title>");
						title = response.substring(start + 7, end);	
					});
					*/
					title = "${url}";
					html += '<li data-theme="c" data-role="list-divider" id="list-divider">${scanned}</li>';
					html += '<li data-theme="c"><a href="#" onclick="onClickLink(\''+ url +'\')"><h3 style="white-space:normal">' + title + '</h3><p>' + url + '</p></a></li>';					
					$("#list").html(html).listview("refresh");
					$("[data-role='page']").page('refresh');
				}
				
				if(parsedURL.protocol == "tel" || myurl.toLowerCase().indexOf("tel:") != -1){
					title = "${phonenumber}";
					url = "tel:" + myurl.substring(4);
					html += '<li data-theme="c" data-role="list-divider" id="list-divider">${scanned}</li>';
					html += '<li data-theme="c" class="link-phone"><a href="'+ url +'"><h3 style="white-space:normal">' + title + '</h3><p>' + url + '</p></a></li>';
					$("#list").html(html).listview("refresh");
				}

				if(parsedURL.protocol == "mailto" || myurl.toLowerCase().indexOf("mailto:") != -1){
					title = "${email}";
					list = url.split("?");
					var subject = "";
					var address = list[0];
					address = address.split(":")[1];
					if(list[1] != undefined){					
						list = list[1].split("&");
						var subject = "<p>${emailsubject}: " + list[0].split("=")[1] + "</p>";
					}
					html += '<li data-theme="c" data-role="list-divider" id="list-divider">${scanned}</li>';
					html += '<li data-theme="c" class="link-email"><a href="'+ url +'"><h3 style="white-space:normal">' + title + '</h3><p>${recipient}: ' + address + '</p>' + subject + '</a></li>';
					$("#list").html(html).listview("refresh");
				}
			}

		    
		    function safeLink(url){
		    	var newurl = "";
				newurl = url.replace("kualis://", "https://");
				newurl = newurl.replace("kuali://", "http://");					
				//alert(newurl);
		    	window.location = newurl;	
		    }
		    
			function onClickLink(url){
				if(IsiOS){
			        try{ 
			            //both of these should work... 
			            window.plugins.childBrowser.showWebPage(url); 
			            //childBrowser.showWebPage(url); 
			        } 
			        catch (err){ 
			            alert(err); 
			        } 
				}else if(IsAndroid){
					// It's not an internal MDOT link, open in Child Browser. 
					window.plugins.childBrowser.showWebPage(url, { showLocationBar: true });
				}
			}
			
			/* This parses the url so we can get the different components.*/    
			function parseURL(url) {
				var a =  document.createElement('a');
				a.href = url;
				return {
					source: url,
					protocol: a.protocol.replace(':',''),
					host: a.hostname,
					port: a.port,
					query: a.search,
					params: (function(){
							 var ret = {},
							 seg = a.search.replace(/^\?/,'').split('&'),
							 len = seg.length, i = 0, s;
							 for (;i<len;i++) {
							 if (!seg[i]) { continue; }
							 s = seg[i].split('=');
							 ret[s[0]] = s[1];
							 }
							 return ret;
							 })(),
					file: (a.pathname.match(/\/([^\/?#]+)$/i) || [,''])[1],
					hash: a.hash.replace('#',''),
					path: a.pathname.replace(/^([^\/])/,'/$1'),
					relative: (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [,''])[1],
					segments: a.pathname.replace(/^\//,'').split('/')
				};
			}

			
			function clearList(){
				console.log("clearList");

				// Testing the statusBarNotification here. 
				//window.plugins.statusBarNotification.notify('Kuali Mobile', "Cleared Scan List!");
				var html = '<li data-theme="c" data-role="list-divider" id="list-divider">${scanned}</li>';
				$("#list").html(html).listview("refresh");
			}
			
			function scan(){
				window.plugins.barcodeScanner.scan( function(result) {
			        if(result.cancelled){
						// Used this to test when device isn't available. 
						// GUI works, just no scanning. Can use to test ChildBrowser. 
						
			        	//displayURL("http://m.digg.com");
			        }else{
						displayURL(result.text);           
			        }
			    }, function(error) {
			    	navigator.notification.alert("${scanningfailed}: " + error, function(){}, "${mdotName}", 'OK');
//			        alert("Scanning failed: " + error);
			    });
			}

		    $(function(){    			
				//scan();
		    });
		    
			</script>
	<kme:content>
	    <kme:listView id="list" filter="false">
			<li data-theme="c" data-role="list-divider" id="list-divider">${scanned}</li>		
	    </kme:listView>
	    <br>
<fieldset class="ui-grid-a">
	<div class="ui-block-a"><button onclick="scan();" id="scanbutton" data-theme="c">${scan}</button></div> 	
	<div class="ui-block-b"><button onclick="clearList();" id="closebutton" data-theme="c">${clear}</button></div> 
</fieldset>
	</kme:content>
</kme:page>
