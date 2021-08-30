package it.uniroma3.siw.spring.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.ExamValidator;
import it.uniroma3.siw.spring.model.Exam;
import it.uniroma3.siw.spring.service.ExamService;

@Controller
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
    @Autowired
    private ExamValidator examValidator;
        
    @RequestMapping(value="/admin/exam", method = RequestMethod.GET)
    public String addExam(Model model) {
    	model.addAttribute("exam", new Exam());
    	model.addAttribute("patient", this.examService.getUserService().getAllUsers());
    	model.addAttribute("doctor", this.examService.getDoctorService().allDoctors());
    	model.addAttribute("typeOfExamination", this.examService.getTypeOfExaminationService().allTypeOfExamination());
        return "examForm";
    }

    @RequestMapping(value = "/exam/{id}", method = RequestMethod.GET)
    public String getExam(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("exam", this.examService.examById(id));
    	return "exam";
    }

    @RequestMapping(value = "/exam", method = RequestMethod.GET)
    public String getExams(Model model) {
    		model.addAttribute("exams", this.examService.allExams());
    		return "exams";
    }
    
    @RequestMapping(value = "/admin/exam", method = RequestMethod.POST)
    public String addExam(@ModelAttribute("exam") Exam exam, 
    									Model model, BindingResult bindingResult) {
    	this.examValidator.validate(exam, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.examService.insert(exam);
            model.addAttribute("exams", this.examService.allExams());
            return "exams";
        }
        return "examForm";
    }
}
