package com.example.javafxbibliotheque.model;

public class Abonne {
    private int id;
    private String nom;
    private String prenom;

    public Abonne(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public Abonne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Abonne() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return nom + " " +  prenom ;
    }
}
