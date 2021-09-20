package it.uniroma3.siw.spring.controller;



import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lowagie.text.DocumentException;

import it.uniroma3.siw.spring.model.Exam;

import it.uniroma3.siw.spring.service.ExamService;
import it.uniroma3.siw.upload.ExamPDFExporter;


@Controller
public class ExamController {
	
	@Autowired
	private ExamService examService;
	

	//private final Logger logger = LoggerFactory.getLogger(this.getClass());	
	



        
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
    @GetMapping("/exam/export/pdf/{id}")
    public void exportToPDF(@PathVariable("id") Long id,HttpServletResponse response) throws DocumentException, IOException {
    	Exam exam=this.examService.examById(id);
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=exam_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue); 
        ExamPDFExporter exporter = new ExamPDFExporter(exam);
        exporter.export(response);
         
    }
}
