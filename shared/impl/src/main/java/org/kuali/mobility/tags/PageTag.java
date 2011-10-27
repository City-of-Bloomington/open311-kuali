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

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.kuali.mobility.shared.Constants;
import org.kuali.mobility.user.entity.User;

public class PageTag extends SimpleTagSupport {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PageTag.class);

    private String id;
    private String title;
    private boolean homeButton;
    private boolean backButton;
    private String backButtonURL;
    private boolean preferencesButton;
    private String preferencesButtonURL;
    private boolean usesGoogleMaps;
    private boolean usesGoogleMapsGeometry;
    private String appcacheFilename;
	private String cssFilename;
    private String jsFilename;
    private boolean loginButton;
    private String loginButtonURL;
	private String logoutButtonURL;
    
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setHomeButton(boolean homeButton) {
        this.homeButton = homeButton;
    }
    
    public void setBackButton(boolean backButton) {
        this.backButton = backButton;
    }
    
    public void setBackButtonURL(String backButtonURL) {
        this.backButtonURL = backButtonURL;
    }
    
    public void setPreferencesButton(boolean preferencesButton) {
		this.preferencesButton = preferencesButton;
	}

	public void setPreferencesButtonURL(String preferencesButtonURL) {
		this.preferencesButtonURL = preferencesButtonURL;
	}
	
	public void setUsesGoogleMaps(boolean usesGoogleMaps) {
		this.usesGoogleMaps = usesGoogleMaps;
	}
	
	public void setUsesGoogleMapsGeometry(boolean usesGoogleMapsGeometry) {
		this.usesGoogleMapsGeometry = usesGoogleMapsGeometry;
	}
	
    public void setAppcacheFilename(String appcacheFilename) {
    	this.appcacheFilename = appcacheFilename;
    }
	
	public void setCssFilename(String cssFilename) {
		this.cssFilename = cssFilename;
	}
    
    public void setJsFilename(String jsFilename) {
        this.jsFilename = jsFilename;
    }

	public void setLoginButton(boolean loginButton) {
		this.loginButton = loginButton;
	}

	public void setLoginButtonURL(String loginButtonURL) {
		this.loginButtonURL = loginButtonURL;
	}

	public void setButtonLogoutURL(String logoutButtonURL) {
		this.logoutButtonURL = logoutButtonURL;
	}

	public void doTag() throws JspException {
        PageContext pageContext = (PageContext) getJspContext();
        User user = (User) pageContext.getSession().getAttribute(Constants.KME_USER_KEY);
        String contextPath = pageContext.getServletContext().getContextPath();
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
            out.println("<link href=\"http://www.iu.edu/favicon.ico\" rel=\"icon\" />");
            out.println("<link href=\"http://www.iu.edu/favicon.ico\" rel=\"shortcut icon\" />");
            out.println("<link rel=\"apple-touch-icon\" href=\"" + contextPath + "/apple-touch-icon.png\"/>");
            out.println("<link href=\"" + contextPath + "/css/jquery.mobile.css\" rel=\"stylesheet\" type=\"text/css\" />");
            out.println("<link href=\"" + contextPath + "/css/jquery-mobile-fluid960.css\" rel=\"stylesheet\" type=\"text/css\" />");
            out.println("<link href=\"" + contextPath + "/css/custom.css\" rel=\"stylesheet\" type=\"text/css\" />");
            if (cssFilename != null && !cssFilename.trim().equals("")) {
            	out.println("<link href=\"" + contextPath + "/css/" + cssFilename + ".css\" rel=\"stylesheet\" type=\"text/css\" />");
            }
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/jquery.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/custom.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/jquery.mobile.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/jquery.tmpl.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/jquery.validate.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/jquery.validate.ready.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/jquery.templates.js\"></script>");
            if (usesGoogleMaps) {
            	if (usesGoogleMapsGeometry) {
            		out.println("<script type=\"text/javascript\" src=\"http://maps.google.com/maps/api/js?libraries=geometry&v=3&sensor=true\"></script>");
            	} else {
            		out.println("<script type=\"text/javascript\" src=\"http://maps.google.com/maps/api/js?v=3&sensor=true\"></script>");
            	}
            }
            if (jsFilename != null && !jsFilename.trim().equals("")) {
                out.println("<script type=\"text/javascript\" src=\"" + contextPath + "/js/" + jsFilename + ".js\"></script>");
            }
            out.println("<meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<div data-role=\"page\" id=\"" + id + "\">");
            out.println("<div data-role=\"header\">");
            if (loginButton) {
            	if (user == null || user.isPublicUser()) {
                    out.println("<a href=\"" + (loginButtonURL != null ? loginButtonURL : contextPath + "/login") + "\" data-role=\"button\" data-icon=\"lock\">Login</a>");
            	} else {
                    out.println("<a href=\"" + (logoutButtonURL != null ? logoutButtonURL : contextPath + "/logout") + "\" data-role=\"button\" data-icon=\"unlock\">Logout</a>");
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
            
            out.println("<script src=\"" + contextPath + "/js/urchin.js\" type=\"text/javascript\"></script>");
            out.println("<script type=\"text/javascript\">_userv=0;urchinTracker();</script>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}