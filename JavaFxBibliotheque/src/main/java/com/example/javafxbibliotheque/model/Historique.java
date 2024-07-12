package com.example.javafxbibliotheque.model;

public class Historique {
    private  Abonne abonne ;
    private Livre livre;
    private Emprunt emprunt;
    private LivreRetour livreRetour;

    private String nbJourRetard;


    public Historique(Abonne abonne, Livre livre, Emprunt emprunt, LivreRetour livreRetour) {
        this.abonne = abonne;
        this.livre = livre;
        this.emprunt = emprunt;
        this.livreRetour = livreRetour;

    }

    public Historique() {
    }

    public Abonne getAbonne() {
        return abonne;
    }

    public void setAbonne(Abonne abonne) {
        this.abonne = abonne;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Emprunt getEmprunt() {
        return emprunt;
    }

    public void setEmprunt(Emprunt emprunt) {
        this.emprunt = emprunt;
    }

    public LivreRetour getLivreRetour() {
        return livreRetour;
    }

    public void setLivreRetour(LivreRetour livreRetour) {
        this.livreRetour = livreRetour;
    }

    @Override
    public String toString() {
        return  this.nbJourRetard +" Jours";
    }

    public String getNbJourRetard() {
        if (livreRetour.getDateRetour().after(emprunt.getDateRetour())) {
            long l = ((livreRetour.getDateRetour().getTime()) - (emprunt.getDateRetour().getTime())) / (1000 * 60 * 60 * 24);
            this.nbJourRetard = String.valueOf(l) + " Jours";
        } else
            this.nbJourRetard = "0 Jours";

        return nbJourRetard;
    }

    public void setNbJourRetard(String nbJourRetard) {
        this.nbJourRetard = nbJourRetard;
    }
}
