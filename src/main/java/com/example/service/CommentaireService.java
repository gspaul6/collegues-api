package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entite.CommentaireCollegue;
import com.example.exception.CommentaireNonTrouverException;
import com.example.repository.CommentaireRepository;

@Service
public class CommentaireService {

	@Autowired
	private CommentaireRepository commentaireRepository;

	public void setCommentaireRepository(CommentaireRepository commentaireRepository) {
		this.commentaireRepository = commentaireRepository;
	}

	public void savingCommentaire(CommentaireCollegue commentaire) {
		this.commentaireRepository.save(commentaire);

	}

	public List<CommentaireCollegue> researchCommentaireParMatricule(String matricule)
			throws CommentaireNonTrouverException {
		List<CommentaireCollegue> listCommentaire = new ArrayList<>();
		if (this.commentaireRepository.findDistinctCommentaireByCollegueMatricule(matricule) != null) {
			listCommentaire = this.commentaireRepository.findDistinctCommentaireByCollegueMatricule(matricule);
		} else {
			listCommentaire.clear();
			throw new CommentaireNonTrouverException("commentaire does not exist");

		}
		return listCommentaire;

	}

	public void supremeCommentaireParmatricule(Integer id) {

		this.commentaireRepository.deleteById(id);
	}

}
