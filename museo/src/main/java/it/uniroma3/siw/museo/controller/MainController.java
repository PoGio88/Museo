package it.uniroma3.siw.museo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.museo.service.MuseoService;

@Controller
public class MainController {
	
	@Autowired
    private MuseoService service;
	
	@RequestMapping(value = "/visualizzaOpere", method = RequestMethod.GET)
	public String visualizzaOpere(Model model) { 
		model.addAttribute("opere",service.tutteLeOpere());
		return "opere.html" ; }
	
	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String index(Model model) { return "index.html";	}
	
	
}