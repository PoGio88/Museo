package it.uniroma3.siw.museo.controller;

import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.model.Collezione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.museo.model.Credentials;
import it.uniroma3.siw.museo.service.CredentialsService;
import it.uniroma3.siw.museo.service.MuseoService;


@Controller
public class UtenteController {

    @Autowired
    private MuseoService service;

    @Autowired
    private CredentialsService credentialsService;

    @RequestMapping(value = "/visualizzaOpere", method = RequestMethod.GET)
    public String visualizzaOpere(Model model) {
        model.addAttribute("opere", service.tutteLeOpere());
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        if (SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))
            return "opere.html";
        else return "admin/opere.html";
    }

    @RequestMapping(value = "/visualizzaArtisti", method = RequestMethod.GET)
    public String visualizzaArtisti(Model model) {
        model.addAttribute("artisti", service.tuttiGliArtisti());
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        if (SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))
            return "artisti.html";
        else return "admin/artisti.html";
    }

    @RequestMapping(value = "/artista/{id}", method = RequestMethod.GET)
    public String visualizzaArtista(@PathVariable("id") Long id, Model model) {
        Artista artista = service.artistaPerId(id);
        model.addAttribute("artista", artista);
        model.addAttribute("opere", service.operePerArtista(artista));
        return "artista.html";
    }

    @RequestMapping(value = "/collezioni", method = RequestMethod.GET)
    public String visualizzaCollezione(Model model) {
        model.addAttribute("collezioni", service.tutteLeCollezioni());
        System.out.println(SecurityContextHolder.getContext().getAuthentication());
        if (SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser"))
            return "collezioni.html";
        else return "admin/collezioni.html";
    }

    @RequestMapping(value = "/collezione/{id}", method = RequestMethod.GET)
    public String visualizzaCollezione(@PathVariable("id") Long id, Model model) {
        Collezione collezione = service.collezionePerId(id);
        model.addAttribute("collezione", collezione);
        model.addAttribute("opere", service.operePerCollezione(collezione));
        return "collezione.html";
    }

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "index.html";
    }


}