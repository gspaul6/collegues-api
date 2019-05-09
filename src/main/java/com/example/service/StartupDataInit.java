package com.example.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.entite.Collegue;

@Component
public class StartupDataInit {

	private static final Logger LOG = LoggerFactory.getLogger(StartupDataInit.class);

	@Autowired
	CollegueService service;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// La méthode init va être invoquée au démarrage de l'application.
	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		LOG.error("hello starting");

		service.savingColleguesMethod(
				new Collegue(UUID.randomUUID().toString(), "Marty", "Nicolas", "martynicolas@gmail.com",
						LocalDate.of(1997, 01, 01), "https://d2gg9evh47fn9z.cloudfront.net/800px_COLOURBOX18400141.jpg",
						passwordEncoder.encode("pass1"), Arrays.asList("ROLE_ADMIN")));

		service.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Boss", "Hugo", "bosshugo@gmail.com",
				LocalDate.of(1997, 12, 12),
				"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSc1RCr83mpi6e0hRy6Ti-uCRCOBN_KCovDZqpxx_KHMe_iRdH9",
				passwordEncoder.encode("pass2"), Arrays.asList("ROLE_USER")));

		service.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Tikal", "Tikka",
				"tikaltikka@gmail.com", LocalDate.of(1994, 03, 04),
				"https://cdn1.iconfinder.com/data/icons/avatars-55/100/avatar_profile_user_music_headphones_shirt_cool-512.png",
				passwordEncoder.encode("pass2"), Arrays.asList("ROLE_USER")));

		service.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Polinski", "Nicolas",
				"polinskinicolas@gmail.com", LocalDate.of(1996, 12, 12),
				"http://www.soidergi.com/wp-content/uploads/ph/thumb-photostock-vector-elegant-bearded-man-face-in-sunglasses-vector-hipster-character-fashion-silhouette-avatar-emblem-log.jpg",
				passwordEncoder.encode("pass2"), Arrays.asList("ROLE_USER")));

		service.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Russe", "Ygurpratap",
				"russeygurpratap@gmail.com", LocalDate.of(1988, 05, 06),
				"http://www.arenawp.com/wp-content/uploads/st/thumb-stock-illustration-man-silhouette-hipster-style-vector-illustration-image.jpg",
				passwordEncoder.encode("pass2"), Arrays.asList("ROLE_USER")));

	}
}