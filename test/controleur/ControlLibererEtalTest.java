package controleur;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlLibererEtalTest {
	
	private Chef chef;
	private Village village;
	private ControlPrendreEtal controlPrendreEtal;
	private ControlVerifierIdentite controlVerifierIdentite;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	
	@BeforeEach
	public void initialiserSituation() {
		village = new Village("Village gaulois", 10, 2);
		chef = new Chef("Abrara", 10, village);
		village.setChef(chef);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		chef = new Chef("Abrara", 18, village);;
		controlEmmenager.ajouterGaulois("Asterix", 10);
		controlEmmenager.ajouterGaulois("Obelix", 15);
		controlEmmenager.ajouterGaulois("Bonemine", 5);
	}

	@Test
	void testControlLibererEtal() {
		ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		assertNotNull(controlLibererEtal, "Le constructeur ne renvoie pas null");
	}

	@Test
	void testIsVendeur() {
		ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		controlPrendreEtal.prendreEtal("Obelix", "menhirs", 5);
		assertTrue(controlLibererEtal.isVendeur("Obelix"));
		assertFalse(controlLibererEtal.isVendeur("Asterix"));
		controlPrendreEtal.prendreEtal("Asterix", "menhirs", 5);
		assertTrue(controlLibererEtal.isVendeur("Asterix"));
	}

	@Test
	void testLibererEtal() {
		ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		controlPrendreEtal.prendreEtal("Obelix", "menhirs", 5);
		assertNull(controlLibererEtal.libererEtal("Asterix"));
		String[] etalObelix = {"true","Obelix","menhirs","5","0"};
		String[] secondEtalObelix = controlLibererEtal.libererEtal("Obelix");
		assertEquals(etalObelix.length, secondEtalObelix.length);
		for(int i =0; i< etalObelix.length; i++) {
			assertEquals(etalObelix[i], secondEtalObelix[i]);
		}
	}

}
