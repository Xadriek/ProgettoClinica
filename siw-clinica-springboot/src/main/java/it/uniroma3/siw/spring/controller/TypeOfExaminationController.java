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

import it.uniroma3.siw.spring.controller.validator.TypeOfExaminationValidator;
import it.uniroma3.siw.spring.model.Requirement;
import it.uniroma3.siw.spring.model.TypeOfExamination;
import it.uniroma3.siw.spring.service.RequirementService;
import it.uniroma3.siw.spring.service.TypeOfExaminationService;

@Controller
public class TypeOfExaminationController {
	
	@Autowired
	private TypeOfExaminationService typeOfExaminationService;
	
    @Autowired
    private TypeOfExaminationValidator typeOfExaminationValidator;
    
    @Autowired
	private RequirementService requirementService;
        
    @RequestMapping(value="/admin/typeOfExamination", method = RequestMethod.GET)
    public String addTypeOfExamination(Model model) {
    	model.addAttribute("typeOfExamination", new TypeOfExamination());
    	model.addAttribute("requirement",this.requirementService.allRequirements());
        return "typeOfExaminationForm";
    }

   @RequestMapping(value = "/typeOfExamination/{id}", method = RequestMethod.GET)
    public String getTypeOfExamination(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("typeOfTypeOfExaminationination", this.typeOfExaminationService.typeOfExaminationById(id));
    	model.addAttribute("role", this.typeOfExaminationService.getCredentialsService().getRoleAuthenticated());
    	return "typeOfExamination";
    }

    @RequestMapping(value = "/typeOfExaminationFree", method = RequestMethod.GET)
    public String getTypeOfExaminationFree(Model model) {
    		model.addAttribute("typeOfExaminations", this.typeOfExaminationService.allTypeOfExamination());
    		
  
    		
    		return "typeOfExaminationsFree";
    }
    @RequestMapping(value = "/typeOfExamination", method = RequestMethod.GET)
    public String getTypeOfExamination(Model model) {
    		model.addAttribute("typeOfExaminations", this.typeOfExaminationService.allTypeOfExamination());
    		
    		model.addAttribute("role", this.typeOfExaminationService.getCredentialsService().getRoleAuthenticated());
    		
    		return "typeOfExaminations";
    }
    
    @RequestMapping(value = "/admin/typeOfExamination", method = RequestMethod.POST)
    public String newTypeOfExamination(@ModelAttribute("typeOfExamination") TypeOfExamination typeOfExamination,
    									@ModelAttribute("requirement1") Requirement requirement1,
    									@ModelAttribute("requirement2") Requirement requirement2,
    									@ModelAttribute("requirement3") Requirement requirement3,
    									@ModelAttribute("requirement4") Requirement requirement4,
    									@ModelAttribute("requirement5") Requirement requirement5,
    									@ModelAttribute("requirement6") Requirement requirement6,
    									Model model, BindingResult bindingResult) {
    	this.typeOfExaminationValidator.validate(typeOfExamination, bindingResult);
        if (!bindingResult.hasErrors()) {
        	
        	typeOfExamination.getRequirements().add(requirement1);
        	typeOfExamination.getRequirements().add(requirement2);
        	typeOfExamination.getRequirements().add(requirement3);
        	typeOfExamination.getRequirements().add(requirement4);
        	typeOfExamination.getRequirements().add(requirement5);
        	typeOfExamination.getRequirements().add(requirement6);
        	this.typeOfExaminationService.insert(typeOfExamination);
        	
            model.addAttribute("typeOfExaminations", this.typeOfExaminationService.allTypeOfExamination());
            return "typeOfExaminations";
        }
        return "typeOfExaminationForm";
    }
}
