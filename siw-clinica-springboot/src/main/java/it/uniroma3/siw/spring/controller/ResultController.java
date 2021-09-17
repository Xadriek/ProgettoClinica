package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.ResultValidator;
import it.uniroma3.siw.spring.model.Result;
import it.uniroma3.siw.spring.service.ResultService;

@Controller
public class ResultController {
	
	@Autowired
	private ResultService resultService;
	
   @Autowired
    private ResultValidator resultValidator;
        
    @RequestMapping(value="/admin/result", method = RequestMethod.GET)
    public String addResult(Model model) {
    	model.addAttribute("result", new Result());
        return "resultForm";
    }

    @RequestMapping(value = "/result/{id}", method = RequestMethod.GET)
    public String getResult(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("result", this.resultService.resultById(id));
    	model.addAttribute("role", this.resultService.getCredentialsService().getRoleAuthenticated());

    	return "result";
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String getResults(Model model) {
    		model.addAttribute("results", this.resultService.allResult());
        	model.addAttribute("role", this.resultService.getCredentialsService().getRoleAuthenticated());
    		return "results";
    }
    
    @RequestMapping(value = "/admin/result", method = RequestMethod.POST)
    public String addResult(@ModelAttribute("result") Result result, 
    									Model model, BindingResult bindingResult) {
    	this.resultValidator.validate(result, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.resultService.insert(result);
            model.addAttribute("results", this.resultService.allResult());
            return "results";
        }
        return "resultForm";
    }
}