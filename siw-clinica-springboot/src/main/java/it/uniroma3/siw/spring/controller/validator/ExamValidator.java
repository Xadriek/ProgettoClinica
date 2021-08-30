package it.uniroma3.siw.spring.controller.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.spring.model.Exam;
import it.uniroma3.siw.spring.service.ExamService;
@Component
public class ExamValidator implements Validator {
	@Autowired
	private ExamService examService;
	
    private static final Logger logger = LoggerFactory.getLogger(ExamValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "patient", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "doctor", "required");

		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.examService.alreadyExists((Exam)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicato");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Exam.class.equals(aClass);
	}
}