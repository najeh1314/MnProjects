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
}
