package fr.eni.filmotheque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.filmotheque.bo.Avis;
import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.security.Utilisateur;
import fr.eni.filmotheque.service.AvisService;
import fr.eni.filmotheque.service.FilmService;
import fr.eni.filmotheque.service.GenreService;
import fr.eni.filmotheque.service.ParticipantService;

@Controller
public class FilmController {
	
	/*
	 * Comme on doit afficher dans le formulaire 
	 * - la liste des genres
	 * - la liste des participants
	 * Alors on est obligé d'injecter ParticipantService/GenreService en plus de FilmService
	 */
	@Autowired  // va chercher dans le contexte Spring un bean qui correspond au type ParticipantService et il va l'injecter AUTOMATIQUEMENT
	private ParticipantService participantService;
	@Autowired 
	private GenreService genreService;
	@Autowired
	private FilmService filmService;
	@Autowired
	private AvisService avisService;
	/*
	 * J'affiche la liste des films sur une route à part
	 */
	@GetMapping("/")
	private String getFilms(Model model) {
		model.addAttribute("listeFilms",filmService.getFilms());
		return "affichageListeFilms";
	}
	
	/*
	 * J'affiche le détail d'un film sur une route à part
	 */
	@GetMapping("/filmDetail")
	private String getFilmDetail(int id, Model model) {
		model.addAttribute("film",filmService.getFilmById(id));
		model.addAttribute("avis", new Avis());
		return "affichageDetailFilm";
	}
	
	@PostMapping("/prive/filmDetail")
	private String postFilmDetail(@AuthenticationPrincipal Utilisateur utilisateurConnecte, @Valid Avis avis, BindingResult br, long filmId, Model model) {
		Film film;
		if(br.hasErrors()) {
			model.addAttribute("film",filmService.getFilmById(filmId));
			return "avis";
		}
		try{
		film = filmService.getFilmById(filmId);
		avis.setFilm(film);
		film.getAvis().add(avis);
		avis.setMembre(utilisateurConnecte.getMembre());
		avisService.addAvis(avis);
		}
		catch(Exception e) {
		model.addAttribute("erreur", e.getMessage());
		model.addAttribute("film",filmService.getFilmById(filmId));
		return "avis";
		}
		return "redirect:/filmDetail?id=" + film.getId();
	}

	
	@GetMapping("/admin/ajoutFilm")
	private String getFilm(Model model) {
		// afin que mon template remplisse un objet participant, je lui passe en attribut du modèle
		model.addAttribute("film", new Film());
		
		// je passe également la liste des genres/participants pour pouvoir remplir les <select> dur formulaire
		majModeleAvecListes(model);
		return "ajoutFilm";
	}
	
	
	
	@PostMapping("/admin/ajoutFilm")
	private String postFilm(@Valid Film film, BindingResult br, Model model) {
		
		// si on a des erreurs de validations, on retourne  le template pour les afficher
		if (br.hasErrors()) {
			majModeleAvecListes(model);
			return "ajoutFilm";
		}
		
		// creer le participant via participantService
		try {
			filmService.addFilm(film);
		} 
		// si jamais ca se passe mal
		catch (Exception e) {
			majModeleAvecListes(model);
			return "ajoutFilm";
		}
		return "redirect:/";
	}
	
	@PostMapping("/admin/films/delete")
	public String deleteFilm(long idFilmDelete) {

		filmService.deleteFilmById(idFilmDelete);

		return "redirect:/";
	}
	
	@PostMapping("/admin/filmDetail/avisDelete")
	public String deleteAvis(long filmId, long idAvisDelete) {
		Film film = avisService.getAvisById(idAvisDelete).getFilm();
		avisService.deleteAvisById(idAvisDelete);

		return "redirect:/filmDetail?id=" + filmId;
	}
	
	/**
	 * Pour ne pas se répeter, je refactorise mes ajout de listes au modèle dans une méthod à part
	 */
	private void majModeleAvecListes(Model model) {
		model.addAttribute("listeParticipants", participantService.listeParticipants());
		model.addAttribute("listeGenres", genreService.listeGenres());
	}
}
