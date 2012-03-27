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

package org.kuali.mobility.news.entity;

public interface NewsSource {

	public Long getId();

	public void setId(Long id);

	/**
	 * @return the URL of the feed
	 */
	public String getUrl();

	/**
	 * @param url the URL of the feed
	 */
	public void setUrl(String url);

	public Long getVersionNumber();

	public void setVersionNumber(Long versionNumber);

	/**
	 * @return whether the feed is active or not
	 */
	public boolean isActive();

	/**
	 * @param active set this feed active or inactive
	 */
	public void setActive(boolean active);

	/**
	 * @return the name assigned to this feed
	 */
	public String getName();

	/**
	 * @param name the name to set for this feed.  It is not displayed to end users.
	 */
	public void setName(String name);

	/**
	 * @return the display order
	 */
	public int getOrder();

	/**
	 * @param order the display order
	 */
	public void setOrder(int order);
	
	public void setParentId( Long parentId );
	public Long getParentId();

}