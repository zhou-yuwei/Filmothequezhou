package fr.eni.filmotheque.service.impl.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.repository.AvisRepository;
import fr.eni.filmotheque.service.AvisService;

@Service
@Profile("prod")
public class AvisServiceJpaImpl implements AvisService {

	@Autowired
	private AvisRepository avisRepository;

	@Override
	public void addAvis(Avis avis) {

		avisRepository.save(avis);

	}

	@Override
	public List<Avis> getAvis() {
		return avisRepository.findAll();
	}

	@Override
	public Avis getAvisById(long id) {

		return avisRepository.findById(id).get();

	}

	@Override
	public void deleteAvisById(long idAvisDelete) {
		avisRepository.deleteById(idAvisDelete);
		
	}

	@Override
	public void updateAvis(Avis avis) {
		avisRepository.save(avis);
		
	}

	@Override
	public List<Avis> getAvisByFilmId(long filmId) {
		
		return avisRepository.findByFilmId(filmId);
	}


}
