package fr.eni.filmotheque.service.impl.dev;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.service.MembreService;

@Service // obligatoire pour pouvoir injecter ce service avec @Autowired
@Profile("dev")
public class MembreServiceDevImpl implements MembreService {
	
	// je gère en interne une liste de participants
	private List<Membre> listeMembres = new ArrayList<>();
	private int compteur = 0; // je gère un compteur pour affecter les ids
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	public void addMembre(Membre membre) throws Exception {
		
		// 1. Phase de validation fonctionelle
		if (listeMembres.contains(membre)) {
			throw new Exception("membre déjà existant");
		}
		
		// 2. j'affecte un id à mon participant (sera fait automatiquement avec la base de donnée)
		compteur++;
		//membre.setId(compteur);
		

		// 3. j'ajoute le participant à ma liste de participants
		membre.setMotDePasse(passwordEncoder.encode(membre.getMotDePasse()));
		listeMembres.add(membre);
	}
	/**
	 * Doit me retourner la liste des participants
	 */
	@Override
	public List<Membre> listeMembres() {
		return listeMembres;
	}
	
	/**
	 * Doit me renvoyer le participant qui correspond à l'id passé en paramère
	 */
	/*@Override
	public Membre getMembreById(long id) {
		// je parcours la liste des participants jusqu'à trouvber celui qui correspond
		for (Membre membre : listeMembres) {
			if (membre.getId() == id) {
				return membre;
			}
		}
		// si pas trouvé, je retourne null
		return null;
	}*/
	@Override
	public Membre getMembreByPseudo(String name) {
		for (Membre membre : listeMembres) {
			if (membre.getPseudo().equals(name)) {
				return membre;
			}
		
		}
		return null;
	}
	@Override
	public void deleteMembreByPseudo(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMembre(Membre membre) {
		// TODO Auto-generated method stub
		
	}

}
