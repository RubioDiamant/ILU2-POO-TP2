package controleur;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlAfficherMarcheTest {
	
	private Chef chef;
	private Village village;
	private ControlPrendreEtal controlPrendreEtal;
	private ControlVerifierIdentite controlVerifierIdentite;


	@BeforeEach
	public void initialiserSituation() {
		village = new Village("Village gaulois", 10, 5);
		chef = new Chef("Abrara", 10, village);
		village.setChef(chef);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		chef = new Chef("Abrara", 18, village);;
		controlEmmenager.ajouterGaulois("Asterix", 10);
		controlEmmenager.ajouterGaulois("Obelix", 15);
		controlEmmenager.ajouterGaulois("Bonemine", 5);
		controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 20);
		controlPrendreEtal.prendreEtal("Obelix", "menhirs", 5);
		controlPrendreEtal.prendreEtal("Asterix", "pommes", 10);

	}

	@Test
	void testControlAfficherMarche() {
		ControlAfficherMarche controlAfficherMarche = new ControlAfficherMarche(village);
		assertNotNull(controlAfficherMarche, "Constructeur ne renvoie pas null");
	}

	@Test
	void testDonnerInfosMarche() {
		ControlAfficherMarche controlAfficherMarche = new ControlAfficherMarche(village);
		String[] infoMarche = {"Bonemine","20","fleurs","Obelix", "5","menhirs","Asterix", "10","pommes"};
		String[] infoTest = controlAfficherMarche.donnerInfosMarche();
		assertEquals(infoMarche.length, infoTest.length);
		for(int i = 0; i<infoMarche.length; i++) {
			assertEquals(infoMarche[i], infoTest[i]);
		}
	}

}
