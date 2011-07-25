package org.kuali.mobility.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ListViewTag extends SimpleTagSupport {
    
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ListViewTag.class);
    
    private String id;
    private boolean filter;
    private String dataTheme;
    private String dataDividerTheme;
    private boolean dataInset;
    
    public void doTag() throws JspException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        try {
            out.println("<ul data-role=\"listview\" data-theme=\"b\" data-inset=\"true\">");
            out.println("<dl data-role=\"listview\" data-theme=\"b\" data-dividertheme=\"b\" data-filter=\""+ (filter ? "true" : "false") + "\" data-inset=\"false\" class=\"" + id + "\">");
            getJspBody().invoke(out);          
            out.println("</ul>");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
    
    public void setDataTheme(String dataTheme) {
        this.dataTheme = dataTheme;
    }

    public void setDataDividerTheme(String dataDividerTheme) {
        this.dataDividerTheme = dataDividerTheme;
    }
    
    public void setDataInset(boolean dataInset) {
        this.dataInset = dataInset;
    }
}	