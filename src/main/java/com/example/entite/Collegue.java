package com.example.entite;

import java.time.LocalDate;

public class Collegue {

	private String matricule;
	private String nom;
	private String prenoms;
	private String email;
	private LocalDate dateDeNaissance;
	private String photoUrl;

	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}

	public Collegue() {
	}

	public Collegue(String matricule, String nom, String prenoms, String email, LocalDate dateDeNaissance,
			String photoUrl) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenoms = prenoms;
		this.email = email;
		this.dateDeNaissance = dateDeNaissance;
		this.photoUrl = photoUrl;
	}

	public Collegue(String nom, String prenoms, String email, LocalDate dateDeNaissance, String photoUrl) {
		super();

		this.nom = nom;
		this.prenoms = prenoms;
		this.email = email;
		this.dateDeNaissance = dateDeNaissance;
		this.photoUrl = photoUrl;
	}

	/**
	 * @param matricule
	 *            the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom
	 *            the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenoms
	 */
	public String getPrenoms() {
		return prenoms;
	}

	/**
	 * @param prenoms
	 *            the prenoms to set
	 */
	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the dateDeNaissance
	 */
	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}

	/**
	 * @param dateDeNaissance
	 *            the dateDeNaissance to set
	 */
	public void setDateDeNaissance(LocalDate dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	/**
	 * @return the photoUrl
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}

	/**
	 * @param photoUrl
	 *            the photoUrl to set
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

}
