package it.uniroma3.siw.museo.controller.validator;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.service.MuseoService;


@Component
public class ArtistaValidator implements Validator {

	@Autowired
	private MuseoService museoService;

	private static final Logger logger = LoggerFactory.getLogger(ArtistaValidator.class);

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		


		if (!errors.hasErrors()) {
			logger.debug("confermato: valori non nulli");
			if (this.museoService.artistaGiaPresente((Artista)o)) {
				logger.debug("e' un duplicato");
				errors.reject("duplicatoArtista");
			}
		}


	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Artista.class.equals(clazz);
	}


}
