package it.uniroma3.siw.museo.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.service.MuseoService;


@Component
public class OperaValidator implements Validator {
	
	@Autowired
	private MuseoService museoService;
	
    private static final Logger logger = LoggerFactory.getLogger(CollezioneValidator.class);
    
@Override
public void validate(Object o, Errors errors) {
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo", "required");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descrizione", "required");
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "artista", "required");


	if (!errors.hasErrors()) {
		logger.debug("confermato: valori non nulli");
		if (this.museoService.collezioneAlreadyExists((Collezione)o)) {
			logger.debug("e' un duplicato");
			errors.reject("duplicatoCollezione");
		}
	}
	
	
}

@Override
public boolean supports(Class<?> clazz) {
	return Collezione.class.equals(clazz);
}


}
