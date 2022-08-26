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

import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.service.AvisService;
import fr.eni.filmotheque.service.FilmService;

@RestController
@CrossOrigin
//@RequestMapping("/api/avis")
@RequestMapping("/api/films/{filmId}/avis")//route imbriquée, pour une ressource qui est dépendante d'une autre, url du type /ressource_parent/{id}ressource_enfant
public class AvisRestController {
		
	@Autowired
	private AvisService avisService;
	
	@Autowired
	private FilmService filmService;
	
/*	@GetMapping("/api/avis")
	public List<Avis> getAvisAll() {
		
		return avisService.getAvis();
	}
	
	//indique qu'on veut récupérer un objet Todo à partir du JSON envoyé
	@PostMapping("/api/avis")
	public Avis postAvisAll(@RequestBody Avis avis) throws Exception {
		avisService.addAvis(avis);
		return avis;
	}
*/	
	@GetMapping()
	public List<Avis> getAvis(@PathVariable long filmId) {
		
		return avisService.getAvisByFilmId(filmId);
	}
	
	@PostMapping
	public Avis postAvis(@PathVariable long filmId, @RequestBody Avis avis) {

			Film film = filmService.getFilmById(filmId);
			avis.setFilm(film);
			avisService.addAvis(avis);
		return avis;		
	}
	
	@PutMapping("/{id}")
	public Avis putAvis(@PathVariable Long id, @RequestBody Avis avis) {
		if(id != null) {
			avis.setId(id);//ne pas oublier afin que le repository fasse un update
			avisService.updateAvis(avis);
		}			
		return avis;
	}
	@DeleteMapping("/{id}")
	public void deleteAvis(@PathVariable Long id) throws Exception {
		if(id != null) {
		
			avisService.deleteAvisById(id);
		}			
	}
	
}
