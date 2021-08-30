package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.TypeOfExamination;
import it.uniroma3.siw.spring.service.TypeOfExaminationService;
@Component
public class TypeOfExaminationValidator implements Validator {
	@Autowired
	private TypeOfExaminationService typeOfExaminationService;
	
    private static final Logger logger = LoggerFactory.getLogger(TypeOfExaminationValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required");


		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.typeOfExaminationService.alreadyExists((TypeOfExamination)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return TypeOfExamination.class.equals(aClass);
	}
}
