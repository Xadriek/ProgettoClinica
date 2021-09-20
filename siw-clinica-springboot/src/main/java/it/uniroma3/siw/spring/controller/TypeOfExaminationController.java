package it.uniroma3.siw.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
        
    @RequestMapping(value="/admin/typeOfExamination", method = RequestMethod.GET)
    public String addTypeOfExamination(Model model) {
    	model.addAttribute("typeOfExamination", new TypeOfExamination());
    	model.addAttribute("requirement",this.requirementService.allRequirements());
        return "typeOfExaminationForm";
    }

   @RequestMapping(value = "/typeOfExamination/{id}", method = RequestMethod.GET)
    public String getTypeOfExamination(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("typeOfExamination", this.typeOfExaminationService.typeOfExaminationById(id));
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
    									
    									Model model, BindingResult bindingResult) {
    	
    	logger.debug("qui ce so arrivato");
        if (!bindingResult.hasErrors()) {
        	
        	
            model.addAttribute("role", this.typeOfExaminationService.getCredentialsService().getRoleAuthenticated());
            Requirement requirement=new Requirement();
            requirement.setTypeOfExamination(typeOfExamination);
            model.addAttribute("requirement",requirement);
            this.typeOfExaminationService.insert(typeOfExamination);
            return "requirementForm";
        }
        return "typeOfExaminationForm";
    }
}
