package it.uniroma3.siw.museo.repository;

import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Curatore;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CuratoreRepository extends CrudRepository<Curatore,Long> {

    public Optional<Curatore> findByCollezione(Collezione collezione);
}
