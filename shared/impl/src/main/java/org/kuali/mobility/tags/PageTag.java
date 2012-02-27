/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.kuali.mobility.tags;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.kuali.mobility.security.authn.entity.User;
import org.kuali.mobility.security.authn.util.AuthenticationConstants;
import org.kuali.mobility.security.authn.util.AuthenticationMapper;
import org.kuali.mobility.shared.CoreService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * The backing class for the Page JSP tag.  Renders everything necessary for the page excluding the actual content.
 * 
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 *
 */
public class PageTag extends SimpleTagSupport {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PageTag.class);

    private AuthenticationMapper authMapper;
    
    private String id;
    private String title;
    private boolean homeButton;
    private boolean backButton;
    private String backButtonURL;
    private boolean preferencesButton;
    private String preferencesButtonURL;
    private boolean usesGoogleMaps;
    private String appcacheFilename;
	private String cssFilename;
	private String onBodyLoad;
	private String platform;
	private String phonegap;
	private String jsFilename;
	private String mapLocale;
    private boolean loginButton;
    private String loginButtonURL;
	private String logoutButtonURL;
	private boolean disableGoogleAnalytics;
	private String institutionCss;

	/**
	 * @param id the id of the div containing the page content
	 */
    public void setId(String id) {
        this.id = id;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }    

    public void setPhonegap(String phonegap) {
        this.phonegap = phonegap;
    }  
    
    /**
     * @param title the title that will appear in the the header bar
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param locale that will be used. 
     */
    public void setMapLocale(String mapLocale) {
        this.mapLocale = mapLocale;
    }    
    
    public void setOnBodyLoad(String onBodyLoad) {
        this.onBodyLoad = onBodyLoad;
    }
    
    /**
     * Enable the home button
     * @param homeButton
     */
    public void setHomeButton(boolean homeButton) {
        this.homeButton = homeButton;
    }
    
    /**
     * enable the back button
     * @param backButton
     */
    public void setBackButton(boolean backButton) {
        this.backButton = backButton;
    }
    
    /**
     * set the URL that the back button points to.  If null, the back button uses the browser history.
     * @param backButtonURL
     */
    public void setBackButtonURL(String backButtonURL) {
        this.backButtonURL = backButtonURL;
    }
    
    /**
     * enable a preferences button
     * @param preferencesButton
     */
    public void setPreferencesButton(boolean preferencesButton) {
		this.preferencesButton = preferencesButton;
	}

    /**
     * the URL for preferences.  If null, the context path + "/preferences" will be used.
     * @param preferencesButtonURL
     */
	public void setPreferencesButtonURL(String preferencesButtonURL) {
		this.preferencesButtonURL = preferencesButtonURL;
	}
	
	/**
	 * select whether to include the Google Maps API v3 javascript
	 * @param usesGoogleMaps
	 */
	public void setUsesGoogleMaps(boolean usesGoogleMaps) {
		this.usesGoogleMaps = usesGoogleMaps;
	}
	
	/**
	 * Enables the HTML5 app cache and specifies the manifest file name.
	 * @param appcacheFilename
	 */
    public void setAppcacheFilename(String appcacheFilename) {
    	this.appcacheFilename = appcacheFilename;
    }
	
    /**
     * the name of the css file without the .css extension
     * @param cssFilename
     */
	public void setCssFilename(String cssFilename) {
		this.cssFilename = cssFilename;
	}
	
	/**
	 * the name of the javascript file without the .js extension
	 * @param jsFilename
	 */
    public void setJsFilename(String jsFilename) {
        this.jsFilename = jsFilename;
    }

    /**
     * enable a login/logout button
     * @param loginButton
     */
	public void setLoginButton(boolean loginButton) {
		this.loginButton = loginButton;
	}

	/**
     * the URL to log in.  If null, the context path + "/login" will be used.
     * @param loginButtonURL
     */
	public void setLoginButtonURL(String loginButtonURL) {
		this.loginButtonURL = loginButtonURL;
	}

	/**
     * the URL to log out.  If null, the context path + "/logout" will be used.
     * @param logoutButtonURL
     */
	public void setLogoutButtonURL(String logoutButtonURL) {
		this.logoutButtonURL = logoutButtonURL;
	}

	public void doTag() throws JspException {
        PageContext pageContext = (PageContext) getJspContext();
        ServletContext servletContext = pageContext.getServletContext();
		WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		setAuthMapper( (AuthenticationMapper)ac.getBean("authenticationMapper") );
		CoreService coreService = (CoreService) ac.getBean("coreService");
        User user = (User) pageContext.getSession().getAttribute(AuthenticationConstants.KME_USER_KEY);
        String contextPath = servletContext.getContextPath();
        JspWriter out = pageContext.getOut();
        try {
            out.println("<!DOCTYPE html>");

            if (appcacheFilename != null && !appcacheFilename.trim().equals("")) {
            	out.println("<html manifest=\"" + contextPath + "/" + appcacheFilename + "\">");
            } else {
            	out.println("<html>");
            }
            
            out.println("<head>");
            out.println("<title>" + title + "</title>");
            out.println("<link href=\"http://www.kuali.org/favicon.ico\" rel=\"icon\" />");
            out.println("<link href=\"http://www.kuali.org/favicon.ico\" rel=\"shortcut icon\" />");
            out.println("<link rel=\"apple-touch-icon\" href=\"" + contextPath + "/apple-touch-icon.png\"/>");
            out.println("<link href=\"" + contextPath + "/css/jquery.mobile.css\" rel=\"stylesheet\" type=\"text/css\" />");
            out.println("<link href=\"" + contextPath + "/css/jquery-mobile-fluid960.css\" rel=\"stylesheet\" type=\"text/css\" />");
            out.println("<link href=\"" + contextPath + "/css/kme.css\" rel=\"stylesheet\" type=\"text/css\" />");
            out.println("<link href=\"" + contextPath + "/css/" + institutionCss + ".css\" rel=\"stylesheet\" type=\"text/css\" />");
            if (cssFilename != null && !cssFilename.trim().equals("")) {
            	out.println("<link href=\"" + contextPath + "/css/" + cssFilename + ".css\" rel=\"stylesheet\" type=\"text/css\" />");
            }

            //out.println("<script src=\"http://jsconsole.com/remote.js?83838903-5AB2-408D-9991-087ADD37665C\"></script>");
            
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/jquery.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/jquery.cookie.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/custom.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/applyStylize.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/jquery.mobile.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/jquery.tmpl.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/jquery.validate.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/jquery.validate.ready.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/jquery.templates.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/doT.js\"></script>");
                      
            if(platform != null && platform.equals("iOS")){

            	out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/iOS/phonegap-" + phonegap + ".js\"></script>");
                out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/iOS/ChildBrowser.js\"></script>");
                out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/iOS/barcodescanner.js\"></script>");
//                out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/iOS/Connection.js\"></script>");
                out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/iOS/PushHandler.js\"></script>");
                out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/iOS/Badge.js\"></script>");
                out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/iOS/applicationPreferences.js\"></script>");
            }else if(platform != null && platform.equals("Android")){
                out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/android/phonegap-" + phonegap + ".js\"></script>");
                out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/android/childbrowser.js\"></script>");
                out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/android/barcodescanner.js\"></script>");                        	
                out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/android/statusbarnotification.js\"></script>"); 
                //out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/android/C2DMPlugin.js\"></script>");
                //out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/android/PG_C2DM_script.js\"></script>");
            }

            String profileId = coreService.findGoogleAnalyticsProfileId().trim();
            if (!disableGoogleAnalytics && profileId.length() > 0) {
            	out.println("<script type=\"text/javascript\">");
            	out.println("var _gaq = _gaq || [];");
            	out.println("_gaq.push(['_setAccount', '" + profileId + "']);");
            	out.println("_gaq.push(['_trackPageview']);");
            	out.println("(function() {");
            	out.println("var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;");
            	out.println("ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';");
            	out.println("var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);");
            	out.println("})();");
            	out.println("</script>");
            }
            
            if (usesGoogleMaps) {
            	if(mapLocale != null){
               		out.println("<script type=\"text/javascript\" src=\"http://maps.google.com/maps/api/js?sensor=true&language=" + mapLocale + "\"></script>");            		
            	}else{
            		out.println("<script type=\"text/javascript\" src=\"http://maps.google.com/maps/api/js?sensor=true\"></script>");
            	}
            }

            if (jsFilename != null && !jsFilename.trim().equals("")) {
                out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/" + jsFilename + ".js\"></script>");
            }
            out.println("<meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\">");
            out.println("</head>");

            if(onBodyLoad != null){
            	out.println("<body onload='" + onBodyLoad + "'>");
            }else{
            	out.println("<body onload='" + onBodyLoad + "'>");
            }
            
            out.println("<div data-role=\"page\" id=\"" + id + "\">");
            out.println("<div data-role=\"header\">");
            if (loginButton || getAuthMapper().getLoginURL() != null) {
            	if (user == null || user.isPublicUser()) {
                    out.println("<a href=\"" + (loginButtonURL != null ? loginButtonURL : ( getAuthMapper().getLoginURL() != null ? getAuthMapper().getLoginURL() : contextPath + "/login") ) + "\" data-role=\"button\" data-icon=\"lock\">Login</a>");
            	} else {
                    out.println("<a href=\"" + (logoutButtonURL != null ? logoutButtonURL : ( getAuthMapper().getLogoutURL() != null ? getAuthMapper().getLogoutURL() : contextPath + "/logout") ) + "\" data-role=\"button\" data-icon=\"unlock\">Logout</a>");
            	}
            }
            if (backButton) {
                out.println("<a href=\"" + (backButtonURL != null ? backButtonURL : "javascript: history.go(-1)") + "\" class=\"ui-btn-left\" data-icon=\"back\" data-iconpos=\"notext\">Back</a>");
            }
            out.println("<h1>" + title + "</h1>");
            if (preferencesButton) {
                out.println("<a href=\"" + (preferencesButtonURL != null ? preferencesButtonURL : contextPath + "/preferences") + "\" class=\"ui-btn-right\" data-icon=\"gear\" data-iconpos=\"notext\">Preferences</a>");
            }
            if (homeButton) {
                out.println("<a href=\"" + contextPath + "/home\" class=\"ui-btn-right\" data-icon=\"home\" data-iconpos=\"notext\">Home</a>");
            }
            out.println("</div>");
            getJspBody().invoke(out);
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

	/**
	 * @return the disableGoogleAnalytics
	 */
	public boolean isDisableGoogleAnalytics() {
		return disableGoogleAnalytics;
	}

	/**
	 * @param disableGoogleAnalytics the disableGoogleAnalytics to set
	 */
	public void setDisableGoogleAnalytics(boolean disableGoogleAnalytics) {
		this.disableGoogleAnalytics = disableGoogleAnalytics;
	}

	public AuthenticationMapper getAuthMapper() {
		return authMapper;
	}

	public void setAuthMapper(AuthenticationMapper authMapper) {
		this.authMapper = authMapper;
	}
	
	public void setInstitutionCss(String institutionCss) {
		this.institutionCss = institutionCss;
	}
}