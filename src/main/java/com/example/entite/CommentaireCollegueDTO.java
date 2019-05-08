package com.example.entite;

import java.time.LocalDateTime;

public class CommentaireCollegueDTO {

	
	
	public CommentaireCollegueDTO() {
		super();
	}

	public CommentaireCollegueDTO(Integer id, String commentaire, LocalDateTime dateCreated) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.commentaire=commentaire;
		this.dateCreated=dateCreated;
	}

	public String getCommentaire() {
		return commentaire;
	}

	/**
	 * @param commentaire
	 *            the commentaire to set
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	private String commentaire;
	private Integer id;
	private LocalDateTime dateCreated;
}
