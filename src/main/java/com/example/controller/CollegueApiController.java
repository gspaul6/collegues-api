package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
@RequestMapping("/collegues")
public class CollegueApiController {
	private CollegueService serviceOfCollegue = new CollegueService();

	@GetMapping
	public List<String> searchByName(@RequestParam("nom") String nom) {
		return this.serviceOfCollegue.rechercherParNom(nom).stream().map(Collegue::getMatricule)
				.collect(Collectors.toList());
	}

	@GetMapping("/{matricule}")
	@ResponseBody // parser l'objet Collegues `GET /collegues/matriculeRecherche
	public Collegue recherchermatricules(@PathVariable String matricule) throws Exception {
		Collegue collegueWithMatriculetrouve = this.serviceOfCollegue.rechercherParMatricule(matricule);

		return collegueWithMatriculetrouve;

	}

	@PostMapping
	public ResponseEntity<Object> create(@RequestBody Collegue collegue) {

		this.serviceOfCollegue.savingColleguesMethod(collegue);

		return ResponseEntity.status(HttpStatus.OK).body(collegue);
	}

}
