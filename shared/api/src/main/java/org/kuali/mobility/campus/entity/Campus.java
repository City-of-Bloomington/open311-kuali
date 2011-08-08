package org.kuali.mobility.campus.entity;

import java.io.Serializable;

public class Campus implements Serializable {

	private static final long serialVersionUID = -8000615503651726243L;

	private String name;

	private String code;

	public Campus(String code, String name) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
