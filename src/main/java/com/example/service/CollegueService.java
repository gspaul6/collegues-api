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
	public Collegue collegue = new Collegue();

	public CollegueService() {
		// TODO alimenter data avec des données fictives
		// Pour générer un matricule : `UUID.randomUUID().toString()`

		collegue.setMatricule(UUID.randomUUID().toString());
		this.data.put(collegue.getMatricule(), collegue);
	}

	public Collegue savingColleguesMethod() {
		return this.data.put(UUID.randomUUID().toString(), collegue);
	}

	public Map<String, Collegue> getData() {
		return data;
	}

	public void setData(Map<String, Collegue> data) {
		this.data = data;
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
 */