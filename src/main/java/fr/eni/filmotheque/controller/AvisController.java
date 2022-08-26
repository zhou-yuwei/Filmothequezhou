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

@Controller
public class AvisController {
	
	/*
	 * Comme on doit afficher dans le formulaire 
	 * - la liste des genres
	 * - la liste des participants
	 * Alors on est obligé d'injecter ParticipantService/GenreService en plus de FilmService
	 */
	@Autowired  // va chercher dans le contexte Spring un bean qui correspond au type ParticipantService et il va l'injecter AUTOMATIQUEMENT
	private AvisService avisService;
	@Autowired
	private FilmService filmService;

	
	@GetMapping("/prive/avis")
	private String getAvis(long filmId, Model model) {
		model.addAttribute("film", filmService.getFilmById(filmId));
		model.addAttribute("avis",new Avis());	
		return "avis";
	}
	//@PostMapping("/prive/avis")
	private String postAvis(@AuthenticationPrincipal Utilisateur utilisateurConnecte, @Valid Avis avis, BindingResult br, long filmId, Model model) {
		//on va compléter l'avis envoyé par le formularie avec -le film correspond à l'id, -le membre correspond à l'utilisateur connecté
		//1.faire correspondre film et avis -ajouter le film à l'avis (comme c'est une relation bidrectionnelle, on doit le faire dans les 2 sens)
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
	
}
