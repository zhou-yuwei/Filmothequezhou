package fr.eni.filmotheque.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.filmotheque.bo.Film;
import fr.eni.filmotheque.service.FilmService;

/**
 * Converter => permet de specifier à Spring comment convertir les champs de formulaire de type Date
 * Pour qu'il soit reconnu par Spring, il faut le rajouter à son contexte
 */
@Component
public class FilmConverter implements Converter<String, Film> {

	@Autowired
	private FilmService filmService;
	
	/**
	 * On doit coder explicitement comment on passe d'une date au format texte => format Genre
	 */
	@Override
	public Film convert(String idAuFormatTexte) {
		long id = Integer.parseInt(idAuFormatTexte);
		return filmService.getFilmById(id);
	}
}
