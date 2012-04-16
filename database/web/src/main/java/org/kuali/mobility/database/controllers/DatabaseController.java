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

package org.kuali.mobility.database.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.kuali.mobility.database.entity.DatabaseSchemaOutputForm;
import org.kuali.mobility.database.service.DatabaseService;
import org.kuali.mobility.database.validators.DatabaseSchemaOutputFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
@RequestMapping("/database")
public class DatabaseController {

    @Autowired
    private DatabaseService service;
    public void setDatabaseService(DatabaseService service) {
        this.service = service;
    }
	
    private static final Map<String, String> dialectTypes;
    
    static {
    	dialectTypes = new LinkedHashMap<String, String>();
    	dialectTypes.put("", "Select a Database Type");
    	dialectTypes.put("org.hibernate.dialect.Oracle10gDialect", "Oracle10g");
//    	dialectTypes.put("org.kuali.mobility.database.entity.KMEOracleDialect", "Oracle");
    	dialectTypes.put("org.hibernate.dialect.MySQL5Dialect", "MySQL5");
//    	dialectTypes.put("org.kuali.mobility.database.entity.KMEMySql5Dialect", "MySQL5");
    	dialectTypes.put("org.hibernate.dialect.SQLServerDialect", "SQL Server");
    	dialectTypes.put("org.hibernate.dialect.PostgreSQLDialect", "PostgreSQL");
    	dialectTypes.put("org.hibernate.dialect.DerbyDialect", "Derby");
//    	dialectTypes.put("", "");
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model uiModel) {
//    	String schema = service.getSchema();
//    	uiModel.addAttribute("schema", schema);
    	DatabaseSchemaOutputForm form = new DatabaseSchemaOutputForm();
    	uiModel.addAttribute("form", form);
   		uiModel.addAttribute("dialectTypes", dialectTypes);
    	return "database/schemaoutputform";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String getSchema(Model uiModel, @ModelAttribute("form") DatabaseSchemaOutputForm form, BindingResult result) {
		DatabaseSchemaOutputFormValidator validator = new DatabaseSchemaOutputFormValidator();
		validator.validate(form, result);
		if (result.hasErrors()) {
			uiModel.addAttribute("dialectTypes", dialectTypes);
			return "database/schemaoutputform";
		} else {
	    	String schema = this.getSchema(form.getDialectType(), form.getDelimiter(), form.isNewLineBeforeDelimiter(), form.isRemoveForeignKeys());
	    	uiModel.addAttribute("schema", schema);
	    	uiModel.addAttribute("dialect", form.getDialectType());
	    	uiModel.addAttribute("delimiter", form.getDelimiter());
			return "database/schemaoutput";
		}
    }

    @RequestMapping(method = RequestMethod.POST, params = "download")
    public String getSchemaDownload(Model uiModel, @ModelAttribute("form") DatabaseSchemaOutputForm form, BindingResult result, HttpServletResponse response) {
		DatabaseSchemaOutputFormValidator validator = new DatabaseSchemaOutputFormValidator();
		validator.validate(form, result);
		if (result.hasErrors()) {
			uiModel.addAttribute("dialectTypes", dialectTypes);
			return "database/schemaoutputform";
		} else {
			try {
				response.setContentType("text/plain");
				response.setHeader("Content-Disposition", "attachment;filename=schema.ddl");
				String schema = this.getSchema(form.getDialectType(), form.getDelimiter(), form.isNewLineBeforeDelimiter(), form.isRemoveForeignKeys());
				InputStream is = new ByteArrayInputStream(schema.getBytes("UTF-8"));
				IOUtils.copy(is, response.getOutputStream());
				response.flushBuffer();
			} catch (IOException ex) {
				// log.info("Error writing file to output stream. Filename was '" +
				// fileName + "'");
				throw new RuntimeException("IOError writing file to output stream");
			}
			return null;
		}
    }

    /*
    @RequestMapping(value="/schema", method = RequestMethod.GET)
	@ResponseBody
	public String getSchema(@RequestParam(value = "dialect", required = true) String dialect, @RequestParam(value = "delimiter", required = true) String delimiter, HttpServletRequest request) {
//		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		String schema = service.getSchema(dialect, delimiter);
		return schema;
	}
	*/
    
    /*
    @RequestMapping(value = "/schema2", method = RequestMethod.GET)
    public void getFile(@RequestParam(value = "dialect", required = true) String dialect, @RequestParam(value = "delimiter", required = true) String delimiter, @RequestParam(value = "newline", required = true) boolean newLine, HttpServletResponse response) {
		try {
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment;filename=schema.ddl");
			String schema = this.getSchema(dialect, delimiter, newLine);
			InputStream is = new ByteArrayInputStream(schema.getBytes("UTF-8"));
			IOUtils.copy(is, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException ex) {
			// log.info("Error writing file to output stream. Filename was '" +
			// fileName + "'");
			throw new RuntimeException("IOError writing file to output stream");
		}
    }
    */
    
    private String getSchema(String dialect, String delimiter, boolean newLine, boolean overrideAlterTable) {
		if (newLine) {
			delimiter = "\n" + delimiter;
		}
		String schema = service.getSchema(dialect, delimiter, overrideAlterTable);
		return schema;
    }

    
}
