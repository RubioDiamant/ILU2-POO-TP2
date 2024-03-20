package frontiere;

import controleur.ControlLibererEtal;

public class BoundaryLibererEtal {
	private ControlLibererEtal controlLibererEtal;

	public BoundaryLibererEtal(ControlLibererEtal controlLibererEtal) {
		this.controlLibererEtal = controlLibererEtal;
	}

	public void libererEtal(String nomVendeur) {
		if(!controlLibererEtal.isVendeur(nomVendeur)) {
			System.out.println("Mais vous n'etes pas inscrit sur notre marche aujourd'hui !\n");
			String[] donnees = controlLibererEtal.libererEtal(nomVendeur);
			System.out.println("Vous avez vendu " + donnees[4] + " sur " + donnees[3] + " " + donnees[2] + ".\n");
		}
		
	}

}
