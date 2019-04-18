package dev.CollegueService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.entite.Collegue;
import com.example.exception.CollegueInvalidException;
import com.example.service.CollegueService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CollegueService.class)
public class CollegueServiceTest {
	private static final Logger LOG = LoggerFactory.getLogger(CollegueServiceTest.class);

	@Autowired
	private CollegueService serviceOfCollegue;

	@Test(expected = CollegueInvalidException.class)
	public void testThelengthForNom() {
		LOG.info("Etant donné, une instance de Collegue");
		Collegue newCollegue = new Collegue("P", "Jacky", "Pauljacky@gmail.com",
				LocalDate.parse("1996-12-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")), "http://www.google.com");
		this.serviceOfCollegue.savingColleguesMethod(newCollegue);
	}

	@Test(expected = CollegueInvalidException.class)
	public void testThelengthForPrenom() {
		LOG.info("Etant donné, une instance de Collegue");
		Collegue newCollegue = new Collegue("Paula", "J", "Pauljacky@gmail.com",
				LocalDate.parse("1996-12-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")), "http://www.google.com");
		this.serviceOfCollegue.savingColleguesMethod(newCollegue);
	}

	// email with no @
	@Test(expected = CollegueInvalidException.class)
	public void testForEmail() {
		LOG.info("Etant donné, une instance de Collegue");
		Collegue newCollegue = new Collegue("Paula", "Jacky", "Pauljackygmail.com",
				LocalDate.parse("1996-12-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")), "http://www.google.com");
		this.serviceOfCollegue.savingColleguesMethod(newCollegue);
	}

	// email with no length less then 3
	@Test(expected = CollegueInvalidException.class)
	public void testThelengthForEmailLength() {
		LOG.info("Etant donné, une instance de Collegue");
		Collegue newCollegue = new Collegue("Paula", "Jacky", "com",
				LocalDate.parse("1996-12-12", DateTimeFormatter.ofPattern("yyyy-MM-dd")), "http://www.google.com");
		this.serviceOfCollegue.savingColleguesMethod(newCollegue);
	}

	@Test(expected = CollegueInvalidException.class)
	public void testThelengthForyears() {
		LOG.info("Etant donné, une instance de Collegue");
		Collegue newCollegue = new Collegue("Paula", "Jacky", "Pauljacky@gmail.com", LocalDate.of(2001, 12, 12),
				"http://www.google.com");
		this.serviceOfCollegue.savingColleguesMethod(newCollegue);
	}

	@Test(expected = CollegueInvalidException.class)
	public void testModifierEmail() {
		LOG.info("Etant donné, une instance de Collegue");
		Collegue newCollegue = new Collegue("Paula", "Jacky", "Pauljacky@gmail.com", LocalDate.of(2000, 12, 12),
				"http://www.google.com");
		newCollegue.setMatricule(UUID.randomUUID().toString());
		this.serviceOfCollegue.savingColleguesMethod(newCollegue);
		this.serviceOfCollegue.modifierEmail(newCollegue.getMatricule(), "PAultimgmail.com");
	}

	@Test(expected = CollegueInvalidException.class)
	public void testModifierEmailLength() {
		LOG.info("Etant donné, une instance de Collegue");
		Collegue newCollegue = new Collegue("Paula", "Jacky", "Pauljack@gmail.com", LocalDate.of(2001, 12, 12),
				"http://www.google.com");

		newCollegue.setMatricule(UUID.randomUUID().toString());
		this.serviceOfCollegue.savingColleguesMethod(newCollegue);
		this.serviceOfCollegue.modifierEmail(newCollegue.getMatricule(), "om");
	}

	@Test(expected = CollegueInvalidException.class)
	public void testModifierPhotoUrl() {
		LOG.info("Etant donné, une instance de Collegue");
		Collegue newCollegue = new Collegue("Paula", "Jacky", "Pauljack@gmail.com", LocalDate.of(1996, 12, 12),
				"http://www.google.com");

		newCollegue.setMatricule(UUID.randomUUID().toString());
		this.serviceOfCollegue.savingColleguesMethod(newCollegue);
		this.serviceOfCollegue.modifierPhotoUrl(newCollegue.getMatricule(), "//google.com");
	}

}
