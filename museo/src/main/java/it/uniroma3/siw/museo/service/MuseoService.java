package it.uniroma3.siw.museo.service;

import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.repository.ArtistaRepository;
import it.uniroma3.siw.museo.repository.OperaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MuseoService {

    @Autowired
    private OperaRepository operaRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @Transactional
    public Opera inserisciOpera() {

    }
}
