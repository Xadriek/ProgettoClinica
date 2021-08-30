package it.uniroma3.siw.spring.controller;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.TypeOfExaminationValidator;
import it.uniroma3.siw.spring.model.TypeOfExamination;
import it.uniroma3.siw.spring.service.TypeOfExaminationService;

@Controller
public class TypeOfExaminationController {
	
	@Autowired
	private TypeOfExaminationService typeOfExaminationinationService;
	
    @Autowired
    private TypeOfExaminationValidator typeOfExaminationinationValidator;
        
    @RequestMapping(value="/admin/typeOfExaminationination", method = RequestMethod.GET)
    public String addTypeOfExamination(Model model) {
    	model.addAttribute("typeOfExaminationination", new TypeOfExamination());
        return "typeOfExaminationinationForm";
    }

   /* @RequestMapping(value = "/typeOfTypeOfExaminationination/{id}", method = RequestMethod.GET)
    public String getTypeOfExamination(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("typeOfTypeOfExaminationination", this.typeOfTypeOfExaminationinationService.typeOfTypeOfExaminationinationById(id));
    	return "typeOfTypeOfExaminationination";
    }*/

    @RequestMapping(value = "/typeOfExaminationination", method = RequestMethod.GET)
    public String getTypeOfExamination(Model model) {
    		model.addAttribute("typeOfExaminationinations", this.typeOfExaminationinationService.allTypeOfExamination());
    		return "typeOfTypeOfExaminationinations";
    }
    
    @RequestMapping(value = "/admin/typeOfExaminationination", method = RequestMethod.POST)
    public String newTypeOfExamination(@ModelAttribute("typeOfExaminationination") TypeOfExamination typeOfExaminationination, 
    									Model model, BindingResult bindingResult) {
    	this.typeOfExaminationinationValidator.validate(typeOfExaminationination, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.typeOfExaminationinationService.insert(typeOfExaminationination);
            model.addAttribute("typeOfExaminationinations", this.typeOfExaminationinationService.allTypeOfExamination());
            return "typeOfExaminationinations";
        }
        return "typeOfExaminationinationForm";
    }
}
