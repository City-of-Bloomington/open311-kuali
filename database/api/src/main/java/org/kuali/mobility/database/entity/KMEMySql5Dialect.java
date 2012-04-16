package org.kuali.mobility.database.entity;

import org.hibernate.dialect.MySQL5Dialect;

public class KMEMySql5Dialect extends MySQL5Dialect implements KMEDialect {

	private boolean overrideAlterTable;
	
	@Override
	public String getAddForeignKeyConstraintString(String constraintName,
			String[] foreignKey, String referencedTable, String[] primaryKey,
			boolean referencesPrimaryKey) {
		if (overrideAlterTable) {
			return "";	
		}
		return super.getAddForeignKeyConstraintString(constraintName, foreignKey, referencedTable, primaryKey, referencesPrimaryKey);		
	}
	
	public boolean hasAlterTable() {
		if (overrideAlterTable) {
			return false;
		}
		return super.hasAlterTable();
	}

	public boolean isOverrideAlterTable() {
		return overrideAlterTable;
	}

	public void setOverrideAlterTable(boolean overrideAlterTable) {
		this.overrideAlterTable = overrideAlterTable;
	}
	
}
