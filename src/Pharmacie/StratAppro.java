package Pharmacie;

public class StratAppro implements StrategieMvm {

	@Override
	public void changeQtite(Medicament medic, int q) {
		medic.qtiteStock +=q;
		
	}

}
