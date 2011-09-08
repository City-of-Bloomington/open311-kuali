package org.kuali.mobility.user.entity;

import java.io.Serializable;
import java.util.Date;

public class UserCacheObject implements Serializable {

	private static final long serialVersionUID = -8416651148864340848L;
	
	private long lastUpdateTime;
	private Object item;
	
	public UserCacheObject(Object item) {
		setItem(item);
	}
	
	public Object getItem() {
		return item;
	}
	
	public void setItem(Object item) {
		this.item = item;
		this.lastUpdateTime = new Date().getTime();
	}
	
	public long getLastUpdateTime() {
		return lastUpdateTime;
	}
}
