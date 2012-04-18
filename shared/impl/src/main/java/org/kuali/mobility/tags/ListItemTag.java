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

/**
 * The backing class for the ListItem JSP tag.  Renders an li appropriate for the mobile framework.
 * 
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 *
 */
public class ListItemTag extends SimpleTagSupport {
    
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ListItemTag.class);
    
    private String dataRole;
    private String dataTheme;
    private String dataIcon;
    private boolean hideDataIcon;
    private String cssClass;
    
    public void doTag() throws JspException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        try {
            out.println("<li style='overflow:hidden' " + (dataRole != null && !"".equals(dataRole.trim()) ? " data-role=\"" + dataRole.trim() + "\"" : "") + (hideDataIcon ? " data-icon=\"false\"" : (dataIcon != null && !"".equals(dataIcon.trim()) ? " data-icon=\"" + dataIcon.trim() + "\"" : "")) + (cssClass != null && !"".equals(cssClass.trim()) ? " class=\"" + cssClass.trim() + "\"" : "") + " data-theme=\"" + (dataTheme != null && !"".equals(dataTheme.trim()) ? dataTheme : "c") + "\">");
            getJspBody().invoke(out);          
            out.println("</li>");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
    
    public void setDataRole(String dataRole) {
        this.dataRole = dataRole;
    }
    
    public void setDataTheme(String dataTheme) {
        this.dataTheme = dataTheme;
    }
    
    public void setDataIcon(String dataIcon) {
        this.dataIcon = dataIcon;
    }
    
    public void setHideDataIcon(boolean hideDataIcon) {
        this.hideDataIcon = hideDataIcon;
    }
    
    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }
}