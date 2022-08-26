package fr.eni.filmotheque.bo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter @Getter
public class Film {
	@Id 	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty
	private String titre;
	private LocalDate dateSortie;
	
	@Min(0)
	private int duree;
	private String synopsis;
	
	@ManyToOne
	private Genre genre;
	
	@ManyToOne
	private Participant realisateur;
	
	@ManyToMany
	private List<Participant> acteurs = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "film_id")
	@JsonManagedReference 
	private List<Avis> avis;
	//spécifie dans une relation bidirectionnelle (ici Film <-> Avis) c'est cet attribut "avis" qui va contenir le JSON
	
	public Film() {
		avis = new ArrayList<>();
	}
	
	public void addActeurs(Participant acteur) {
		acteurs.add(acteur);
	}
}
