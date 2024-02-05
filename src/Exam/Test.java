package Exam;

public class Test {
	public static void main(String[] args) {
		Mediatheque m = new Mediatheque("les roses");
		
		Livre liv = new Livre("Jacques Durant", "Windows pour les dubutants", 10, 500);
		Disque d = new Disque("Fairouz", "kifak inta", 5, "Universal Music");
		Adherent a = new Adherent("Gates", "Bill");

		//emprunt du livre liv a l'adherent a
		Emprunt e = new Emprunt(liv, a);
		m.ajoutAdherent(a);
		m.ajoutOuvrage(d);
		m.ajoutEmprunt(e);
		
		//Affichage de la liste
		m.listeAdherentAyantEmprunt("Windows pour les dubutants");
		
		//rendre le livre liv emprunté par a
		e.terminer();
				//supprission de l'empreint e du tableau d'emprunte de ma mediatheque m
		for(int i=0; i<m.getNbEmprunt();i++) {
			if(m.getTabEmprunt()[i].equals(e))
				for(int j=i; j<m.getNbEmprunt();j++)
					m.getTabEmprunt()[j]=m.getTabEmprunt()[j+1];
			m.getTabEmprunt()[m.getNbEmprunt()]=null;
			m.setNbEmprunt(m.getNbEmprunt()-1);
			return;
		}	
	}
}
