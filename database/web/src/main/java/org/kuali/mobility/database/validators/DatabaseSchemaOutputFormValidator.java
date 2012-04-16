package org.kuali.mobility.database.validators;

import org.kuali.mobility.database.entity.DatabaseSchemaOutputForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DatabaseSchemaOutputFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> parameter) {
		return DatabaseSchemaOutputForm.class.equals(parameter);
	}

	@Override
	public void validate(Object object, Errors errors) {
		DatabaseSchemaOutputForm form = (DatabaseSchemaOutputForm) object;
//		if (form.getDelimiter() == null || "".equals(form.getDelimiter().trim())) {
//			errors.rejectValue("delimiter", "DELIMITER.REQUIRED", "A delimiter is required.");
//		}
		if (form.getDialectType() == null || "".equals(form.getDialectType().trim())) {
			errors.rejectValue("dialectType", "DIALECT.TYPE.REQUIRED", "Selecting a database type is required.");
		}
	}

}
