package it.uniroma3.siw.museo.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@NamedQuery(name = "Opera.deleteOperaByCollezione", query = "DELETE FROM Opera p WHERE p.collezione = ?1")

public class Opera {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    
    private String titolo;

    private int anno;  //dobbiamo capire che tipo mettere

    private String descrizione;

    @ManyToOne
    private Artista artista;
    
    private String nomeArtista;

    private String nomeCollezione;

    @ManyToOne
    private Collezione collezione;
}
