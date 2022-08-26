package fr.eni.filmotheque.bo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString 
public class Membre {
	//private long id;
	@Id
	private String pseudo;
	@NotEmpty
	private String motDePasse;
	private boolean admin;
	
	public Membre(String pseudo, String motDePasse, boolean admin) {
		super();
		this.pseudo = pseudo;
		this.motDePasse = motDePasse;
		this.admin = admin;
	}
	
}
