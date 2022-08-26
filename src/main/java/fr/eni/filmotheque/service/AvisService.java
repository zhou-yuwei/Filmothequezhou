package fr.eni.filmotheque.service;

import java.util.List;

import fr.eni.filmotheque.bo.Avis;

public interface AvisService {
	
	public void addAvis(Avis avis);
	
	public List<Avis> getAvis();
	
	public Avis getAvisById(long id);

	public void deleteAvisById(long idAvisDelete);

	public void updateAvis(Avis avis);

	public List<Avis> getAvisByFilmId(long filmId);

	

}
