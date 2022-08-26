package fr.eni.filmotheque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.filmotheque.bo.Avis;

public interface AvisRepository extends JpaRepository<Avis, Long> {

	List<Avis> findByFilmId(long filmId);
	/**
	 * je rajoute une signature de méthode qui respecte la conventon des Queries Method
	 * -ca doit commencer par findBy ou existsBy
	 * -ensuite on met le mon de l'attribut avce 1ère lettre majuscule sur lequel on va faire la recherche
	 * -la methode va être créée et va effectuer la recherche voulue
	 */

	
	
}
