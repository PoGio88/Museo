package it.uniroma3.siw.museo.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.museo.model.Collezione;

import java.util.List;
import java.util.Optional;

public interface CollezioneRepository extends CrudRepository<Collezione, Long>{


    public Collezione findByNome(String nome);
    
}
