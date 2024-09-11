package com.example.javafxbibliotheque.model;

import java.util.Date;

public class LivreRetour {
    private  int id ;
    private int idAbonne;
    private int idLivre;
    private Date dateRetour;

    public LivreRetour(int id, int idAbonne, int idLivre, Date dateRetour) {
        this.id = id;
        this.idAbonne = idAbonne;
        this.idLivre = idLivre;
        this.dateRetour = dateRetour;
    }

    public LivreRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    public LivreRetour() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAbonne() {
        return idAbonne;
    }

    public void setIdAbonne(int idAbonne) {
        this.idAbonne = idAbonne;
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    @Override
    public String toString() {
        return  String.valueOf(dateRetour) ;
    }
}
