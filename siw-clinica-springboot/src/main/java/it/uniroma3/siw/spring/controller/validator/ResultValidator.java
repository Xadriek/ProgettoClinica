package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import it.uniroma3.siw.spring.model.Result;


@Component
public class ResultValidator implements Validator {

	
    private static final Logger logger = LoggerFactory.getLogger(ResultValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "value", "required");
		

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Result.class.equals(aClass);
	}
}
