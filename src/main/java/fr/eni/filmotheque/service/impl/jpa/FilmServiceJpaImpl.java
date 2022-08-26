package fr.eni.filmotheque.service.impl.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.repository.FilmRepository;
import fr.eni.filmotheque.service.FilmService;

@Service
@Profile("prod")
public class FilmServiceJpaImpl implements FilmService {
	
	@Autowired
	private FilmRepository filmRepository;
	
	@Override
	public void addFilm(Film film) {
		
		filmRepository.save(film);

	}

	@Override
	public List<Film> getFilms() {
		return filmRepository.findAll();
	}

	@Override
	public Film getFilmById(long id) {
		return filmRepository.findById(id).get();
	}
	
	@Override
	public void deleteFilmById(long idFilmDelete) {
		filmRepository.deleteById(idFilmDelete);
	}

	@Override
	public void updateFilm(Film film) {
		filmRepository.save(film);
		
	}

}
