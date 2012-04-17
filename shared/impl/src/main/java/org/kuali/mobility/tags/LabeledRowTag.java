/**
 * Copyright 2011-2012 The Kuali Foundation Licensed under the
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
 * The backing class for the LabeledRow JSP tag.  Renders a row of content with a styleable label.
 * 
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 *
 */
public class LabeledRowTag extends SimpleTagSupport {
    
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LabeledRowTag.class);
    
    private String fieldLabel;
    private String fieldValue;
    
    public void doTag() throws JspException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        try {
        	if (fieldLabel != null && !fieldLabel.trim().equals("")) {
        		//out.println("<div style=\"border:2px solid black; clear:both;\">");
        		out.println("<div style=\"width:30%; text-align:right; float:left; color:#900\">" + fieldLabel + "</div> ");
        		out.println("<div style=\"width:60%; padding-left:35%\">" + (fieldValue == null || fieldValue.trim().equals("") ? "&nbsp;" : fieldValue));
        		if (getJspBody() != null) {
        			getJspBody().invoke(out);
        		}
        		out.println("</div>");
        		//out.println("</div>");
        	}
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

	public String getFieldLabel() {
		return fieldLabel;
	}

	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
    
    
    
}	