package com.example.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entite.CollegueAuth;
import com.example.entite.CollegueUtilisateur;
import com.example.service.CollegueService;

import io.jsonwebtoken.Jwts;

@RestController
@CrossOrigin
public class AuthentificationCtrl {

	@Value("${jwt.expires_in}")
	private Integer EXPIRES_IN;

	@Value("${jwt.cookie}")
	private String TOKEN_COOKIE;

	@Value("${jwt.secret}")
	private String SECRET;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CollegueService serviceOfCollegue;

	@PostMapping(value = "/auth")
	public ResponseEntity authenticate(@RequestBody CollegueAuth authenticationRequest, HttpServletResponse response) {

		// encapsulation des informations de connexion
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				authenticationRequest.getNom(), authenticationRequest.getMotPass());

		// vérification de l'authentification
		// une exception de type `BadCredentialsException` en cas d'informations
		// non valides
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		User user = (User) authentication.getPrincipal();

		String rolesList = user.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(","));

		Map<String, Object> infosSupplementaireToken = new HashMap<>();
		infosSupplementaireToken.put("roles", rolesList);

		String jetonJWT = Jwts.builder().setSubject(user.getUsername()).addClaims(infosSupplementaireToken)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRES_IN * 1000))
				.signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, SECRET).compact();

		Cookie authCookie = new Cookie(TOKEN_COOKIE, jetonJWT);
		authCookie.setHttpOnly(true);
		authCookie.setMaxAge(EXPIRES_IN * 1000);
		authCookie.setPath("/");
		response.addCookie(authCookie);
		CollegueUtilisateur collegueUtilisateur = this.serviceOfCollegue.loadUserByname(authenticationRequest.getNom());
		return ResponseEntity.ok().body(collegueUtilisateur);

	}
}