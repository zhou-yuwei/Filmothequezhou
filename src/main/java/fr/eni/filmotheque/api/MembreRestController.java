package fr.eni.filmotheque.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.service.GenreService;
import fr.eni.filmotheque.service.MembreService;
import fr.eni.filmotheque.service.ParticipantService;

@RestController
@CrossOrigin
@RequestMapping("/api/membres")
public class MembreRestController {
		
	@Autowired
	private MembreService membreService;
	
	@GetMapping
	public List<Membre> getMembres() {
		
		return membreService.listeMembres();
	}
	//indique qu'on veut récupérer un objet Todo à partir du JSON envoyé
	@PostMapping
	public Membre postMembres(@RequestBody Membre membre) throws Exception {
		membreService.addMembre(membre);
		return membre;
	}
	
	@PutMapping("/{pseudo}")
	public Membre putMembres(@PathVariable String pseudo, @RequestBody Membre membre) {
		if(pseudo != null) {
			membre.setPseudo(pseudo);//ne pas oublier afin que le repository fasse un update
			membreService.updateMembre(membre);
		}			
		return membre;
	}
	@DeleteMapping("/{pseudo}")
	public void deleteMembres(@PathVariable String pseudo) throws Exception {
		if(pseudo != null) {
		
			membreService.deleteMembreByPseudo(pseudo);
		}			
	}
	
}
