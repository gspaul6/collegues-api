package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.example.entite.Collegue;

public class CollegueService {
	private Map<String, Collegue> data = new HashMap<>();

	public CollegueService() {
		// TODO alimenter data avec des données fictives
		// Pour générer un matricule : `UUID.randomUUID().toString()`
		this.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Marty", "Nicolas",
				"MartyNicolas@gmail.com", "18/12/1987", "img1"));
		this.savingColleguesMethod(
				new Collegue(UUID.randomUUID().toString(), "Boss", "Hugo", "BossHugo@gmail.com", "12/12/1997", "img2"));
		this.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Tikal", "Tikka", "TikalTikka@gmail.com",
				"03/12/1994", "img3"));
		this.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Polinski", "Nicolas",
				"PolinskiNicolas@gmail.com", "10/06/1996", "img4"));
		this.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Russe", "Ygurpratap",
				"RusseYgurpratap@gmail.com", "18/12/1987", "img5"));

	}

	public void savingColleguesMethod(Collegue collegue) {
		this.data.put(collegue.getMatricule(), collegue);
	}

	public List<Collegue> rechercherParNom(String nomRecherche) {
		List<Collegue> listCollegue = new ArrayList<>(this.data.values());
		Iterator<Collegue> itrListCollegue = listCollegue.iterator();
		while (itrListCollegue.hasNext()) {
			Collegue nomCollegue = itrListCollegue.next();
			if (nomCollegue.getNom().equals(nomRecherche)) {
				listCollegue.clear();
				listCollegue.add(nomCollegue);

			}
		}

		return listCollegue;
	}
}

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