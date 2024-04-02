package controleur;

import personnages.Gaulois;
import villagegaulois.Etal;
import villagegaulois.Village;

public class ControlAcheterProduit {
	private Village village;
	private ControlTrouverEtalVendeur controlTrouverEtalVendeur;
	private ControlVerifierIdentite controlVerifierIdentite;

	public ControlAcheterProduit(ControlVerifierIdentite controlVerifierIdentite,
			ControlTrouverEtalVendeur controlTrouverEtalVendeur,
			Village village) {
		this.village = village;
		this.controlVerifierIdentite = controlVerifierIdentite;
		this.controlTrouverEtalVendeur = controlTrouverEtalVendeur;
	}

	public boolean verifierIdentiteClient(String nomAcheteur) {
		return controlVerifierIdentite.verifierIdentite(nomAcheteur);
	}
	
	public Gaulois[] trouverVendeurProduit(String produit) {
		Gaulois[] vendeurTrouve = village.rechercherVendeursProduit(produit);
		return vendeurTrouve;
		
	}
	
	public int acheterProduit(String vendeur, int nbProduit) {
		Etal etal = controlTrouverEtalVendeur.trouverEtalVendeur(vendeur);
		return etal.acheterProduit(nbProduit);	
	}
}
