package Exam;

public class Ouvrage {
	private String auteur;
	private String titre;
	private int nbExemplaire;
	
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getNbExemplaire() {
		return nbExemplaire;
	}
	public void setNbExemplaire(int nbExemplaire) {
		this.nbExemplaire = nbExemplaire;
	}
	@Override
	public String toString() {
		return "Ouvrage [titre=" + titre + ", auteur= " + auteur + ", nbExemplaire=" + nbExemplaire + "]";
	}
	public Ouvrage(String auteur, String titre, int nbExemplaire) {
		super();
		this.auteur = auteur;
		this.titre = titre;
		this.nbExemplaire = nbExemplaire;
	}
}
