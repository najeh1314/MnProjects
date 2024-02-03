package Pharmacie;

public class StratAppro implements StrategieMvm {

	@Override
	public void changeQtite(Medicament medic, int q) {
		medic.qtiteStock +=q;
		
	}

	@Override
	public String nomStrategieMvm() {
		// TODO Auto-generated method stub
		return "Approvisionnement";
	}

}
