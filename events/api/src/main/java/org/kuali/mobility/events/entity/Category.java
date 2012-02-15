package org.kuali.mobility.events.entity;

import java.util.List;

public interface Category {

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#getReturnPage()
	 */
	public abstract String getReturnPage();

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#setReturnPage(java.lang.String)
	 */
	public abstract void setReturnPage(String returnPage);

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#getDays()
	 */
	public abstract List<Day> getDays();

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#setDays(java.util.List)
	 */
	public abstract void setDays(List<Day> days);

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#getCategoryId()
	 */
	public abstract String getCategoryId();

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#setCategoryId(java.lang.String)
	 */
	public abstract void setCategoryId(String categoryId);

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#getTitle()
	 */
	public abstract String getTitle();

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#setTitle(java.lang.String)
	 */
	public abstract void setTitle(String title);

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#getOrder()
	 */
	public abstract int getOrder();

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#setOrder(int)
	 */
	public abstract void setOrder(int order);

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#getCampus()
	 */
	public abstract String getCampus();

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Category#setCampus(java.lang.String)
	 */
	public abstract void setCampus(String campus);

	public abstract String getUrlString();

	public abstract void setUrlString(String url);

}