package fr.eni.filmotheque.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.eni.filmotheque.bo.Membre;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class Utilisateur implements UserDetails {
//attirbut qui va contenir un membre
	private Membre membre;
	/**
	 * getAuthorities() => doit renyoyer la liste des autorisations de notre utilisateur
	 * si jamais notre membre a l'attribut "admin" à true
	 */
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (membre.isAdmin()) {
			return List.of(new SimpleGrantedAuthority("ROLE_admin"));
			}
			return List.of(new SimpleGrantedAuthority("ROLE_user"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.membre.getMotDePasse();
	}

	@Override
	public String getUsername() {
		// 
		return this.membre.getPseudo();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// Test ce que le compte est bien non bloqué
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// est ce que l'utilisateur a un mot de passe non expiré
		return true;
	}

	@Override
	public boolean isEnabled() {
		//est ce que l'utilisateur est actif -> tous sont actif
		//on retourne true tout le temps
		return true;
	}

}
