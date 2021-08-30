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

import it.uniroma3.siw.spring.controller.validator.DoctorValidator;
import it.uniroma3.siw.spring.model.Doctor;
import it.uniroma3.siw.spring.service.DoctorService;

@Controller
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
   @Autowired
    private DoctorValidator doctorValidator;
        
    @RequestMapping(value="/admin/doctor", method = RequestMethod.GET)
    public String addDoctor(Model model) {
    	model.addAttribute("doctor", new Doctor());
        return "doctorForm";
    }

    @RequestMapping(value = "/doctor/{id}", method = RequestMethod.GET)
    public String getDoctor(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("doctor", this.doctorService.doctorById(id));
    	return "doctor";
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    public String getDoctors(Model model) {
    		model.addAttribute("doctors", this.doctorService.allDoctors());
    		return "doctors";
    }
    
    @RequestMapping(value = "/admin/doctor", method = RequestMethod.POST)
    public String addDoctor(@ModelAttribute("doctor") Doctor doctor, 
    									Model model, BindingResult bindingResult) {
    	this.doctorValidator.validate(doctor, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.doctorService.insert(doctor);
            model.addAttribute("doctors", this.doctorService.allDoctors());
            return "doctors";
        }
        return "doctorForm";
    }
}
