package com.example.javafxbibliotheque.model;

public class Livre {

    private int id;
    private String titre;
    private String editeur;
    private String type;


    public Livre(){}

    public Livre(String titre) {
        this.titre = titre;
    }

    public Livre(int id, String titre, String editeur, String type) {
        this.id = id;
        this.titre = titre;
        this.editeur = editeur;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return titre ;
    }

    public void setType(String type) {
        this.type = type;
    }
}
