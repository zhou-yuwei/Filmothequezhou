package fr.eni.filmotheque.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtUtils {
private String jwtSecret = "secret";
//renvoie la donnée "username" d'un Json web token après l'avoir decodé avec l'algorithme HMAC256 et le secret "secret"
public String generateJwtToken(Authentication authentication) {
Utilisateur userPrincipal = (Utilisateur) authentication.getPrincipal();
//définit un attribut qui serq présente dans la partie "payload" du token
return JWT.create().withClaim("username", userPrincipal.getUsername()).sign(Algorithm.HMAC256(jwtSecret));
}
public String getUserNameFromJwtToken(String token) {
return JWT.require(Algorithm.HMAC256(jwtSecret)).build().verify(token).getClaim("username").asString();
}
public boolean validateJwtToken(String authToken) {
try {
JWT.require(Algorithm.HMAC256(jwtSecret)).build().verify(authToken);
return true;
} catch (Exception e) {
System.out.println("error : " + e.getStackTrace());
}
return false;
}
}