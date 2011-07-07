package org.kuali.mobility.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class DefinitionListTermTag extends SimpleTagSupport {
    
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DefinitionListTermTag.class);
    
    public void doTag() throws JspException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        try {
            out.println("<dt>");
            getJspBody().invoke(out);          
            out.println("</dt>");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
    
}