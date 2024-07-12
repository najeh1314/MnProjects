package com.example.javafxbibliotheque.model;

import java.util.Date;

public class Emprunt {
    private  int id ;
    private int idAbonne;
    private int idLivre;
    private Date dateEmprunt;
    private Date dateRetour;

    public Emprunt(Date dateEmprunt, Date dateRetour) {
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public Emprunt(int id, int idAbonne, int idLivre, Date dateEmprunt, Date dateRetour) {
        this.id = id;
        this.idAbonne = idAbonne;
        this.idLivre = idLivre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
    }

    public Emprunt() {
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

    public Date getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
        this.dateRetour = dateRetour;
    }

    @Override
    public String toString() {
        return  String.valueOf(dateEmprunt);
    }
}
