package fr.eni.filmotheque.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import fr.eni.filmotheque.bo.Membre;

/**
 * Interface
 * Ce qui sert à specifier les fonctionnalités de notre service
 */
public interface MembreService {
	
	/**
	 * 1. On va avoir besoin de créer des participants
	 */
	public void addMembre(Membre membre) throws Exception;
	
	/**
	 * 2. On va avoir besoind de lister les participants	 * 
	 */
	public List<Membre> listeMembres();
	
	/**
	 * 3. On va avoir besoin de recupérer un participant à partir de son id 
	 */
	//public Membre getMembreById(long id);

	public Membre getMembreByPseudo(String username);

	public void deleteMembreByPseudo(String username);

	public void updateMembre(Membre membre);
}
