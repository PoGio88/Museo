package it.uniroma3.siw.museo.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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
}
