package org.kuali.mobility.conference.entity;

import java.io.Serializable;

public class MenuItem implements Serializable {

	private static final long serialVersionUID = 389877169344713082L;

	private String title;
	private String description;
	private String linkURL;
	private String iconURL;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLinkURL() {
		return linkURL;
	}
	public void setLinkURL(String linkURL) {
		this.linkURL = linkURL;
	}
	public String getIconURL() {
		return iconURL;
	}
	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}
}
