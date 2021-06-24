package it.uniroma3.siw.museo.service;

import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.repository.ArtistaRepository;
import it.uniroma3.siw.museo.repository.CollezioneRepository;
import it.uniroma3.siw.museo.repository.OperaRepository;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MuseoService {

    @Autowired
    private OperaRepository operaRepository;

    @Autowired
    private ArtistaRepository artistaRepository;
    
    @Autowired
    private CollezioneRepository collezioneRepository;

    @Transactional
    public Opera inserisciOpera(Opera opera) {
    	return operaRepository.save(opera);
    	}
    
    @Transactional
	public List<Opera> tutteLeOpere() {
		return (List<Opera>) operaRepository.findAll();
	}
    
    @Transactional
   	public List<Collezione> tutteLeCollezioni() {
   		return (List<Collezione>) collezioneRepository.findAll();
   	}
    
    @Transactional
	public Collezione collezionePerId(Long id) {
		Optional<Collezione> optional = collezioneRepository.findById(id);
		return optional.orElse(null);
	}
    
    @Transactional
	public Opera operaPerId(Long id) {
		Optional<Opera> optional = operaRepository.findById(id);
		return optional.orElse(null);
	}

	@Transactional
	public void provaInserisciCollezioni() {
    	this.collezioneRepository.save(new Collezione("ciao"));
		this.collezioneRepository.save(new Collezione("blu"));
		this.collezioneRepository.save(new Collezione("rosso"));
	}

	@Transactional
	public Collezione collezionePerNome(String nome) {
    	return this.collezioneRepository.findByNome(nome).orElse(null);
	}
}
