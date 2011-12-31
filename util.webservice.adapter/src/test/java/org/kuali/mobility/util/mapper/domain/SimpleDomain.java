package org.kuali.mobility.util.mapper.domain;

import java.io.Serializable;
import java.util.List;

public class SimpleDomain implements Serializable {
	private static final long serialVersionUID = -2711818566806279257L;

	private String id;
	private String givenName;
	private String surname;
	private String displayName;
	private List<String> aliases;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}
}
