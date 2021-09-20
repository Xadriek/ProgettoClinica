package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.RequirementValidator;
import it.uniroma3.siw.spring.model.Requirement;
import it.uniroma3.siw.spring.model.TypeOfExamination;
import it.uniroma3.siw.spring.service.RequirementService;

@Controller
public class RequirementController {
	
	@Autowired
	private RequirementService requirementService;
	
   @Autowired
    private RequirementValidator requirementValidator;
        
    @RequestMapping(value="/admin/requirement", method = RequestMethod.GET)
    public String addRequirement(Model model) {
    	model.addAttribute("requirement", new Requirement());
        return "requirementForm";
    }

    @RequestMapping(value = "/requirement/{id}", method = RequestMethod.GET)
    public String getRequirement(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("requirement", this.requirementService.requirementById(id));
    	model.addAttribute("role", this.requirementService.getCredentialsService().getRoleAuthenticated());

    	return "requirement";
    }
    
    @RequestMapping(value = "/requirement/type", method = RequestMethod.GET)
    public String getRequirementByType(@ModelAttribute("typeOfExamination") TypeOfExamination typeOfExamination, Model model) {
    	model.addAttribute("requirement", this.requirementService.requirementByTypeOfExamination(typeOfExamination));
    	model.addAttribute("role", this.requirementService.getCredentialsService().getRoleAuthenticated());

    	return "requirement";
    }

    @RequestMapping(value = "/requirement", method = RequestMethod.GET)
    public String getRequirements(Model model) {
    		model.addAttribute("requirements", this.requirementService.allRequirements());
        	model.addAttribute("role", this.requirementService.getCredentialsService().getRoleAuthenticated());
    		return "requirements";
    }
    
    @RequestMapping(value = "/admin/requirement", method = RequestMethod.POST)
    public String addRequirement(@ModelAttribute("requirement") Requirement requirement,
    									Model model, BindingResult bindingResult) {
    	this.requirementValidator.validate(requirement, bindingResult);
        if (!bindingResult.hasErrors()) {
        	Requirement req= this.requirementService.insert(requirement);
        	 Requirement _requirement=new Requirement();
             _requirement.setTypeOfExamination(req.getTypeOfExamination());
             model.addAttribute("requirement",_requirement);
            return "requirementForm";
        }
        return "requirementForm";
    }
    @RequestMapping(value = "/requirementComplete", method = RequestMethod.GET)
    public String stopRequirements(@ModelAttribute("requirement") Requirement requirement,Model model) {
    		model.addAttribute("typeOfExamination",requirement.getTypeOfExamination() );
        	model.addAttribute("role", this.requirementService.getCredentialsService().getRoleAuthenticated());
    		return "typeOfExamination";
    }
}
