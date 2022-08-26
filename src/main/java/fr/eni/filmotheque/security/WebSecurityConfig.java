package fr.eni.filmotheque.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;
	@Bean // On définit un bean pour la configuration des routes
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	http.authorizeRequests() // ON AUTORISE
	.antMatchers(HttpMethod.GET, "/**").permitAll() // les requêtes GET
	.antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() // les requêtes OPTIONS
	.antMatchers("/api/login").permitAll() // les requêtes pour se loguer
	.anyRequest().authenticated() // sinon, besoin d'être authentifie, c'est fait au niveau du filtre
	.and().csrf().ignoringAntMatchers("/api/**");
	//.formLogin(); commenté car on ne veut pas de redirection vers le formulaire de login
	/*****************************************************************
	* AVANT DE FAIRE LA VERIFICATION DE SECURITE, ON AJOUTE UN FILTRE
	* qui va vérifier la présence ou non d'un Json Web Token
	******************************************************************/
	http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	return http.build();
	}
	
	/*
	@Bean // On définit un bean pour la configuration des routes
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//**** Sur quels chemins impose-t-on d'être authentifié ****
		http.authorizeRequests().antMatchers("/admin/**").hasRole("admin") // 1 - Si route /admin/*** => besoin d'avoir
																			// le rôle admin
				.antMatchers("/prive/**").authenticated() // 2 - Sinon, si route /prive/*** => besoin d'être authentifie
				.anyRequest().permitAll().and() // 3 - Sinon, on autorise les autres requêtes
				//**** On précise qu'on souhaite une authentification username/password ****
				.formLogin();
		// .loginPage("/login"); si on veut avoir une page personalisée de login
		
		http.csrf().disable();//enlever la securité
		return http.build();
	}
	*/

/**
 * on doit retourner une liste d'utilisatuers (classe user fournie par SpringSecutiry qui seront reconnus pour la connexion
 * 
 */
	/*
	@Bean // on définit un bean pour la gestion des utilisateurs en mémoire
	public InMemoryUserDetailsManager userDetailsService() {
	List<UserDetails> userDetailsList = new ArrayList<>();
	userDetailsList.add(
	User.withUsername("membre").password(passwordEncoder().encode("membre123"))
	.roles("user").build());
	userDetailsList.add(
	User.withUsername("admin").password(passwordEncoder().encode("admin123"))
	.roles("admin", "user").build());
	return new InMemoryUserDetailsManager(userDetailsList);
	}
	*/
	/*
	@Bean // on définit un bean pour l'utilitaire d'encryption de mot de passe
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	*/
}
