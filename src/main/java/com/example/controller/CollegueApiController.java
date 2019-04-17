package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entite.Collegue;
import com.example.service.CollegueService;

/**
 * @author gurpr
 *
 */

/*
 * Un contrôleur est un composant de Spring qui permet de gérer une requête*HTTP
 * et produire une réponse HTTP
 */

@RestController
// Ici cette classe va répondre aux requêtes `/exemples`
@RequestMapping("/collegue")
public class CollegueApiController {
	private CollegueService serviceOfCollegue = new CollegueService();

	@GetMapping
	public List<String> searchByName(@RequestParam("nom") String nom) {
		return this.serviceOfCollegue.rechercherParNom(nom).stream().map(Collegue::getMatricule)
				.collect(Collectors.toList());
	}
}
