package Pharmacie;

import java.util.ArrayList;

public class StratVente implements StrategieMvm {

	@Override
	public void changeQtite(Medicament medic, int q) {
		medic.qtiteStock -=q;
	}

	@Override
	public String nomStrategieMvm() {
		// TODO Auto-generated method stub
		return "Vente";
	}
	

}
