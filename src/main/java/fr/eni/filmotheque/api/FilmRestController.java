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

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.service.FilmService;

@RestController
@CrossOrigin
@RequestMapping("/api/films")
public class FilmRestController {
		
	@Autowired
	private FilmService filmService;
	
	@GetMapping
	public List<Film> getFilms() {
		
		return filmService.getFilms();
	}
	
	@GetMapping("/{id}")
	public Film getFilmsById(@PathVariable long id) {
		
		return filmService.getFilmById(id);
	}
	
	//indique qu'on veut récupérer un objet Todo à partir du JSON envoyé
	@PostMapping
	public Film postFilms(@RequestBody Film film) throws Exception {
		filmService.addFilm(film);
		return film;
	}
	
	@PutMapping("/{id}")
	public Film putFilm(@PathVariable Long id, @RequestBody Film film) {
		if(id != null) {
			film.setId(id);//ne pas oublier afin que le repository fasse un update
			filmService.updateFilm(film);
		}			
		return film;
	}
	@DeleteMapping("/{id}")
	public void deleteFilms(@PathVariable Long id) throws Exception {
		if(id != null) {
		
			filmService.deleteFilmById(id);
		}			
	}
	
}
