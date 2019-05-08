package com.example.utls;

import com.example.entite.Collegue;
import com.example.entite.CollegueDTO;
import com.example.entite.CommentaireCollegue;
import com.example.entite.CommentaireCollegueDTO;

public interface DtoUtils {
	public static CollegueDTO ToCollegueDTO(Collegue col) {

		return new CollegueDTO(col.getMatricule(), col.getNom(), col.getPrenoms(), col.getDateDeNaissance(),
				col.getEmail(), col.getPhotoUrl());
	}

	public static Collegue ToCollegue(CollegueDTO col) {
		return new Collegue(col.getNom(), col.getPrenoms(), col.getDateDeNaissance(), col.getEmail(),
				col.getPhotoUrl());
	}
	public static CommentaireCollegueDTO toCommentaireCollegueDTO(CommentaireCollegue com) {

		return new CommentaireCollegueDTO(com.getId(), com.getCommentaire(), com.getDateCreated());

	}

	public static CommentaireCollegue toCommentaireCollegue(CommentaireCollegueDTO com) {

		return new CommentaireCollegue(com.getId(), com.getCommentaire(), com.getDateCreated());

	}

}
