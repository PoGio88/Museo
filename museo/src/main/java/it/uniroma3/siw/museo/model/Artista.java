package it.uniroma3.siw.museo.model;


import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Artista {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    private String cognome;

    private String luogoNascita;

    private String dataNascita;

    private String luogoMorte;

    private String dataMorte;

    private String nazionalita;
    
    private String foto;

    @OneToMany ( mappedBy = "artista" )
    private List<Opera> opere;
    
    @Transient
	public String getPhotosImagePath() {
		if (foto == null) return null;
		return "/images/foto-artisti/" + id + "/" + foto;
	}
}
