package Exam;

public class Livre extends Ouvrage implements Empruntable {
	private int nbPage;
	private int nbEmpruntes;

	public int getNbPage() {
		return nbPage;
	}
	public void setNbPage(int nbPage) {
		this.nbPage = nbPage;
	}
	public int getNbEmpruntes() {
		return nbEmpruntes;
	}
	public void setNbEmpruntes(int nbEmpruntes) {
		this.nbEmpruntes = nbEmpruntes;
	}
	@Override
	public String toString() {
		return super.toString()+ " Livre [nbPage=" + nbPage + ", nbEmpruntes=" + nbEmpruntes + "]";
	}
	public Livre(String auteur, String titre, int nbExemplaire, int nbPage) {
		super(auteur, titre, nbExemplaire);
		this.nbPage = nbPage;
		this.nbEmpruntes = 0;
	}
	public boolean estEmpruntable() {
		return (super.getNbExemplaire()>nbEmpruntes);
	}
	public int nbDisponible() {
		return super.getNbExemplaire()-nbEmpruntes;
	}
	@Override
	public void emprunter() {
		nbEmpruntes++;		
	}
	@Override
	public void rendre() {
		nbEmpruntes++;		
	}
	
	
	
}
