package com.example.javafxbibliotheque.controller;

import com.example.javafxbibliotheque.db.Connexion;
import com.example.javafxbibliotheque.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HistoriqueControllerFXML implements Initializable {

    PreparedStatement st = null;
    ResultSet rs = null;
    Connection con = null;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private TableColumn<Historique,Emprunt> colDateEmp;

    @FXML
    private TableColumn<Historique, LivreRetour> colDateRe;


    @FXML
    private TableColumn<Historique,Abonne> colAbonne;

    @FXML
    private TableColumn<Historique, String> colJourRetard;

    @FXML
    private TableColumn<Historique, Livre> colTitre;


    @FXML
    private TextField filter;

    @FXML
    private TableView<Historique> tableHis;

    @FXML
    private Label nbAbonnes;

    @FXML
    private Label nbLivreEmp;

    @FXML
    private Label nbLivreRetour;
    ObservableList<Historique> dataList;


    AbonneControllerFXML abonne = new AbonneControllerFXML();
    EmpruntControllerFXML emprunt = new EmpruntControllerFXML();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchAbonne();
        nbAbonnes.setText(String.valueOf(abonne.getAbonnes().size()));
        nbLivreEmp.setText(String.valueOf(getCountEmprunt()));
        nbLivreRetour.setText(String.valueOf(getCountRetour()));
    }

    public ObservableList<Historique> getLivresHistorique() {
        ObservableList<Historique> list = FXCollections.observableArrayList();

        String select = "select nom , prenom , titre  ,dateEmprunt, e.dateRetour ,r.dateRetour   " +
                "from abonne a , emprunt e , retour r , livre l\n" +
                "where a.id = e.idAbonne and a.id=r.idAbonne and l.id = r.idLivre";
        con = Connexion.getCon();
        try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
                Historique historique = new Historique();
                historique.setAbonne(new Abonne(rs.getString("nom"), rs.getString("prenom")));
                historique.setLivre(new Livre(rs.getString("titre")));
                historique.setEmprunt(new Emprunt(rs.getDate("dateEmprunt"), rs.getDate("e.dateRetour")));
                historique.setLivreRetour(new LivreRetour(rs.getDate("r.dateRetour")));
                historique.setNbJourRetard(historique.getNbJourRetard());
                list.add(historique);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbonneControllerFXML.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public void searchAbonne() {

        colAbonne.setCellValueFactory(new PropertyValueFactory<Historique, Abonne>("abonne"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Historique, Livre>("livre"));
        colDateEmp.setCellValueFactory(new PropertyValueFactory<Historique, Emprunt>("emprunt"));
        colDateRe.setCellValueFactory(new PropertyValueFactory<Historique, LivreRetour>("livreRetour"));
        colJourRetard.setCellValueFactory(new PropertyValueFactory<Historique, String>("nbJourRetard"));

        dataList = getLivresHistorique();
        tableHis.setItems(dataList);
        FilteredList<Historique> filteredData = new FilteredList<>(dataList, b -> true);
        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(historique -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (historique.getAbonne().getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches username
                } else if (historique.getAbonne().getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                } else if (String.valueOf(historique.getNbJourRetard()).indexOf(lowerCaseFilter) != -1)
                    return true;// Filter matches email
                else if (historique.getLivre().getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true; // Filter matches password
                else
                    return false; // Does not match.
            });
        });
        SortedList<Historique> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableHis.comparatorProperty());
        tableHis.setItems(sortedData);
    }

    public int getCountEmprunt() {
        ObservableList<Abonne> list = FXCollections.observableArrayList();

        String select = "SELECT count(*) from emprunt";
        con = Connexion.getCon();
        try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            if (rs.next())
                return rs.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(AbonneControllerFXML.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getCountRetour() {
        ObservableList<Abonne> list = FXCollections.observableArrayList();

        String select = "SELECT count(*) from retour";
        con = Connexion.getCon();
        try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            if (rs.next())
                return rs.getInt(1);

        } catch (SQLException ex) {
            Logger.getLogger(AbonneControllerFXML.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return 0;
    }


}
