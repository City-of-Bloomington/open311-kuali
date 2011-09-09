package org.kuali.mobility.classifieds.entity;

import java.util.List;
import java.util.Map;

public class ClassifiedsPageException extends Exception {

	private static final long serialVersionUID = 9058396855683140816L;
	private Map<String, List<String>> pageErrors;

	public ClassifiedsPageException(Map<String, List<String>> errors) {
		this.pageErrors = errors;
	}

	public Map<String, List<String>> getPageErrors() {
		return pageErrors;
	}

	public void setPageErrors(Map<String, List<String>> pageErrors) {
		this.pageErrors = pageErrors;
	}
}
