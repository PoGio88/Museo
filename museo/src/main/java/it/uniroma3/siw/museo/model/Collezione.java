package it.uniroma3.siw.museo.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;



@Data
@Entity
public class Collezione {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    @Column (length = 2000)
    private String descrizione;

    @OneToMany(mappedBy = "collezione")
    private List<Opera> opere;

    @ManyToOne
    private Curatore curatore;
}
