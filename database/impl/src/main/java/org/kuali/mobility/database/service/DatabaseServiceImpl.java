/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
 
package org.kuali.mobility.database.service;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.spi.PersistenceUnitInfo;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.Dialect;
import org.hibernate.ejb.Ejb3Configuration;
import org.hibernate.jdbc.util.FormatStyle;
import org.hibernate.jdbc.util.Formatter;
import org.hibernate.mapping.ForeignKey;
import org.hibernate.mapping.Table;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.util.PropertiesHelper;
import org.kuali.mobility.database.dao.DatabaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class DatabaseServiceImpl implements DatabaseService {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DatabaseServiceImpl.class);
	
    @Autowired
    private DatabaseDao dao;
    public void setDatabaseDao(DatabaseDao dao) {
        this.dao = dao;
    }
    public DatabaseDao getDatabaseDao() {
        return dao;
    }
    
	@Resource(name="&entityManagerFactory")
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;
    
	@Override
	public String getSchema(String dialect, String delimiter, boolean overrideAlterTable) {
		return execute(dialect, delimiter, overrideAlterTable);
	}

	private String execute(String dialectStr, String delimiter, boolean overrideAlterTable) {
	    PersistenceUnitInfo persistenceUnitInfo = entityManagerFactory.getPersistenceUnitInfo();

	    Map<String, Object> jpaPropertyMap = entityManagerFactory.getJpaPropertyMap();	
	    jpaPropertyMap.put("hibernate.dialect", dialectStr); 
	    Configuration configuration = new Ejb3Configuration().configure( persistenceUnitInfo, jpaPropertyMap ).getHibernateConfiguration();
//	    KMEDatabaseConfiguration c = (KMEDatabaseConfiguration) configuration;
	    
	    if (overrideAlterTable) {
			Iterator iter = configuration.getTableMappings();
			while ( iter.hasNext() ) {
				Table table = (Table) iter.next();
				if ( table.isPhysicalTable() ) {
					Iterator subIter = table.getForeignKeyIterator();
					while ( subIter.hasNext() ) {
						ForeignKey fk = (ForeignKey) subIter.next();
						if ( fk.isPhysicalConstraint() ) {
							subIter.remove();
						}
					}
				}
			}	    	
	    }
	    
	    Properties configurationProperties = configuration.getProperties();
	    
	    Dialect dialect = Dialect.getDialect(configurationProperties);
//	    if (dialect instanceof KMEDialect) {
//	    	KMEDialect d = (KMEDialect) dialect;
//	    	d.setOverrideAlterTable(overrideAlterTable);
//	    }
	    
		Properties props = new Properties();
		props.putAll(dialect.getDefaultProperties());
		props.putAll(configurationProperties);

		String[] dropSQL = configuration.generateDropSchemaScript(dialect);
		String[] createSQL = configuration.generateSchemaCreationScript(dialect);

		Formatter formatter = (PropertiesHelper.getBoolean(Environment.FORMAT_SQL, props) ? FormatStyle.DDL
				: FormatStyle.NONE).getFormatter();
		boolean format = true;
		formatter = ( format ? FormatStyle.DDL : FormatStyle.NONE ).getFormatter();
		
//		String delimiter = ";";
		StringBuffer output = new StringBuffer();
		for (String s : dropSQL) {
			output.append(formatMe(s, formatter, delimiter));
			output.append("\n");
		}
		for (String s : createSQL) {
			output.append(formatMe(s, formatter, delimiter));
			output.append("\n");
		}

	    SchemaExport schema = new SchemaExport(configuration);
//	    schema.setFormat(true);
//	    schema.setDelimiter(";");
//	    schema.setOutputFile("/tmp/schema.sql");
//  	schema.create(false, false);
		
//org.hibernate.dialect.Oracle10gDialect
//org.hibernate.dialect.MySQL5Dialect
		return output.toString();
	}
	
	private String formatMe(String sql, Formatter formatter, String delimiter) {
		String formatted = formatter.format( sql );
		if ( delimiter != null ) {
			formatted += delimiter;
		}
		return formatted;
	}
	
    
}
