/**
 * 
 */
package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entite.Collegue;

/**
 * @author gurpr
 *
 */
public interface CollegueRepository extends JpaRepository<Collegue, String> {

	List<Collegue> findDistinctCollegueByNom(String lastname);

	// Optional<Collegue> findDistinctCollegueByMatricule(String matricule);

	// @Modifying
	// @Query("update Collegue c set c.email = ?1, where c.id = ?2")
	// void setCollegueInfoByEmail(String email, String matricule);
	//
	// @Modifying
	// @Query("update Collegue c set c.photoUrl = ?1, where c.id = ?2")
	// void setCollegueInfoByPhotoUrl(String photoUrl, String matricule);
}
