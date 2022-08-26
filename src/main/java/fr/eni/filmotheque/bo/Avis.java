package fr.eni.filmotheque.bo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter  @Setter @NoArgsConstructor
public class Avis {
	@Id 	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Min(0) @Max(5)
	private int note;
	private String commentaire;
	
	@ManyToOne
	private Membre membre;
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JsonBackReference
	private Film film;
	
	public Avis(Film filmById) {
		super();
		this.film = filmById;
	}
}
