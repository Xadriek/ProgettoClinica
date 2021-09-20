package it.uniroma3.siw.spring.controller;

import java.io.IOException;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import it.uniroma3.siw.spring.controller.validator.DoctorValidator;
import it.uniroma3.siw.spring.model.Doctor;
import it.uniroma3.siw.spring.service.DoctorService;
import it.uniroma3.siw.upload.FileUploadUtil;

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
    	model.addAttribute("role", this.doctorService.getCredentialsService().getRoleAuthenticated());

    	return "doctor";
    }

    @RequestMapping(value = "/doctor", method = RequestMethod.GET)
    public String getDoctors(Model model) {
    		model.addAttribute("doctors", this.doctorService.allDoctors());
        	model.addAttribute("role", this.doctorService.getCredentialsService().getRoleAuthenticated());
    		return "doctors";
    }
    @RequestMapping(value = "/doctorFree", method = RequestMethod.GET)
    public String getDoctorsFree(Model model) {
    		model.addAttribute("doctors", this.doctorService.allDoctors());
    		return "doctorsFree";
    }
    
    
    @RequestMapping(value = "/admin/doctor", method = RequestMethod.POST)
    public String addDoctor(@ModelAttribute("doctor") Doctor doctor,@RequestParam("image") MultipartFile multipartFile, 
    									Model model, BindingResult bindingResult)throws IOException {
    	this.doctorValidator.validate(doctor, bindingResult);
        if (!bindingResult.hasErrors()) {
        	String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        	doctor.setPhotos(fileName);
        	
        	Doctor savedDoctor =this.doctorService.insert(doctor);
        	
        	String uploadDir = "doctor-photos/" + savedDoctor.getId();
        	
        	FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        	model.addAttribute("doctors", this.doctorService.allDoctors());
        	model.addAttribute("role", this.doctorService.getCredentialsService().getRoleAuthenticated());
        	
        	return "doctors";
        	}
          return "doctorForm";
}
}
