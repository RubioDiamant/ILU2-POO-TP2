package controleur;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Etal;
import villagegaulois.Village;

class ControlTrouverEtalVendeurTest {
	
	private Village village;
	private Chef chef;
	
	@BeforeEach
	public void initialiseSituation() {
		System.out.println("Start test...");
		village = new Village("VillageTest", 10, 5);
		chef = new Chef("ChefVillage", 10, village);
		village.setChef(chef);
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		controlEmmenager.ajouterGaulois("Asterix", 10);
		controlEmmenager.ajouterGaulois("Obelix", 15);
		controlEmmenager.ajouterGaulois("Bonemine", 5);
		controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 20);
		controlPrendreEtal.prendreEtal("Obelix", "menhirs", 5);
		controlPrendreEtal.prendreEtal("Asterix", "pommes", 10);
	}

	@Test
	void testControlTrouverEtalVendeur() {
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		assertNotNull(controlTrouverEtalVendeur, "Le constructeur ne renvoie pas null");
	}

	@Test
	void testTrouverEtalVendeur() {
		
		ControlTrouverEtalVendeur controlTrouverEtalVendeur = new ControlTrouverEtalVendeur(village);
		assertEquals("Bonemine", controlTrouverEtalVendeur.trouverEtalVendeur("Bonemine").getVendeur().getNom());
		assertEquals("Obelix", controlTrouverEtalVendeur.trouverEtalVendeur("Obelix").getVendeur().getNom());
		Etal etalAsterix = controlTrouverEtalVendeur.trouverEtalVendeur("Asterix");
		assertEquals("Asterix", etalAsterix.getVendeur().getNom());
		assertEquals("pommes", etalAsterix.getProduit());
		assertEquals(10, etalAsterix.getQuantite());
		assertEquals(null, controlTrouverEtalVendeur.trouverEtalVendeur("Panoramix"));
		assertNotNull(etalAsterix);
		
	}

}
