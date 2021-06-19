package it.uniroma3.siw.museo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Opera {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    private String titolo;

    private int anno;  //dobbiamo capire che tipo mettere

    private String descrizione;

    @ManyToOne
    private Artista artista;
}
