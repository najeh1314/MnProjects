package Exam;

public class Disque extends Ouvrage {

	private String editeur;
	
	public String getEditeur() {
		return editeur;
	}
	public void setEditeur(String editeur) {
		this.editeur = editeur;
	}
	@Override
	public String toString() {
		return super.toString()+" Disque [editeur=" + editeur + "]";
	}
	public Disque(String auteur, String titre, int nbExemplaire, String editeur) {
		super(auteur, titre, nbExemplaire);
		this.editeur = editeur;
	}
	
}