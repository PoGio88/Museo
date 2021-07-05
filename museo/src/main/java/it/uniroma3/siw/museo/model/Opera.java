package it.uniroma3.siw.museo.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@NamedQuery(name = "Opera.deleteOpereByCollezione", query = "DELETE FROM Opera p WHERE p.collezione = ?1")


public class Opera {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(nullable = false)

	private String titolo;

	private String anno;  

	@Column(length = 2000)
	private String descrizione;

	@ToString.Exclude
	@ManyToOne
    private Artista artista;

	@Column(nullable = true, length = 64)
	private String foto;

	@ToString.Exclude
	@ManyToOne
	private Collezione collezione;

	@Transient
	public String getPhotosImagePath() {
		if (foto == null) return null;
		return "/images/foto-opere/" + id + "/" + foto;
	}
}
