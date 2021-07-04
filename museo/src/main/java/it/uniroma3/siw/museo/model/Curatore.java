package it.uniroma3.siw.museo.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Curatore {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    private String cognome;

    private String luogoNascita;

    private Date dataNascita;    //controllare tipo

    private String email;

    private String matricola;

    private String telefono;

    @OneToMany (mappedBy = "curatore")
    private List<Collezione> collezione;
}
