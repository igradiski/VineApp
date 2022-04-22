package com.hr.igz.VineApp.security.jwt;

import com.hr.igz.VineApp.controller.VinogradHasLozaController;
import com.hr.igz.VineApp.security.servicesImpl.UserDetailsSecurityImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
	
	@Value("${vineApp.jwtSecret}")
	private String jwtSecret;

	@Value("${vineApp.jwtExpiration}")
	private int jwtExpirationMs;

	private Logger log = LoggerFactory.getLogger(JwtUtils.class);

	public String generateJwtToken(UserDetailsSecurityImpl userPrincipal) {
	    return generateTokenFromUsername(userPrincipal.getUsername());
	  }
	
	public String generateTokenFromUsername(String username) {
	    return Jwts.builder().setSubject(username).setIssuedAt(new Date())
	        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
	        .compact();
	  }

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			log.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			log.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

}
