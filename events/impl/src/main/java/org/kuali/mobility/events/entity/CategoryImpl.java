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
package org.kuali.mobility.events.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
public class CategoryImpl implements Serializable, Category {

    private static final long serialVersionUID = 2433472025839184720L;
    private String categoryId;
    private String title;
    private String returnPage;
    private List<Day> days;
    private int order;
    private String campus;
    private String urlString;

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#getReturnPage()
	 */
	@Override
	public String getReturnPage() {
        return returnPage;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#setReturnPage(java.lang.String)
	 */
	@Override
	public void setReturnPage(String returnPage) {
        this.returnPage = returnPage;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#getDays()
	 */
	@Override
	public List<Day> getDays() {
        return days;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#setDays(java.util.List)
	 */
	@Override
	public void setDays(List<Day> days) {
        this.days = days;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#getCategoryId()
	 */
	@Override
	public String getCategoryId() {
        return categoryId;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#setCategoryId(java.lang.String)
	 */
	@Override
	public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#getTitle()
	 */
	@Override
	public String getTitle() {
        return title;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
        this.title = title;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#getOrder()
	 */
	@Override
	public int getOrder() {
        return order;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#setOrder(int)
	 */
	@Override
	public void setOrder(int order) {
        this.order = order;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#getCampus()
	 */
	@Override
	public String getCampus() {
        return campus;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#setCampus(java.lang.String)
	 */
	@Override
	public void setCampus(String campus) {
        this.campus = campus;
    }

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#getUrlString()
	 */
	@Override
	public String getUrlString() {
		return urlString;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#setUrlString(java.lang.String)
	 */
	@Override
	public void setUrlString(String url) {
		this.urlString = url;
	}
}
