package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/admin/patient", method = RequestMethod.GET)
    public String getPatients(Model model) {
    		model.addAttribute("patients", this.userService.getAllUsers());
    		model.addAttribute("role", this.userService.getCredentialsService().getRoleAuthenticated());
    		return "patients";
}
	@RequestMapping(value = "/admin/patient/{id}", method = RequestMethod.GET)
    public String getExam(@PathVariable("id") Long id, Model model) {
		
    	model.addAttribute("patient", this.userService.getUser(id));
    	model.addAttribute("role", this.userService.getCredentialsService().getRoleAuthenticated());

    	return "patient";
    }
}
