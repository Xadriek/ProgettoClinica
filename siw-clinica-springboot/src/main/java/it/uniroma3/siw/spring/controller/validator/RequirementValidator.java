package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Requirement;
import it.uniroma3.siw.spring.service.RequirementService;

@Component
public class RequirementValidator implements Validator {
	@Autowired
	private RequirementService requirementService;
	
    private static final Logger logger = LoggerFactory.getLogger(RequirementValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required");
		

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.requirementService.alreadyExists((Requirement)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Requirement.class.equals(aClass);
	}
}
