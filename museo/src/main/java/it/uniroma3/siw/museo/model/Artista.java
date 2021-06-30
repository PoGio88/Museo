package it.uniroma3.siw.museo.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Artista {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    private String cognome;

    private String luogoNascita;

    private String dataNascita;    //controllare tipo

    private String luogoMorte;

    private String dataMorte;    //controllare tipo

    private String nazionalita;
    
    private String foto;
    
    private String nomeCompleto;

    @OneToMany ( mappedBy = "artista" )
    private List<Opera> opere;
    
    @Transient
	public String getPhotosImagePath() {
		if (foto == null) return null;
		return "/images/foto-artisti/" + id + "/" + foto;
	}
}
