package Exam;

public class Mediatheque {
	private String nom;
	private Ouvrage[] tabOuvrage;
	private Adherent[] tabAdherent;
	private Emprunt[] tabEmprunt;
	private int nbOuvrage;
	private int nbAdherent; 
	private int nbEmprunt;
	
	public int getNbOuvrage() {
		return nbOuvrage;
	}
	public int getNbAdherent() {
		return nbAdherent;
	}
	public int getNbEmprunt() {
		return nbEmprunt;
	}	
	public void setNbOuvrage(int nbOuvrage) {
		this.nbOuvrage = nbOuvrage;
	}
	public void setNbAdherent(int nbAdherent) {
		this.nbAdherent = nbAdherent;
	}
	public void setNbEmprunt(int nbEmprunt) {
		this.nbEmprunt = nbEmprunt;
	}
	public Ouvrage[] getTabOuvrage() {
		return tabOuvrage;
	}
	public Adherent[] getTabAdherent() {
		return tabAdherent;
	}
	public Emprunt[] getTabEmprunt() {
		return tabEmprunt;
	}
	public Mediatheque(String nom) {
		super();
		this.nom = nom;
		this.nbAdherent=0;
		this.nbOuvrage =0;
		this.nbEmprunt =0;
		this.tabOuvrage = new Ouvrage[100];
		this.tabAdherent = new Adherent[100];
		this.tabEmprunt = new Emprunt[100];
	}
	
	public boolean ajoutOuvrage(Ouvrage o) {
		if(nbOuvrage<100) {
			tabOuvrage[nbOuvrage++]=o;
			return true;
		}
		return false;
	}
	public boolean ajoutAdherent(Adherent a) {
		if(nbAdherent<100) {
			tabAdherent[nbAdherent++]=a;
			return true;
		}
		return false;
	}
	
	public boolean ajoutEmprunt(Emprunt e) {
		if(nbEmprunt<100) {
			tabEmprunt[nbEmprunt++]=e;
			return true;
		}
		return false;
	}
	
	public void listeAdherentAyantEmprunt(String titre) {
		System.out.println("La liste des adherents ayant empreinter le livre sont:\n");
		for(int i=0;i<nbEmprunt; i++)
			if(tabEmprunt[i].getLivre().getTitre().equals(titre))
				System.out.println("\t" + tabEmprunt[i].getEmprunteur().toString() +"\n");
	}
	
}
