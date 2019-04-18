package com.example.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
	// la méthode handleConflict est exécutée lorsqu'un contrôleur émet une
	// exception présente
	// dans la liste définie par l'annotation @ExceptionHandler
	@ExceptionHandler(value = { CollegueInvalidException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "This should be application specific for runtime exception";
		return ResponseEntity.status(400).body(bodyOfResponse);
	}

	@ExceptionHandler(value = { CollegueNonTrouveException.class })
	protected ResponseEntity<Object> conflictHandle(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "This should be application specific, which deals with Web request ";
		return ResponseEntity.status(404).body(bodyOfResponse);
	}

}
