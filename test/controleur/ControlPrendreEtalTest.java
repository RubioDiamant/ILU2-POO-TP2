package controleur;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import personnages.Chef;
import villagegaulois.Village;

//Probleme ici !!!

class ControlPrendreEtalTest {

	private Chef chef;
	private Village village;
	private ControlPrendreEtal controlPrendreEtal;
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
		ControlEmmenager controlEmmenager = new ControlEmmenager(village);
		chef = new Chef("Abrara", 18, village);;
		controlEmmenager.ajouterGaulois("Asterix", 10);
		controlEmmenager.ajouterGaulois("Obelix", 15);
		controlEmmenager.ajouterGaulois("Bonemine", 5);
		

	}
	
	@Test
	void testControlPrendreEtal() {
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertNotNull(controlPrendreEtal, "Le constructeur ne renvoie pas null");
	}

	
	@Test
	void testPrendreEtal() {
		ControlLibererEtal controlLibererEtal = new ControlLibererEtal(controlTrouverEtalVendeur);
		ControlAfficherMarche controlAfficherMarche = new ControlAfficherMarche(village);
		int etalBonemine = controlPrendreEtal.prendreEtal("Bonemine", "fleurs", 20);
		assertNotEquals(-1, etalBonemine);
		int etalObelix = controlPrendreEtal.prendreEtal("Obelix", "menhirs", 5);
		assertNotEquals(-1, etalObelix);
		int etalAsterix = controlPrendreEtal.prendreEtal("Asterix", "menhirs", 5);
		assertEquals(-1, etalAsterix);
		controlLibererEtal.libererEtal("Asterix");
		
	}
	
	@Test
	void testResteEtals() {
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertTrue(controlPrendreEtal.resteEtals());
		controlPrendreEtal.prendreEtal("Asterix", "pommes", 15);
		assertFalse(controlPrendreEtal.resteEtals());
	}


	@Test
	void testVerifierIdentite() {
		ControlPrendreEtal controlPrendreEtal = new ControlPrendreEtal(controlVerifierIdentite, village);
		assertTrue(controlPrendreEtal.verifierIdentite("Bonemine"));
		assertTrue(controlPrendreEtal.verifierIdentite("Obelix"));
		assertFalse(controlPrendreEtal.verifierIdentite("Panoramix"));
		
	}

}
