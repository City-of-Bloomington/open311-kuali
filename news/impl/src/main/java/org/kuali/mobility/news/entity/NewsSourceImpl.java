package org.kuali.mobility.news.entity;

public class NewsSourceImpl implements NewsSource {

	private Long id = null;
	private String name = null;
	private String url = null;
	private boolean active = true;
	private int order = 0;
	private Long versionNumber = null;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	public Long getVersionNumber() {
		return versionNumber;
	}
	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}
	

}
