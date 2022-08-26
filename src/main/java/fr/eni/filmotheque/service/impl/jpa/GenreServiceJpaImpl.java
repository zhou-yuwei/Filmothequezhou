package fr.eni.filmotheque.service.impl.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Genre;
import fr.eni.filmotheque.repository.FilmRepository;
import fr.eni.filmotheque.repository.GenreRepository;
import fr.eni.filmotheque.service.GenreService;

@Service
@Profile("prod")
public class GenreServiceJpaImpl implements GenreService {
	//se servir du repository GenreRepository pour gérer les genres
	@Autowired
	private GenreRepository genreRepository;
	@Autowired 
	private FilmRepository filmRepository;
	
	@Override
	public void addGenre(Genre genre) throws Exception {
		if(genreRepository.existsByLibelle(genre.getLibelle())) {
			throw new Exception("genre existant déjà");
		}
		//pour sauvegarder en base de donnée à partir du repository : save()
		genreRepository.save(genre);
	}

	@Override
	public List<Genre> listeGenres() {
		//pour recupérer la liste de tous les objets correspondants aux lignes d'une table : findAll()
		return genreRepository.findAll();
	}

	@Override
	public Genre getGenreById(long id) {
		//pour recupérer un objet à partir de son id : findById().get()
		return genreRepository.findById(id).get();
	}
	
	@Override
	public void deleteGenreById(long idGenreDelete) throws Exception {
		
		if (filmRepository.existsByGenreId(idGenreDelete)) {
			throw new Exception("je ne peux pas...");
		}
		
		genreRepository.deleteById(idGenreDelete);
		
	}

	@Override
	public void updateGenre(Genre genre) {
		genreRepository.save(genre);
		
	}

}
