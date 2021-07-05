package it.uniroma3.siw.museo.service;

import it.uniroma3.siw.museo.model.Artista;
import it.uniroma3.siw.museo.model.Collezione;
import it.uniroma3.siw.museo.model.Curatore;
import it.uniroma3.siw.museo.model.Opera;
import it.uniroma3.siw.museo.repository.ArtistaRepository;
import it.uniroma3.siw.museo.repository.CollezioneRepository;
import it.uniroma3.siw.museo.repository.CuratoreRepository;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MuseoService {

	@Autowired
	private OperaRepository operaRepository;

	@Autowired
	private ArtistaRepository artistaRepository;

	@Autowired
	private CollezioneRepository collezioneRepository;

	@Autowired
	private CuratoreRepository curatoreRepository;

	@Transactional
	public Opera inserisciOpera(Opera opera) {
		return operaRepository.save(opera);
	}

	@Transactional
	public List<Opera> tutteLeOpere() {
		return (List<Opera>) operaRepository.findAll();
	}

	@Transactional
	public Opera operaPerId(Long id) {
		Optional<Opera> optional = operaRepository.findById(id);
		return optional.orElse(null);
	}

	@Transactional
	public boolean operaGiaPresente(Opera opera) {
		Optional<Opera> op = this.operaRepository.findByTitoloAndArtista(opera.getTitolo(),opera.getArtista());
		return op.isPresent();
	}

	@Transactional
	public List<Opera> operePerArtista(Artista artista) {
		return operaRepository.findByArtista(artista);
	}

	@Transactional
	public List<Opera> operePerCollezione(Collezione collezione) { return operaRepository.findByCollezione(collezione); }

	@Transactional
	public void eliminaOpera(Opera opera) {
		operaRepository.delete(opera);
	}

	@Transactional
	public Collezione inserisciCollezione(Collezione collezione) {
		return collezioneRepository.save(collezione);
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
	public void eliminaCollezione(Collezione collezione) {
		collezioneRepository.delete(collezione);
		operaRepository.deleteOpereByCollezione(collezione);
	}

	@Transactional
	public boolean collezioneGiaPresente(Collezione collezione) {
		Optional<Collezione> coll = this.collezioneRepository.findByNome(collezione.getNome());
		return coll.isPresent();
	}

	@Transactional
	public Artista inserisciArtista(Artista artista) {
		return artistaRepository.save(artista);
	}

	@Transactional
	public List<Artista> tuttiGliArtisti() {
		return (List<Artista>) artistaRepository.findAll();
	}

	@Transactional
	public Artista artistaPerId(Long id) {
		Optional<Artista> artista = artistaRepository.findById(id);
		return artista.orElse(null);
	}

	@Transactional
	public boolean artistaGiaPresente(Artista artista) {
		Optional<Artista> art = this.artistaRepository.findByNomeAndCognome(artista.getNome(), artista.getCognome());
		return art.isPresent();
	}

	@Transactional
	public List<Curatore> tuttiICuratori() {
		return (List<Curatore>) curatoreRepository.findAll();
	}

	@Transactional
	public Curatore curatorePerCollezione(Collezione collezione) {
		Optional<Curatore> optional = curatoreRepository.findByCollezione(collezione);
		return optional.orElse(null);
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

    public void identificaAmministratoreNelModel(Model model) {
		model.addAttribute("admin",true);
	}

}
