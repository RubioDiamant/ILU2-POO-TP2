package controleur;

import villagegaulois.Village;

public class ControlVerifierIdentite {
	private Village village;

	public ControlVerifierIdentite(Village village) {
		this.village = village;
	}

	public boolean verifierIdentite(String nomVendeur) {
		String[] villageois = village.donnerVillageois();
		for(int i = 0; i < villageois.length; i++) {
			if(villageois[i].equals(nomVendeur)){
				return true;
			}
		}
		return false;
	}
}
