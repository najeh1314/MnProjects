package Pharmacie;

public abstract class Medicament implements Article{
	double prixMedic;
	String lot;
	String nom;
	int qtiteStock;

	public Medicament(String nom, double prixArticle, String code, int qtiteStock) {
		this.nom = nom;
		this.prixMedic = prixArticle;
		this.lot = code;
		this.qtiteStock = qtiteStock;
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
	
	
	
	@Override
	public String toString() {
		return("Nom: "+ nom+ "  /prix: "+prixMedic+ "  /En stock: "+qtiteStock);
	}
}
