package com.example.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entite.CommentaireCollegue;
import com.example.entite.CommentaireCollegueDTO;
import com.example.service.CollegueService;
import com.example.service.CommentaireService;
import com.example.utls.DtoUtils;

@RestController
// Ici cette classe va répondre aux requêtes `/exemples`
@RequestMapping("/collegues")
@CrossOrigin
public class CommentaireApiController {

	@Autowired
	private CommentaireService serviceOfCommentaire;

	@Autowired
	private CollegueService serviceOfCollegue;

	// GET /collegues/matriculeComment?matriculeComment=xxx
	@GetMapping(value = "/{matricule}/commentaire")
	public List<CommentaireCollegueDTO> searchByMatricule(@PathVariable("matricule") String matricule) {

		return this.serviceOfCommentaire.researchCommentaireParMatricule(matricule).stream()
				.map(DtoUtils::toCommentaireCollegueDTO)
				.collect(Collectors.toList());
	}

	@PostMapping(value = "/{matricule}/commentaire")
	public ResponseEntity<Object> create(@RequestBody CommentaireCollegueDTO commentaire,
			@PathVariable("matricule") String matricule) {

        commentaire.setDateCreated(LocalDateTime.now());
		
		CommentaireCollegueDTO newCommentaire = serviceOfCommentaire.savingCommentaire(matricule, commentaire);
		
		return ResponseEntity.status(HttpStatus.OK).body(newCommentaire);
	}

	@DeleteMapping(value = "/{matricule}/commentaire/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {

		this.serviceOfCommentaire.supremeCommentaireParmatricule(id);

		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
