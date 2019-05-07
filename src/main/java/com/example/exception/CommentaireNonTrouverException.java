package com.example.exception;

public class CommentaireNonTrouverException extends RuntimeException {

	public CommentaireNonTrouverException() {

		super();
	}

	public CommentaireNonTrouverException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommentaireNonTrouverException(String message) {
		super(message);
	}

	public CommentaireNonTrouverException(Throwable cause) {
		super(cause);
	}

}