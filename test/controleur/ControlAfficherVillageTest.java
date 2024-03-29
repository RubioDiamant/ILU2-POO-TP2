package controleur;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

class ControlAfficherVillageTest {
	
	private Village village;
	private Chef abraracourcix;
	private ControlEmmenager controlEmmenagerVillage;
	
	@BeforeEach
	public void initialiserSituation() {
		System.out.println("Initialisation...");
		village = new Village("le village des irréductibles", 10, 5);
		abraracourcix = new Chef("Abraracourcix", 10, village);
		controlEmmenagerVillage = new ControlEmmenager(village);
		village.setChef(abraracourcix);
	}
	
	@Test
	void testControlAfficherVillage() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		assertNotNull(controlAfficherVillage, "ContolAfficherVillage ne renvoiie pas null");
	}

	@Test
	void testDonnerNomsVillageois() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		controlEmmenagerVillage.ajouterGaulois("Bonemine", 7);
		controlEmmenagerVillage.ajouterDruide("Panopano", 3,3,5);
		String[] tab = {"Bonemine","Panopano"};
		assertEquals(tab, )
	}

	@Test
	void testDonnerNomVillage() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		assertEquals("le village des irréductibles", village.getNom());
	}

	@Test
	void testDonnerNbEtals() {
		ControlAfficherVillage controlAfficherVillage = new ControlAfficherVillage(village);
		assertEquals(5, village.donnerNbEtal());
	}

}
