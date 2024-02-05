package Exam;

public class Emprunt {
	private Livre livre;
	private Adherent emprunteur;
	
	public Emprunt(Livre unLivre, Adherent unemprunteur) {
		if(unLivre != null && unLivre.estEmpruntable()&&unemprunteur != null&&unemprunteur.peutEmprunter()){
			livre = unLivre;
			unLivre.emprunter();
			emprunteur = unemprunteur;
			emprunteur.emprunter();
		}
	
	}
	
	public Livre getLivre() {
		return livre;
	}

	public Adherent getEmprunteur() {
		return emprunteur;
	}

	public void terminer() {
		livre.rendre();
		emprunteur.rendre();
	}
	
}

