package org.kuali.mobility.sakai.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ForumTopic implements Serializable{
	
	private static final long serialVersionUID = 1575033850650398254L;
	
	private String title;
    private String typeUuid;
    private String id;
    private String forumId;
    private String description;
    private int unreadCount;
    private int messageCount;
    private List<ForumThread> threads;
    private List<Attachment> attachments;
    
    public ForumTopic() {
    	threads = new ArrayList<ForumThread>();
    	attachments = new ArrayList<Attachment>();
    }
	
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTypeUuid() {
		return typeUuid;
	}
	public void setTypeUuid(String typeUuid) {
		this.typeUuid = typeUuid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUnreadCount() {
		return unreadCount;
	}
	public void setUnreadCount(int unreadCount) {
		this.unreadCount = unreadCount;
	}
	public int getMessageCount() {
		return messageCount;
	}
	public void setMessageCount(int messageCount) {
		this.messageCount = messageCount;
	}

	public String getForumId() {
		return forumId;
	}

	public void setForumId(String forumId) {
		this.forumId = forumId;
	}

	public List<ForumThread> getThreads() {
		return threads;
	}

	public void setThreads(List<ForumThread> threads) {
		this.threads = threads;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}  
}
