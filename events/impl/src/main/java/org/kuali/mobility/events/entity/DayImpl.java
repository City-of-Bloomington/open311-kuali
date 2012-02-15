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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayImpl implements Serializable, Comparable<Day>, Day {

	private static final long serialVersionUID = -1626066050656327429L;

	private Date date;

	private List<Event> events;

	public DayImpl() {
		events = new ArrayList<Event>();
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Day#compareTo(org.kuali.mobility.events.entity.DayImpl)
	 */
	public int compareTo(Day that) {
		if (getDate() != null && that.getDate() != null) {
			if (getDate().equals(that.getDate())) {
				return 0;
			}
			return getDate().before(that.getDate()) ? -1 : 1;
		}
		return -1;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Day#getDate()
	 */
	@Override
	public Date getDate() {
		return date;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Day#setDate(java.util.Date)
	 */
	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public List<Event> getEvents() {
		return events;
	}

	@Override
	public void setEvents(List<Event> events) {
		this.events = events;
	}

}
