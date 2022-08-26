package fr.eni.filmotheque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.eni.filmotheque.bo.Membre;

public interface MembreRepository extends JpaRepository<Membre, String> {

	public Membre findByPseudo(String pseudo);
}
