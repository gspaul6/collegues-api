package com.example.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.entite.Collegue;
import com.example.exception.CollegueInvalidException;
import com.example.exception.CollegueNonTrouveException;
import com.example.repository.CollegueRepository;

@Service
public class CollegueService {
	// private Map<String, Collegue> data = new HashMap<>();

	@Autowired
	private CollegueRepository collegueRepository;

	/**
	 * @param collegueRepository
	 *            the collegueRepository to set
	 */
	public void setCollegueRepository(CollegueRepository collegueRepository) {
		this.collegueRepository = collegueRepository;
	}

	@PostConstruct
	public void init() {
		// alimenter data avec des données fictives
		// Pour générer un matricule : `UUID.randomUUID().toString()`
		this.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Marty", "Nicolas",
				"MartyNicolas@gmail.com", LocalDate.of(1997, 01, 01), "http://www.yahoo.com"));
		this.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Boss", "Hugo", "BossHugo@gmail.com",
				LocalDate.of(1997, 12, 12), "http://www.hotmail.com"));
		this.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Tikal", "Tikka", "TikalTikka@gmail.com",
				LocalDate.of(1994, 03, 04), "http://www.mappy.com"));
		this.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Polinski", "Nicolas",
				"PolinskiNicolas@gmail.com", LocalDate.of(1996, 12, 12), "http://www.trello.com"));
		this.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Russe", "Ygurpratap",
				"RusseYgurpratap@gmail.com", LocalDate.of(1988, 05, 06), "http://www.picachu.com"));

	}

	public Collegue savingColleguesMethod(Collegue collegueAjouter) {
		// Vérifier que le nom et les prenoms ont chacun au moins 2
		// caractères
		if (collegueAjouter.getNom().length() < 2)
			throw new CollegueInvalidException("Illegal argument nom:Your nom is contains less then 3 characters ");

		if (collegueAjouter.getPrenoms().length() < 2)
			throw new CollegueInvalidException("Illegal argument Prenom:Your prenom contains less then 3 characters ");

		// Vérifier que l'email a au moins 3 caractères et contient `@`
		if (!collegueAjouter.getEmail().contains("@"))
			throw new CollegueInvalidException("Illegal argument:Your email does not contain an @ ");

		if (collegueAjouter.getEmail().length() < 3)
			throw new CollegueInvalidException("Illegal argument:Your email contains less then 3 characters ");

		// Vérifier que la photoUrl commence bien par `http`
		if (!collegueAjouter.getPhotoUrl().startsWith("http"))
			throw new CollegueInvalidException("Illegal argument:Your image does not starts with http ");

		// Vérifier que la date de naissance correspond à un age >= 18
		Period todayDate = Period.between(collegueAjouter.getDateDeNaissance(), LocalDate.now());
		if (todayDate.getYears() < 18)
			throw new CollegueInvalidException("Illegal argument:it  is need to for one to be of an legal age ");

		// générer un matricule pour ce collègue
		// (`UUID.randomUUID().toString()`)
		collegueAjouter.setMatricule(UUID.randomUUID().toString());

		collegueRepository.save(collegueAjouter);
		return collegueAjouter;

	}

	public List<Collegue> rechercherParNom(String nomRecherche) {

		return collegueRepository.findDistinctCollegueByNom(nomRecherche);
	}

	public Collegue rechercherParMatricule(String matriculeRecherche) throws CollegueNonTrouveException {
		// retourner le collègue dont le matricule est fourni

		return collegueRepository.findById(matriculeRecherche).orElseThrow(CollegueNonTrouveException::new);

	}

	@Transactional
	public Collegue modifierEmail(String matricule, String email) throws CollegueNonTrouveException {

		// retourner une exception `CollegueNonTrouveException`
		// si le matricule ne correspond à aucun collègue
		Collegue collegueModifieEmail = collegueRepository.findById(matricule)
				.orElseThrow(CollegueNonTrouveException::new);
		// Vérifier que l'email a au moins 3 caractères et contient `@`
		if (!email.contains("@"))
			throw new CollegueInvalidException("Illegal argument:Your email does not contain an @ ");

		if (email.length() < 3)
			throw new CollegueInvalidException("Illegal argument:Your email contains less then 3 characters ");

		collegueModifieEmail.setEmail(email);

		// collegueRepository.setCollegueInfoByEmail(email, matricule);
		return collegueModifieEmail;

	}

	@Transactional
	public Collegue modifierPhotoUrl(String matricule, String photoUrl) {

		Collegue collegueModifiePhoto = collegueRepository.findById(matricule)
				.orElseThrow(CollegueNonTrouveException::new);

		if (!photoUrl.startsWith("http"))
			throw new CollegueInvalidException("Illegal argument:Your image does not starts with http ");

		collegueModifiePhoto.setPhotoUrl(photoUrl);

		// collegueRepository.setCollegueInfoByPhotoUrl(photoUrl, matricule);

		return collegueModifiePhoto;
	}

}

// public List<Collegue> rechercherParNom(String nomRecherche) {
// List<Collegue> listCollegue = new ArrayList<>(this.data.values());
// Iterator<Collegue> itrListCollegue = listCollegue.iterator();
// while (itrListCollegue.hasNext()) {
// Collegue nomCollegue = itrListCollegue.next();
// if (nomCollegue.getNom().equals(nomRecherche)) {
// listCollegue.clear();
// listCollegue.add(nomCollegue);
//
// }
// }
//
//
// return listCollegue;
// }

/*
 * another way for iterating over maps // using keySet() for iteration over keys
 * for (String keys : data.keySet()) System.out.println("key: " + keys);
 * 
 * // using values() for iteration over keys for (String name : data.values())
 * System.out.println("value: " + name);
 * 
 * another methos this.data.values().stream(){coleg->});
 * 
 */