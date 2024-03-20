package frontiere;

import java.util.Scanner;

import controleur.ControlPrendreEtal;

public class BoundaryPrendreEtal {
	private ControlPrendreEtal controlPrendreEtal;
	private Scanner scan = new Scanner(System.in);

	public BoundaryPrendreEtal(ControlPrendreEtal controlChercherEtal) {
		this.controlPrendreEtal = controlChercherEtal;
	}

	public void prendreEtal(String nomVendeur) {
		if(controlPrendreEtal.verifierIdentite(nomVendeur)) {
			System.out.println("Bonjour " + nomVendeur + ", je vais regarder si je peux vous trouver un "
					+ "etal.\n");
			if(controlPrendreEtal.resteEtals()) {
				installerVendeur(nomVendeur);
			}
			else {
				System.out.println("Désolé " + nomVendeur + " mais je n'ai plus d'étal d'etal qui ne soit pas occupé.\n");
			}
		}
		else {
			System.out.println("Je suis désolé " + nomVendeur + " mais il faut etre un habitant du village pour commercer ici !\n");
		}
	}

	private void installerVendeur(String nomVendeur) {
		String produit;
		System.out.println("C'est parfait, il me reste un étal pour vous !\n"
				+ "Il me faudrait quelques renseingnements :\n"
				+ "Quel produit souhaitez-vous vendre ?\n");
		produit = scan.next();
		System.out.println("Combien souhaitez-vous en vendre ?\n");
		int nombre = scan.nextInt();
		while(nombre <= 0){
			System.out.println("Veuillez entrer un nombre correct\n");
			nombre = scan.nextInt();
			
		}
		int numeroEtal = controlPrendreEtal.prendreEtal(nomVendeur, produit, nombre) + 1;
		System.out.println("Le vendeur " + nomVendeur + " s'est installé à l'etal n " + numeroEtal + "\n");
		
	}
}
