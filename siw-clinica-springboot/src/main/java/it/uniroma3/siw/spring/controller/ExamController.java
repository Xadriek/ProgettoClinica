package it.uniroma3.siw.spring.controller;



import java.time.LocalDate;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.Exam;

import it.uniroma3.siw.spring.service.ExamService;


@Controller
public class ExamController {
	
	@Autowired
	private ExamService examService;
	

	
	



        
    @RequestMapping(value="/admin/exam", method = RequestMethod.GET)
    public String addExam(Model model) {
    	model.addAttribute("exam", new Exam());
    	model.addAttribute("patients", this.examService.getUserService().getAllUsers());
    	model.addAttribute("doctors", this.examService.getDoctorService().allDoctors());
    	model.addAttribute("typeOfExaminations", this.examService.getTypeOfExaminationService().allTypeOfExamination());
        return "examForm";
    }

    @RequestMapping(value = "/exam/{id}", method = RequestMethod.GET)
    public String getExam(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("exam", this.examService.examById(id));
    	model.addAttribute("role", this.examService.getCredentialsService().getRoleAuthenticated());

    	return "exam";
    }

    @RequestMapping(value = "/admin/modExam/{id}", method = RequestMethod.GET)
    public String modExam(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("exam", this.examService.examById(id));
    	model.addAttribute("role", this.examService.getCredentialsService().getRoleAuthenticated());

    	return "examFormMod";
    }
    @RequestMapping(value ="/admin/examUpdate")
    public String updateExam(@ModelAttribute("exam") Exam exam,
    		Model model, BindingResult bindingResult){
    	this.examService.insert(exam);
    	
    	return "exams";
    	

}
    @RequestMapping(value = "/exam", method = RequestMethod.GET)
    public String getExams(Model model) {
    		model.addAttribute("exams", this.examService.allExams());
    		model.addAttribute("role", this.examService.getCredentialsService().getRoleAuthenticated());
    		return "exams";
    }
    @RequestMapping(value = "/exam/patient", method = RequestMethod.GET)
    public String getExamsByPatient(Model model) {
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		model.addAttribute("exams", this.examService.examByPatient(this.examService.getUserService().getUserByUsername(userDetails.getUsername())));
    		return "exams";
    }
   /* 
    @RequestMapping(value = "/exam/doctor", method = RequestMethod.GET)
    public String getExamsByDoctor(@ModelAttribute("doctor") Doctor doctor,Model model) {
    		model.addAttribute("exams", this.examService.examByDoctor(doctor));
    		return "exams";
    }*/
    
    @RequestMapping(value = "/admin/exam", method = RequestMethod.POST)
    public String newExam(@ModelAttribute("exam") Exam exam, 
    									Model model, BindingResult bindingResult) {
    	
    	
        if (!bindingResult.hasErrors()) {
        	
        	exam.setDateOfPrenotation(LocalDate.now());
        	
        	this.examService.insert(exam);
        	
            model.addAttribute("exams", this.examService.allExams());
            
            return "exams";
        }
        return "examForm";
    }
    
}
