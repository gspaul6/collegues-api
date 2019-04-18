package com.example.exception;

import org.springframework.http.HttpStatus;
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
		return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyOfResponse);
	}

	@ExceptionHandler(value = { IllegalStateException.class })
	protected ResponseEntity<Object> handleConflict(WebRequest request) {
		String bodyOfResponse = "This should be application specific, which deals with Web request ";
		return ResponseEntity.status(HttpStatus.CONFLICT).body(bodyOfResponse);
	}

}
