package Pharmacie;

public class AntiBiotique extends Medicament {

	public AntiBiotique(String nom, double prixArticle, String code, int qtiteStock) {
		super(nom, prixArticle, code, qtiteStock);
	}

	@Override
	public double prix() {
		return this.prixMedic;
	}

	@Override
	public String toStrng() {
		return this.nom;
	}

	@Override
	public int qtite() {
		// TODO Auto-generated method stub
		return this.qtiteStock;
	}

	@Override
	public Medicament getMedic() {
		// TODO Auto-generated method stub
		return this;
	}
	
}
