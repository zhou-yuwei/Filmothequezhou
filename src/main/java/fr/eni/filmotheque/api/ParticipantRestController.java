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
import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.service.GenreService;
import fr.eni.filmotheque.service.ParticipantService;

@RestController
@CrossOrigin
@RequestMapping("/api/participants")
public class ParticipantRestController {
		
	@Autowired
	private ParticipantService participantService;
	
	@GetMapping
	public List<Participant> getParticipants() {
		
		return participantService.listeParticipants();
	}
	//indique qu'on veut récupérer un objet Todo à partir du JSON envoyé
	@PostMapping
	public Participant postParticipants(@RequestBody Participant participant) throws Exception {
		participantService.addParticipant(participant);
		return participant;
	}
	
	@PutMapping("/{id}")
	public Participant putParticipants(@PathVariable Long id, @RequestBody Participant participant) {
		if(id != null) {
			participant.setId(id);//ne pas oublier afin que le repository fasse un update
			participantService.updateParticipant(participant);
		}			
		return participant;
	}
	@DeleteMapping("/{id}")
	public void deleteParticipants(@PathVariable Long id) throws Exception {
		if(id != null) {
		
			participantService.deleteParticipantById(id);
		}			
	}
	
}
