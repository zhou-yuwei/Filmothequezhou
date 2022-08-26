package fr.eni.filmotheque.service.impl.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Participant;
import fr.eni.filmotheque.repository.ParticipantRepository;
import fr.eni.filmotheque.service.ParticipantService;

@Service
@Profile("prod")
public class ParticipantServiceJpaImpl implements ParticipantService {

	@Autowired
	private ParticipantRepository participantRepository;
	
	@Override
	public void addParticipant(Participant participant) throws Exception {

		participantRepository.save(participant);

	}

	@Override
	public List<Participant> listeParticipants() {
		
		return participantRepository.findAll();
	}

	@Override
	public Participant getParticipantById(long id) {
		
		return participantRepository.findById(id).get();
	}
	
	@Override
	public void deleteParticipantById(long idParticipantDelete) {
		participantRepository.deleteById(idParticipantDelete);
		
	}

	@Override
	public void updateParticipant(Participant participant) {
		participantRepository.save(participant);
		
	}

}
