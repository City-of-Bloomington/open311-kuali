package org.kuali.mobility.util.mapper.entity;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("dataMapping")
public class DataMapping implements Serializable {
	private static final long serialVersionUID = 4848897251857351688L;

	@XStreamAsAttribute
	private String id;
	@XStreamAsAttribute
	private String className;
	@XStreamAsAttribute
	private boolean list;
	@XStreamAsAttribute
	private String rootElement;
	@XStreamAsAttribute
	private String rootElementClassName;
	@XStreamAsAttribute
	private String mimeType;

	private List<MappingElement> mappings;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public List<MappingElement> getMappings() {
		return mappings;
	}

	public void setMappings(List<MappingElement> mappings) {
		this.mappings = mappings;
	}

	public boolean isList() {
		return list;
	}

	public void setList(boolean list) {
		this.list = list;
	}

	public String getRootElement() {
		return rootElement;
	}

	public void setRootElement(String rootElement) {
		this.rootElement = rootElement;
	}

	public String getRootElementClassName() {
		return rootElementClassName;
	}

	public void setRootElementClassName(String rootElementClassName) {
		this.rootElementClassName = rootElementClassName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

}
