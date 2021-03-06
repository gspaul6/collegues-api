/**
 * 
 */
package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entite.Collegue;

/**
 * @author gurpr
 *
 */
public interface CollegueRepository extends JpaRepository<Collegue, String> {

	List<Collegue> findDistinctCollegueByNom(String lastname);

	@Query("Select c.email from Collegue c where c.email=:email")
	String findDistinctCollegueByEmail(@Param("email") String email);

	@Query("Select c from Collegue c where c.email=:email")
	Optional<Collegue> findCollegueByTheirEmail(@Param("email") String email);

	// @Modifying
	// @Query("update Collegue c set c.email = ?1, where c.id = ?2")
	// void setCollegueInfoByEmail(String email, String matricule);
	//
	// @Modifying
	// @Query("update Collegue c set c.photoUrl = ?1, where c.id = ?2")
	// void setCollegueInfoByPhotoUrl(String photoUrl, String matricule);
}
