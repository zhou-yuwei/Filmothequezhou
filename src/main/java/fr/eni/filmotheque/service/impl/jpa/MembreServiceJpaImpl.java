package fr.eni.filmotheque.service.impl.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.repository.MembreRepository;
import fr.eni.filmotheque.service.MembreService;

@Service
@Profile("prod")
public class MembreServiceJpaImpl implements MembreService {

	@Autowired
	private MembreRepository membreRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void addMembre(Membre membre) throws Exception {
		membre.setMotDePasse(passwordEncoder.encode(membre.getMotDePasse()));
		membreRepository.save(membre);

	}

	@Override
	public List<Membre> listeMembres() {
		
		return membreRepository.findAll();
	}
	
	@Override
	public Membre getMembreByPseudo(String username) {
		return membreRepository.findByPseudo(username);
		
	}
	
	@Override
	public void deleteMembreByPseudo(String username) {
		Membre membre = membreRepository.findByPseudo(username);
		membreRepository.delete(membre);
		
	}

	@Override
	public void updateMembre(Membre membre) {
		// TODO Auto-generated method stub
		
	}

}
