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
 * The backing class for the ListView JSP tag.  Renders a ul to contain a list of ListItem tags.
 * 
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 *
 */
public class ListViewTag extends SimpleTagSupport {
    
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ListViewTag.class);
    
    private String id;
    private boolean filter;
    private String dataTheme;
    private String dataDividerTheme;
    private boolean dataInset;
    private String cssClass;
    
    public void doTag() throws JspException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        try {
			out.println("<ul data-role=\"listview\"" + (id != null && !"".equals(id.trim()) ? " id=\"" + id.trim() + "\"" : "") + (cssClass != null && !"".equals(cssClass.trim()) ? " class=\"" + cssClass.trim() + "\"" : "") + (dataTheme != null && !"".equals(dataTheme.trim()) ? " data-theme=\"" + dataTheme.trim() + "\"" : "") + " data-inset=\"" + (dataInset ? "true" : "false") + "\" data-filter=\"" + (filter ? "true" : "false") + "\"" + (dataDividerTheme != null && !"".equals(dataDividerTheme.trim()) ? " data-dividertheme=\"" + dataDividerTheme + "\"" : "") + ">");
            getJspBody().invoke(out);          
            out.println("</ul>");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
    
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setDataTheme(String dataTheme) {
        this.dataTheme = dataTheme;
    }

    public void setDataDividerTheme(String dataDividerTheme) {
        this.dataDividerTheme = dataDividerTheme;
    }
    
    public void setFilter(boolean filter) {
        this.filter = filter;
    }
    
    public void setDataInset(boolean dataInset) {
        this.dataInset = dataInset;
    }
    
    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }
}	