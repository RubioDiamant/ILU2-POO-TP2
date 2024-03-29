package controleur;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlVerifierIdentiteTest {
	
	private Village village;
	private Chef chef;
	private ControlEmmenager controlEmmenager;
	
	@BeforeEach
	public void initiaiserSituation(){
		System.out.println("Start test...");
		village = new Village("VillageTest", 10, 5);
		chef = new Chef("ChefVillage", 10, village);
		village.setChef(chef);
		controlEmmenager = new ControlEmmenager(village);
		
	}

	@Test
	void testControlVerifierIdentite() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		assertNotNull(controlVerifierIdentite, "Verifier identite ne renvoie pas null");
	}

	@Test
	void testVerifierIdentite() {
		ControlVerifierIdentite controlVerifierIdentite = new ControlVerifierIdentite(village);
		controlEmmenager.ajouterGaulois("Gaulois1", 10);
		assertTrue(controlVerifierIdentite.verifierIdentite("Gaulois1"), "Habitant non reconnu");
		assertFalse(controlVerifierIdentite.verifierIdentite("Inconnu"), "Inconnu reconnu");
	}

}
