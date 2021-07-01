package it.uniroma3.siw.museo.controller;


import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.service.MuseoService;
import it.uniroma3.siw.museo.controller.validator.ArtistaValidator;
import it.uniroma3.siw.museo.controller.validator.CollezioneValidator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class AmministratoreController {

    @Autowired
    private MuseoService service;

    @Autowired
    private CollezioneValidator collezioneValidator;

    @Autowired
    private ArtistaValidator artistaValidator;

    @RequestMapping(value = "/aggiungiOpera", method = RequestMethod.GET)
    public String aggiungiOpera(Model model) {
        model.addAttribute("opera", new Opera());
        model.addAttribute("collezioni", this.service.tutteLeCollezioni());
        model.addAttribute("artisti", this.service.tuttiGliArtisti());
        return "admin/operaForm.html";
    }

    @RequestMapping(value = "/aggiungiOpera", method = RequestMethod.POST)
    public String aggiungiOpera(@ModelAttribute("opera") Opera opera, @RequestParam("immagine") MultipartFile immagine, Model model) throws Exception {
        String fileName = StringUtils.cleanPath(immagine.getOriginalFilename());
        opera.setFoto(fileName);
        Opera operaSalvata = service.inserisciOpera(opera);
        String uploadDir = "src/main/resources/static/images/foto-opere/" + operaSalvata.getId();
        service.saveImage(uploadDir, fileName, immagine);
        return "admin/home.html";
    }

    @RequestMapping(value = "/aggiungiArtista", method = RequestMethod.GET)
    public String aggiungiArtista(Model model) {
        model.addAttribute("artista", new Artista());
        return "admin/artistaForm.html";
    }

    @RequestMapping(value = "/aggiungiArtista", method = RequestMethod.POST)
    public String aggiungiArtista(@ModelAttribute("artista") Artista artista, @RequestParam("immagine") MultipartFile immagine, Model model, BindingResult bindingResult) throws Exception {
        this.artistaValidator.validate(artista, bindingResult);
        if (!bindingResult.hasErrors()) {
            String fileName = StringUtils.cleanPath(immagine.getOriginalFilename());
            artista.setFoto(fileName);
            Artista artistaSalvato = service.inserisciArtista(artista);
            String uploadDir = "src/main/resources/static/images/foto-artisti/" + artistaSalvato.getId();
            service.saveImage(uploadDir, fileName, immagine);
            return "admin/home.html";
        }
        return "admin/artistaForm";
    }


    @RequestMapping(value = "/aggiungiCollezione", method = RequestMethod.GET)
    public String aggiungiCollezione(Model model) {
        model.addAttribute("collezione", new Collezione());
        return "admin/collezioneForm.html";
    }

    @RequestMapping(value = "/aggiungiCollezione", method = RequestMethod.POST)
    public String aggiungiCollezione(@ModelAttribute("collezione") Collezione collezione, Model model, BindingResult bindingResult) {
        this.collezioneValidator.validate(collezione, bindingResult);
        if (!bindingResult.hasErrors()) {
            this.service.inserisciCollezione(collezione);
            return "admin/home.html";
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
    public String eliminaCollezione(@ModelAttribute("collezione") Collezione collezione, Model model) {
        Collezione collezioneDaEliminare = service.collezionePerId(collezione.getId());
        service.eliminaCollezione(collezioneDaEliminare);
        return "admin/home.html";
    }

    @RequestMapping(value = "/eliminaOpera", method = RequestMethod.GET)
    public String eliminaOpera(Model model) {
        model.addAttribute("opere", this.service.tutteLeOpere());
        model.addAttribute("opera", new Opera());
        return "admin/eliminaOpera.html";
    }

    @RequestMapping(value = "/eliminaOpera", method = RequestMethod.POST)
    public String eliminaOpera(@ModelAttribute("opera") Opera opera, Model model) {
        service.eliminaOpera(service.operaPerId(opera.getId()));
        return "admin/home.html";
    }


}
