package it.uniroma3.siw.museo.repository;

import it.uniroma3.siw.museo.model.Artista;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends CrudRepository<Artista, Long> {

    public List<Artista> findByNome(String nome);

    public Optional<Artista> findById(Long id);
    
    public Optional<Artista> findByNomeAndCognome(String nome, String cognome);
}
