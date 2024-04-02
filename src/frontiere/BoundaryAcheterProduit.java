package frontiere;

import java.util.Scanner;

import controleur.ControlAcheterProduit;
import personnages.Gaulois;

public class BoundaryAcheterProduit {
	private Scanner scan = new Scanner(System.in);
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		String produitCherche;
		if(controlAcheterProduit.verifierIdentiteClient(nomAcheteur)) {
			System.out.println("Quel produit voulez vous acheter ?");
			produitCherche = scan.next();
			
			Gaulois[] vendeur = controlAcheterProduit.trouverVendeurProduit(produitCherche);
			
			if(vendeur == null) {
				System.out.println("Desolé, personne ne vend ce produit au marche !");
			}
			else {
				System.out.println("Chez quel commercant voulez-vous acheter des fleurs ?");
				for(int i = 1; i<= vendeur.length; i++) {
					System.out.println(i + " - " + vendeur[i - 1].getNom());
				}
				
				int choixVendeur = -1;
				choixVendeur = scan.nextInt() - 1;
				while(choixVendeur < 0 || choixVendeur >= vendeur.length) {
					System.out.println("Veuillez entrer un nombre correct");
					choixVendeur = scan.nextInt() - 1; 
				}
				String vendeurChoisi = vendeur[choixVendeur].getNom();
				int nbProduit = -1;
				System.out.println(nomAcheteur + " se deplace vers l'etal du vendeur " + vendeurChoisi +"\nBonjour " + nomAcheteur);
				System.out.println("Combien de " + produitCherche + " voulez-vous acheter ?");
				nbProduit = scan.nextInt();
				while(nbProduit < 1) {
					System.out.println("Veuillez entrer un nombre positif non nul");
					nbProduit = scan.nextInt(); 
				}
				int quantiteAchetee = controlAcheterProduit.acheterProduit(vendeurChoisi, nbProduit);
				if(quantiteAchetee == nbProduit) {
					System.out.println(nomAcheteur + " achete " + nbProduit + " " + produitCherche + " à " + vendeurChoisi);
				}else if(quantiteAchetee == 0) {
					System.out.println(nomAcheteur + " veut acheter " + nbProduit + " " + produitCherche + ", malheureusement, il n'y en a plus !");
				}
				else if(quantiteAchetee < nbProduit) {
					System.out.println(nomAcheteur + " veut acheter " + nbProduit + " " + produitCherche + ", malheureusement " + vendeurChoisi + " n'en a plus que " 
							+ quantiteAchetee + ". " + nomAcheteur + " achete tout le stock de " + vendeurChoisi + ".");
				}
				
			}
			
		}else {
			System.out.println("Je suis désolé " + nomAcheteur + " mais il faut etre un habitant de notre village pour commercer ici.");
		}
		
	}
}
