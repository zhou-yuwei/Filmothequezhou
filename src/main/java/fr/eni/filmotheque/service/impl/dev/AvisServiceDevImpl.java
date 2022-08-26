package fr.eni.filmotheque.service.impl.dev;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.service.AvisService;

@Service
@Profile("dev")
public class AvisServiceDevImpl implements AvisService {

	private List<Avis> listeAvis = new ArrayList<>();
	private int compteur = 0; // je gère un compteur pour affecter les ids

	@Override
	public void addAvis(Avis avis) {
		compteur++;
		avis.setId(compteur);

		// 3. j'ajoute le genre à ma liste de genres
		listeAvis.add(avis);

	}

	@Override
	public List<Avis> getAvis() {
		return listeAvis;
	}

	@Override
	public Avis getAvisById(long id) {
		// je parcours la liste des films jusqu'à trouvber celui qui correspond
		for (Avis avis : listeAvis) {
			if (avis.getId() == id) {
				return avis;
			}
		}
		// si pas trouvé, je retourne null
		return null;
	}

	@Override
	public void deleteAvisById(long idAvisDelete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAvis(Avis avis) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Avis> getAvisByFilmId(long filmId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public List<Avis> getAvisByFilmId(long filmId) {
		List<Avis> listeAvisByFilmId = new ArrayList<>();
		for (Avis element : listeAvis) {
			if(element.getFilm().getId()==filmId)
				listeAvisByFilmId.add(element);
		}
		return listeAvisByFilmId;
	}*/

}
