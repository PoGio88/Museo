package it.uniroma3.siw.museo.service;

import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.repository.ArtistaRepository;
import it.uniroma3.siw.museo.repository.CollezioneRepository;
import it.uniroma3.siw.museo.repository.OperaRepository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
	public List<Artista> tuttiGliArtisti() {
		return (List<Artista>) artistaRepository.findAll();
	}

	@Transactional
	public Collezione collezionePerId(Long id) {
		Optional<Collezione> optional = collezioneRepository.findById(id);
		return optional.orElse(null);
	}

	@Transactional
	public Opera operaPerTitolo(String titolo) {
		Optional<Opera> optional = operaRepository.findByTitolo(titolo);
		return optional.orElse(null);
	}

	@Transactional
	public Artista inserisciArtista(Artista artista) {
		return artistaRepository.save(artista);
	}

	@Transactional
	public Collezione inserisciCollezione(Collezione collezione) {
		return collezioneRepository.save(collezione);
	}

	@Transactional
	public void eliminaCollezione(Collezione collezione) {
		collezioneRepository.delete(collezione);
	}
	
	@Transactional
	public void eliminaOpera(Opera opera) {
		operaRepository.delete(opera);
	}

	@Transactional
	public void eliminaOpereDaCollezione(Collezione collezione) {
		operaRepository.deleteOperaByCollezione(collezione);
	}

	@Transactional
	public Collezione collezionePerNome(String nome) {
		return this.collezioneRepository.findByNome(nome);
	}
	
	@Transactional
	public boolean collezioneAlreadyExists(Collezione collezione) {
		Collezione coll = this.collezioneRepository.findByNome(collezione.getNome());
		if (coll != null)
			return true;
		else 
			return false;
	}
	public void saveImage(String uploadDir, String fileName, MultipartFile immagine) throws Exception {
		
		Path uploadPath = Paths.get(uploadDir);
        
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
         
        try (InputStream inputStream = immagine.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {        
            throw new IOException("Could not save image file: " + fileName, ioe);
        }      
    }

	@Transactional
	public Artista artistaPerNomeECognome(String nomeArtista) {
		Scanner stringa = new Scanner(nomeArtista);
		String nome = null;
		String cognome = null;
		if(stringa.hasNext())
		nome = stringa.next();
		if(stringa.hasNext())
	    cognome = stringa.next();
		if(stringa.hasNext())
		cognome = cognome + " "+ stringa.next();
		return this.artistaRepository.findByNomeAndCognome(nome, cognome);
	}

	@Transactional
	public void elminaOpereDiArtista(Artista artistaDaEliminare) {
		this.operaRepository.deleteOpereByArtista(artistaDaEliminare);
		
	}

	@Transactional
	public void eliminaArtista(Artista artistaDaEliminare) {
		this.artistaRepository.delete(artistaDaEliminare);
		}

	@Transactional
	public boolean artistaAlreadyExists(Artista artista) {
		Artista art = this.artistaRepository.findByNomeAndCognome(artista.getNome(), artista.getCognome());
		if (art != null)
			return true;
		else 
			return false;
	}
	
	
	
}
