package fr.eni.filmotheque.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.eni.filmotheque.bo.Membre;
import fr.eni.filmotheque.service.MembreService;

/**
 * Converter => permet de specifier à Spring comment convertir les champs de formulaire de type Date
 * Pour qu'il soit reconnu par Spring, il faut le rajouter à son contexte
 */
@Component
public class MembreConverter implements Converter<String, Membre> {

	@Autowired
	private MembreService membreService;
	
	/**
	 * On doit coder explicitement comment on passe d'une date au format texte => format Participant
	 */
	@Override
	public Membre convert(String pseudoAuFormatTexte) {
		//long id = Integer.parseInt(pseudoAuFormatTexte);
		return membreService.getMembreByPseudo(pseudoAuFormatTexte);
	}
}
