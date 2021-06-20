package it.uniroma3.siw.museo.model;

import lombok.*;
import javax.persistence.*;
import java.util.List;



@Data
@NoArgsConstructor
@Entity
public class Collezione {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;

    private String nome;

    private String descrizione;

    @OneToMany(mappedBy = "collezione")
    private List<Opera> opere;
    
    


}
