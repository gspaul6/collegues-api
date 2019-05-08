package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entite.Collegue;
import com.example.entite.CommentaireCollegue;
import com.example.entite.CommentaireCollegueDTO;
import com.example.exception.CollegueNonTrouveException;
import com.example.exception.CommentaireNonTrouverException;
import com.example.repository.CollegueRepository;
import com.example.repository.CommentaireRepository;
import com.example.utls.DtoUtils;

@Service
public class CommentaireService {

	@Autowired
	private CommentaireRepository commentaireRepository;
	
	@Autowired
	private  CollegueRepository collegueRepository;

	public void setCommentaireRepository(CommentaireRepository commentaireRepository) {
		this.commentaireRepository = commentaireRepository;
	}

	public CommentaireCollegueDTO savingCommentaire(String matricule,CommentaireCollegueDTO commentaire) {
        CommentaireCollegue comment = DtoUtils.toCommentaireCollegue(commentaire);
		
		Collegue collegue = this.collegueRepository.findById(matricule).orElseThrow(CollegueNonTrouveException::new);
		comment.setCollegue(collegue);
		
		this.commentaireRepository.save(comment);
		
		return DtoUtils.toCommentaireCollegueDTO(comment);

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
