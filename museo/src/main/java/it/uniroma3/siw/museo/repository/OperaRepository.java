package it.uniroma3.siw.museo.repository;


import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Opera;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

public interface OperaRepository extends CrudRepository<Opera, Long> {

	public Optional<Opera> findByCollezione(Collezione collezione);

	public Optional<Opera> findById(Long id);

	public List<Opera> findByArtista(Artista artista);

	@Modifying
	/* Per ottenere l'esecuzione di query di modifica che in realtà richiedono 
	 * solo l'associazione di parametri annotando il metodo di query con @Modifying.
      @Modifying elimina effettivamente tutte le modifiche non scaricate ancora in 
      sospeso in EntityManager. */
	public int deleteOpereByCollezione(Collezione collezione);





}
