package Exam;

public class Adherent implements Empruntable{
	private String nom;
	private String prenom;
	private int nbEmpruntsEncours;
	private int nbEmpruntsAutorises;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getNbEmpruntsEncours() {
		return nbEmpruntsEncours;
	}
	public void setNbEmpruntsEncours(int nbEmpruntsEncours) {
		this.nbEmpruntsEncours = nbEmpruntsEncours;
	}
	public int getNbEmpruntsAutorises() {
		return nbEmpruntsAutorises;
	}
//	public void setNbEmpruntsAutorises(int nbEmpruntsAutorises) {
//		this.nbEmpruntsAutorises = nbEmpruntsAutorises;
//	}
	@Override
	public String toString() {
		return "Adherent [nom=" + nom + ", prenom=" + prenom /*+ ", nbEmpruntsEncours=" + nbEmpruntsEncours
				+ ", nbEmpruntsAutorises=" + nbEmpruntsAutorises */ +"]";
	}
	public Adherent(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.nbEmpruntsEncours = 0;
		this.nbEmpruntsAutorises = 3;
	}
	public boolean aUnLivreEmpunte(){
		//retourne false si nbEmpruntesEncours =0
		//retourne true si nbEmpruntesEncours >0
		return (nbEmpruntsEncours>0);
	}
	public boolean peutEmprunter() {
		return(nbEmpruntsAutorises>nbEmpruntsEncours);
	}
	@Override
	public void emprunter() {
		if(peutEmprunter())
			nbEmpruntsEncours++;
	}
	@Override
	public void rendre() {
		if(nbEmpruntsEncours>0)
			nbEmpruntsEncours--;
	}
	
}
