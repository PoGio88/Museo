package it.uniroma3.siw.museo.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.museo.model.Collezione;

import java.util.Optional;

public interface CollezioneRepository extends CrudRepository<Collezione, Long>{


    public Optional<Collezione> findByNome(String nome);
    
}
