package org.kuali.mobility.database.entity;

public class DatabaseSchemaOutputForm {

	private String dialectType;
	private String delimiter;
	private boolean newLineBeforeDelimiter;
	private boolean removeForeignKeys;

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getDialectType() {
		return dialectType;
	}

	public void setDialectType(String dialectType) {
		this.dialectType = dialectType;
	}

	public boolean isNewLineBeforeDelimiter() {
		return newLineBeforeDelimiter;
	}

	public void setNewLineBeforeDelimiter(boolean newLineBeforeDelimiter) {
		this.newLineBeforeDelimiter = newLineBeforeDelimiter;
	}

	public boolean isRemoveForeignKeys() {
		return removeForeignKeys;
	}

	public void setRemoveForeignKeys(boolean removeForeignKeys) {
		this.removeForeignKeys = removeForeignKeys;
	}


}
