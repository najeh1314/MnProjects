package com.example.javafxbibliotheque.controller;

import com.example.javafxbibliotheque.db.Connexion;
import com.example.javafxbibliotheque.model.Abonne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AbonneControllerFXML implements Initializable {


    PreparedStatement st = null;
    ResultSet rs = null;
    Connection con = null;
    ObservableList<Abonne> dataList;


    @FXML
    private Label labelPrenom;

    @FXML
    private Label labelNom ;
    @FXML
    private TextField id;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;


    @FXML
    private Button bdelete;

    @FXML
    private Button bfind;

    @FXML
    private Button bsave;

    @FXML
    private Button bupdate;

    @FXML
    private TableColumn<Abonne, Integer> colId;

    @FXML
    private TableColumn<Abonne, String> colNom;

    @FXML
    private TableColumn<Abonne, String> colPrenom;

    @FXML
    private TableView<Abonne> table;



    @FXML
    void onBtnAdd(ActionEvent event) {
        if(nom.getText().isBlank()&& prenom.getText().isBlank() ) {
            labelNom.setVisible(true);
            labelPrenom.setVisible(true);
            } else if(prenom.getText().isEmpty()) {
            labelPrenom.setVisible(true);
        }
         else if(nom.getText().isEmpty()) {
            labelNom.setVisible(true);
        }
         else {
            insert(nom.getText(), prenom.getText());
            clear();
        }
    }

    @FXML
    void onBtnDelete(ActionEvent event) {
        delete();
        clear();
    }


    @FXML
    void onBtnUpdate(ActionEvent event) {
        update();
        clear();
        bsave.setDisable(false);
    }
    private int idSelected;
    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        Abonne et = table.getSelectionModel().getSelectedItem();
        idSelected  =  et.getId();
        nom.setText(et.getNom());
        prenom.setText(et.getPrenom());
        bsave.setDisable(true);
    }
    @FXML
    private void clearEvent(ActionEvent event) {
        clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelPrenom.setVisible(false);
        labelNom.setVisible(false);
        searchAbonne();

    }

    public void searchAbonne(){
        colId.setCellValueFactory(new PropertyValueFactory<Abonne,Integer>("id"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<Abonne,String>("nom"));
        colNom.setCellValueFactory(new PropertyValueFactory<Abonne,String>("prenom"));

        dataList = getAbonnes();
        table.setItems(dataList);
        FilteredList<Abonne> filteredData = new FilteredList<>(dataList, b -> true);
        id.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(abonne -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (abonne.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                } else if (abonne.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (String.valueOf(abonne.getId()).indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<Abonne> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
    private void insert(String nom , String prenom) {
        //System.out.println("zz"+id+' '+nom+" "+prenom);
        con = Connexion.getCon();
        String insert = "INSERT INTO abonne(nom,prenom) VALUES(?,?)";
        try {
            st = con.prepareStatement(insert);
            st.setString(1, nom);
            st.setString(2, prenom);
            st.executeUpdate();
            affiche();
        } catch ( SQLException ex) {
            Logger.getLogger(AbonneControllerFXML.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Abonne> getAbonnes() {
        ObservableList<Abonne> list = FXCollections.observableArrayList();

        String select = "SELECT * from abonne";
        con = Connexion.getCon();
        try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
                Abonne et = new Abonne();
                et.setId(rs.getInt("id"));
                et.setNom(rs.getString("nom"));
                et.setPrenom(rs.getString("prenom"));
                list.add(et);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbonneControllerFXML.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;

    }

    public ObservableList<Abonne> getAbonnesForRetour() {
        ObservableList<Abonne> list = FXCollections.observableArrayList();

        String select = "select distinct id , nom , prenom  from abonne a , emprunt e  where a.id=e.idAbonne and a.id not in (select idAbonne from retour )";
        con = Connexion.getCon();
        try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
                Abonne et = new Abonne();
                et.setId(rs.getInt("id"));
                et.setNom(rs.getString("nom"));
                et.setPrenom(rs.getString("prenom"));
                list.add(et);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbonneControllerFXML.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;

    }
    public void affiche() {
        ObservableList<Abonne> list = getAbonnes();
        colId.setCellValueFactory(new PropertyValueFactory<Abonne, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Abonne, String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<Abonne, String>("prenom"));
        table.setItems(list);
    }

    public void delete() {
        con = Connexion.getCon();
        String delete = "DELETE FROM abonne  where id = ?";
        try {
            st = con.prepareStatement(delete);
            st.setInt(1, idSelected);
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(AbonneControllerFXML.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    private void update() {
        con = Connexion.getCon();
        String update
                = "UPDATE abonne SET nom =?,prenom = ? where id =?";
        try {
            st = con.prepareStatement(update);
            st.setString(1, nom.getText());
            st.setString(2, prenom.getText());
            st.setInt(3, this.idSelected);
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(AbonneControllerFXML.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    void clear() {
        id.setText(null);
        nom.setText(null);
        prenom.setText(null);
        bsave.setDisable(false);
        labelNom.setVisible(false);
        labelPrenom.setVisible(false);
    }
}
