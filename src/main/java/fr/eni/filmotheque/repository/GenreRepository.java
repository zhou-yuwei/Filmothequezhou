package fr.eni.filmotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.filmotheque.bo.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
	/**
	 * je rajoute une signature de méthode qui respecte la conventon des Queries Method
	 * -ca doit commencer par findBy ou existsBy
	 * -ensuite on met le mon de l'attribut avce 1ère lettre majuscule sur lequel on va faire la recherche
	 * -la methode va être créée et va effectuer la recherche voulue
	 */
	boolean existsByLibelle(String libelle);

	
}
