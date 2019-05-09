package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
	// la méthode handleConflict est exécutée lorsqu'un contrôleur émet une
	// exception présente
	// dans la liste définie par l'annotation @ExceptionHandler
	/*
	 * 2xx=success 3xx=Redirection 4xx=error client-> { bad request ->404 (the
	 * most common)} 5xx=error server
	 * 
	 */
	@ExceptionHandler(value = { CollegueInvalidException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "This should be application specific for runtime exception";
		return ResponseEntity.status(400).body(bodyOfResponse);
	}

	@ExceptionHandler(value = { CollegueNonTrouveException.class })
	protected ResponseEntity<Object> conflictHandle(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Collegue doesnt exist ";
		return ResponseEntity.status(404).body(bodyOfResponse);
	}

	@ExceptionHandler(value = { EmailNotFoundException.class })
	protected ResponseEntity<Object> conflictHandleEmail(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Email does not Exist ";
		return ResponseEntity.status(404).body(bodyOfResponse);
	}

	@ExceptionHandler(value = { CommentaireNonTrouverException.class })
	protected ResponseEntity<Object> conflictHandleCommentaire(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "Commentaire does not Exist ";
		return ResponseEntity.status(404).body(bodyOfResponse);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity mauvaiseInfosConnexion(BadCredentialsException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
