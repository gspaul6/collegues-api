package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entite.CommentaireCollegue;

public interface CommentaireRepository extends JpaRepository<CommentaireCollegue, Integer> {

	@Query("Select ct from CommentaireCollegue ct where ct.collegue.matricule=:matricule")
	List<CommentaireCollegue> findDistinctCommentaireByCollegueMatricule(@Param("matricule") String matricule);
}
