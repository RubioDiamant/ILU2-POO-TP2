package controleur;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Village;

class ControlAcheterProduitTest {
	
	private Chef chef;
	private Village village;
	private ControlPrendreEtal controlPrendreEtal;
	ControlEmmenager controlEmmenager;
	private ControlVerifierIdentite controlVerifierIdentite;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;


	@BeforeEach
	public void initialiserSituation() {
		village = new Village("Village gaulois", 10, 3);
		chef = new Chef("Abrara", 10, village);
		village.setChef(chef);
		controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		controlEmmenager = new ControlEmmenager(village);
		chef = new Chef("Abrara", 18, village);;
		controlEmmenager.ajouterGaulois("Asterix", 10);
		controlEmmenager.ajouterGaulois("Obelix", 15);
		controlEmmenager.ajouterGaulois("Bonemine", 5);
	}

	@Test
	void testControlAcheterProduit() {
		ControlAcheterProduit controlAcheterProduit= new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		assertNotNull(controlAcheterProduit, "Le constructeur ne renvoie pas null");
	}

	@Test
	void testVerifierIdentiteClient() {
		ControlAcheterProduit controlAcheterProduit= new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		assertTrue(controlAcheterProduit.verifierIdentiteClient("Obelix"));
		assertTrue(controlAcheterProduit.verifierIdentiteClient("Asterix"));
		assertFalse(controlAcheterProduit.verifierIdentiteClient("Inconnu"));
		controlEmmenager.ajouterGaulois("Inconnu", 5);
		assertTrue(controlAcheterProduit.verifierIdentiteClient("Inconnu"));
	}

	@Test
	void testTrouverVendeurProduit() {
		ControlAcheterProduit controlAcheterProduit= new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		controlPrendreEtal.prendreEtal("Obelix", "menhirs", 5);
		controlPrendreEtal.prendreEtal("Asterix", "menhirs", 3);
		controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 10);
		assertEquals(null, controlAcheterProduit.trouverVendeurProduit("pommes"));
		Gaulois[] vendeursMenhirs = {village.trouverHabitant("Obelix"), village.trouverHabitant("Asterix")};
		Gaulois[] vendeursMenhirsRetourne = controlAcheterProduit.trouverVendeurProduit("menhirs");
		assertEquals(vendeursMenhirs.length, vendeursMenhirsRetourne.length);
		for (int i = 0; i < vendeursMenhirsRetourne.length; i++) {
			assertEquals(vendeursMenhirs[i], vendeursMenhirsRetourne[i]);
		}
		Gaulois bonemine = village.trouverHabitant("Bonemine");
		Gaulois[] vendeurFleur = controlAcheterProduit.trouverVendeurProduit("fleurs");
		assertEquals(1, vendeurFleur.length);
		assertEquals(bonemine, vendeurFleur[0]);
	}

	@Test
	void testAcheterProduit() {
		ControlAcheterProduit controlAcheterProduit= new ControlAcheterProduit(controlVerifierIdentite, controlTrouverEtalVendeur, village);
		controlPrendreEtal.prendreEtal("Obelix", "menhirs", 5);
		controlPrendreEtal.prendreEtal("Asterix", "menhirs", 3);
		controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 10);
		assertEquals(3,controlAcheterProduit.acheterProduit("Obelix", 3));
		assertEquals(2,controlAcheterProduit.acheterProduit("Obelix", 4));
		assertEquals(0,controlAcheterProduit.acheterProduit("Obelix", 1));
		assertEquals(3,controlAcheterProduit.acheterProduit("Asterix", 5));
		assertEquals(0,controlAcheterProduit.acheterProduit("Asterix", 1));
		assertEquals(5,controlAcheterProduit.acheterProduit("Bonemine", 5));
		assertEquals(5,controlAcheterProduit.acheterProduit("Bonemine", 5));
		assertEquals(0,controlAcheterProduit.acheterProduit("Bonemine", 5));
	}

}
