package com.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entite.ColPhotoMatricule;
import com.example.entite.Collegue;
import com.example.entite.CollegueDTO;
import com.example.entite.CollegueModifier;
import com.example.service.CollegueService;
import com.example.utls.DtoUtils;

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
@CrossOrigin
public class CollegueApiController {

	@Autowired
	private CollegueService serviceOfCollegue;

	@GetMapping
	public List<String> searchByName(@RequestParam("nom") String nom) {
		return this.serviceOfCollegue.rechercherParNom(nom).stream().map(Collegue::getMatricule)
				.collect(Collectors.toList());
	}

	// /collegues/email?email=xxx
	@GetMapping("/email")
	public boolean searchByEmail(@RequestParam("email") String email) throws Exception {
		return this.serviceOfCollegue.researcheParEmail(email);
	}

	@GetMapping("/emails")
	public ResponseEntity<Object> rechercherParEmail() {
		CollegueDTO collegue = DtoUtils.ToCollegueDTO(this.serviceOfCollegue
				.collegueParEmail(SecurityContextHolder.getContext().getAuthentication().getName()));

		return ResponseEntity.status(HttpStatus.OK).body(collegue);
	}

	@GetMapping("/all")
	public List<ColPhotoMatricule> searchAll() {

		return this.serviceOfCollegue.researchAllCollegue();
	}

	@GetMapping("/{matricule}")
	public ResponseEntity<Object> recherchermatricules(@PathVariable String matricule) throws Exception {
		Collegue collegueWithMatriculetrouve = this.serviceOfCollegue.rechercherParMatricule(matricule);
		CollegueDTO collegueDto = DtoUtils.ToCollegueDTO(collegueWithMatriculetrouve);
		return ResponseEntity.status(HttpStatus.OK).body(collegueDto);
	}

	@PostMapping
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> create(@RequestBody CollegueDTO collegue) {

		this.serviceOfCollegue.savingColleguesMethod(DtoUtils.ToCollegue(collegue));

		return ResponseEntity.status(HttpStatus.OK).body(collegue);
	}

	@PatchMapping(value = "/{matricule}")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Object> modifierEmail(@PathVariable String matricule,
			@RequestBody CollegueModifier collegueModifier) {
		CollegueDTO newCollegue = null;

		System.out.println(newCollegue);

		if (collegueModifier.getEmail() != null) {
			newCollegue = DtoUtils
					.ToCollegueDTO(this.serviceOfCollegue.modifierEmail(matricule, collegueModifier.getEmail()));
		}
		if (collegueModifier.getPhoto() != null) {
			newCollegue = DtoUtils
					.ToCollegueDTO(this.serviceOfCollegue.modifierPhotoUrl(matricule, collegueModifier.getPhoto()));
		}

		return ResponseEntity.status(HttpStatus.OK).body(newCollegue);
	}

}

// @GetMapping("/me")
// public ResponseEntity<Object> getUtilisateur(@RequestParam("email")
// String email) {
// CollegueUtilisateur collegueUtilisateur =
// (this.serviceOfCollegue.loadUserByname(email));
// return ResponseEntity.status(HttpStatus.OK).body(collegueUtilisateur);
//
// }
