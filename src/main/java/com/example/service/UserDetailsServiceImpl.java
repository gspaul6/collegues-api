package com.example.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entite.Collegue;
import com.example.repository.CollegueRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CollegueRepository collegueRepository;

	public UserDetailsServiceImpl(CollegueRepository collegueRepository) {
		this.collegueRepository = collegueRepository;
	}

	// cette méthode va permettre à Spring Security d'avoir accès
	// aux informations d'un utilisateur (mot de passe, roles) à partir
	// d'un nom utilisateur
	//
	// L'interface UserDetails détaille le contrat attendu par Spring Security.
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		// Recherche d'utilisateur par nom utilisateur
		Collegue collegueFound = collegueRepository.findCollegueByTheirEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

		// Création d'un objet User (implémentant UserDetails)
		return new User(collegueFound.getEmail(), collegueFound.getMotPass(),
				collegueFound.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));

	}
}