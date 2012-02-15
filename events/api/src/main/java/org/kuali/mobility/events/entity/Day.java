package org.kuali.mobility.events.entity;

import java.util.Date;
import java.util.List;

public interface Day {

	public abstract int compareTo(Day that);

	public abstract Date getDate();

	public abstract void setDate(Date date);

	public abstract List<Event> getEvents();

	public abstract void setEvents(List<Event> events);

}