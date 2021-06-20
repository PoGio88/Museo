package it.uniroma3.siw.museo.repository;

import it.uniroma3.siw.museo.model.Artista;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistaRepository extends CrudRepository<Long, Artista> {

    public List<Artista> findByNome(String nome);
}
