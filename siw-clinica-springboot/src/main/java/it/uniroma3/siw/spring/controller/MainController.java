package it.uniroma3.siw.spring.controller;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class MainController {
	
	
	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String index(Model model) {
			return "index";
	}
	@RequestMapping(value = {"/chisiamo"}, method = RequestMethod.GET)
	public String chiSiamo(Model model) {
			return "chisiamo";
	}
	@RequestMapping(value = {"/contattaci"}, method = RequestMethod.GET)
	public String contattaci(Model model) {
		return "contattaci";
	}
}