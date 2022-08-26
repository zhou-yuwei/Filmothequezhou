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
import fr.eni.filmotheque.service.GenreService;

@RestController
@CrossOrigin
@RequestMapping("/api/genres")
public class GenreRestController {
		
	@Autowired
	private GenreService genreService;
	
	@GetMapping
	public List<Genre> getGenres() {
		
		return genreService.listeGenres();
	}
	//indique qu'on veut récupérer un objet Todo à partir du JSON envoyé
	@PostMapping
	public Genre postGenres(@RequestBody Genre genre) throws Exception {
		genreService.addGenre(genre);
		return genre;
	}
	
	@PutMapping("/{id}")
	public Genre putGenre(@PathVariable Long id, @RequestBody Genre genre) {
		if(id != null) {
			genre.setId(id);//ne pas oublier afin que le repository fasse un update
			genreService.updateGenre(genre);
		}			
		return genre;
	}
	@DeleteMapping("/{id}")
	public void deleteGenre(@PathVariable Long id) throws Exception {
		if(id != null) {
		
			genreService.deleteGenreById(id);
		}			
	}
	
}
