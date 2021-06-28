package it.uniroma3.siw.museo.controller;


import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.service.MuseoService;
import it.uniroma3.siw.museo.validator.CollezioneValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AmministratoreController {

    @Autowired
    private MuseoService service;
    @Autowired
    private CollezioneValidator collezioneValidator;

    @RequestMapping(value = "/aggiungiOpera", method = RequestMethod.GET)
    public String aggiungiOpera(Model model) {
        model.addAttribute("opera", new Opera());
        model.addAttribute("collezioni",this.service.tutteLeCollezioni());
        return "admin/operaForm.html";
    }

    @RequestMapping(value = "/aggiungiOpera", method = RequestMethod.POST)
    public String aggiungiOpera(@ModelAttribute("opera") Opera opera, Model model){
        opera.setCollezione(service.collezionePerNome(opera.getNomeCollezione()));
        service.inserisciOpera(opera);
        return "admin/home.html";
    }
    @RequestMapping(value = "/aggiungiCollezione", method = RequestMethod.GET)
    public String aggiungiCollezione(Model model) {
        model.addAttribute("collezione", new Collezione());
        return "admin/collezioneForm.html";
    }

    @RequestMapping(value = "/aggiungiCollezione", method = RequestMethod.POST)
    public String aggiungiCollezione(@ModelAttribute("collezione") Collezione collezione, Model model, BindingResult bindingResult){
    	this.collezioneValidator.validate(collezione, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.service.inserisciCollezione(collezione);
        }
        return "admin/collezioneForm.html";
    }
    
    @RequestMapping(value = "/eliminaCollezione", method = RequestMethod.GET)
    public String eliminaCollezione(Model model) {
        model.addAttribute("collezioni", this.service.tutteLeCollezioni());
        model.addAttribute("collezione", new Collezione());
        return "admin/eliminaCollezione.html";
    }

    @RequestMapping(value = "/eliminaCollezione", method = RequestMethod.POST)
    public String eliminaCollezione(@ModelAttribute("collezione") Collezione collezione, Model model){
    	service.eliminaOpereDaCollezione(service.collezionePerNome(collezione.getNome()));
        service.eliminaCollezione(service.collezionePerNome(collezione.getNome()));
        return "admin/home.html";
    }
    
    
    @RequestMapping(value = "/eliminaOpera", method = RequestMethod.GET)
    public String eliminaOpera(Model model) {
        model.addAttribute("opere", this.service.tutteLeOpere());
        model.addAttribute("opera", new Opera());
        return "admin/eliminaOpera.html";
    }

    @RequestMapping(value = "/eliminaOpera", method = RequestMethod.POST)
    public String eliminaOpera(@ModelAttribute("opera") Opera opera, Model model){
        service.eliminaOpera(service.operaPerTitolo(opera.getTitolo()));
        return "admin/home.html";
    }
    

}
