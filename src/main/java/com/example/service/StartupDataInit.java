package com.example.service;

import java.time.LocalDate;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.entite.Collegue;

@Component
public class StartupDataInit {

	private static final Logger LOG = LoggerFactory.getLogger(StartupDataInit.class);

	@Autowired
	CollegueService service;

	// La méthode init va être invoquée au démarrage de l'application.
	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		LOG.error("hello startiiiiiinnnnnng");

		service.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Marty", "Nicolas",
				"MartyNicolas@gmail.com", LocalDate.of(1997, 01, 01), "http://www.yahoo.com"));
		service.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Boss", "Hugo", "BossHugo@gmail.com",
				LocalDate.of(1997, 12, 12), "http://www.hotmail.com"));
		service.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Tikal", "Tikka",
				"TikalTikka@gmail.com", LocalDate.of(1994, 03, 04), "http://www.mappy.com"));
		service.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Polinski", "Nicolas",
				"PolinskiNicolas@gmail.com", LocalDate.of(1996, 12, 12), "http://www.trello.com"));
		service.savingColleguesMethod(new Collegue(UUID.randomUUID().toString(), "Russe", "Ygurpratap",
				"RusseYgurpratap@gmail.com", LocalDate.of(1988, 05, 06), "http://www.picachu.com"));

	}
}
