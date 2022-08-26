package fr.eni.filmotheque.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.security.Utilisateur;


	@Service
	public class MyUserDetailsService implements UserDetailsService {
		
	@Autowired
	private MembreService membreService;	
	
//	List<Membre> membres = new ArrayList<>();
//	/**
//	 * dans le constructeur, je crée des utilisateurs "bidon" à enlever lorsque j'aurai la base de donnée
//	 */
//	public MyUserDetailsService(PasswordEncoder passwordEncoder) {
//	Membre m1 = new Membre("membre1", passwordEncoder.encode("membre1"), false);
//	Membre m2 = new Membre("membre2", passwordEncoder.encode("membre2"), false);
//	Membre m3 = new Membre("admin", passwordEncoder.encode("admin"), true);
//	membres.add(m1);
//	membres.add(m2);
//	membres.add(m3);
//	}
	
	public MyUserDetailsService(MembreService membreService) throws Exception {
		try {
			membreService.addMembre(new Membre("admin", "admin", true));
		} catch (Exception e) {
		new Exception("admin déjà existant");
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
//	for (Membre membre : membres) {
//	if (membre.getPseudo().equals(username)) {
//	return new Utilisateur(membre);
//	}
//	}
//	throw new UsernameNotFoundException(username);
		
		Membre membre = membreService.getMembreByPseudo(username);
		if(membre == null) {
		throw new UsernameNotFoundException(username);
		}
		else {
			return new Utilisateur(membre);
		}
	}
}

