package it.uniroma3.siw.museo.controller;


import it.uniroma3.siw.museo.model.Opera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AmministratoreController {

    @Autowired
    private MuseoService service;

    @RequestMapping(value = "/aggiungiOpera", method = RequestMethod.GET)
    public String aggiungiOpera(Model model) {
        model.addAttribute("opera", new Opera());
        return "operaForm.html";
    }

    @RequestMapping(value = "/aggiungiOpera", method = RequestMethod.POST)
    public String aggiungiOpera(@ModelAttribute("opera") Opera opera, Model model){
        service.inserisciOpera(opera);
        return "index.html";
    }
}
