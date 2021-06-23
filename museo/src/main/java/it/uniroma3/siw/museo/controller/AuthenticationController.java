package it.uniroma3.siw.museo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import it.uniroma3.siw.museo.controller.validator.CredentialsValidator;
//import it.uniroma3.siw.museo.controller.validator.UserValidator;
import it.uniroma3.siw.museo.service.CredentialsService;
import it.uniroma3.siw.museo.model.Credentials;

@Controller
public class AuthenticationController {
	
	@Autowired
	private CredentialsService credentialsService;
	
	//@Autowired
	//private UserValidator userValidator;
	
	//@Autowired
	//private CredentialsValidator credentialsValidator;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	public String mostraLoginForm (Model model) {
		return "loginForm";
	}
	
	@RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultDopoLogin(Model model) {
        return "admin/home";
    }
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET) 
	public String logout(Model model) {
		return "index";
	}
}
