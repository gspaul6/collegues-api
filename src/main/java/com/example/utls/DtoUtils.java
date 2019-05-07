package com.example.utls;

import com.example.entite.Collegue;
import com.example.entite.CollegueDTO;

public interface DtoUtils {
	public static CollegueDTO ToCollegueDTO(Collegue col) {

		return new CollegueDTO(col.getMatricule(), col.getNom(), col.getPrenoms(), col.getDateDeNaissance(),
				col.getEmail(), col.getPhotoUrl());
	}

	public static Collegue ToCollegue(CollegueDTO col) {
		return new Collegue(col.getNom(), col.getPrenoms(), col.getDateDeNaissance(), col.getEmail(),
				col.getPhotoUrl());
	}
}
