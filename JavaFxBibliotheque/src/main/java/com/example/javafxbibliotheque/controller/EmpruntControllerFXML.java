package com.example.javafxbibliotheque.controller;

import com.example.javafxbibliotheque.db.Connexion;
import com.example.javafxbibliotheque.model.Abonne;
import com.example.javafxbibliotheque.model.Livre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class EmpruntControllerFXML implements Initializable  {

    PreparedStatement st = null;
    ResultSet rs = null;
    Connection con = null;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane borderPane;

    @FXML
    private DatePicker dateRetour;

    @FXML
    private TextField idAbonneEmp;

    @FXML
    private TextField idAbonneRe;

    @FXML
    private Label labelAbonne;

    @FXML
    private Label labelAbonneRe;

    @FXML
    private Label labelDate;

    @FXML
    private Label labelLivre;

    @FXML
    private Label labelLivreRe;

    @FXML
    private ComboBox<String> listAbonneEmp;

    @FXML
    private ComboBox<String> listAbonneRe;

    @FXML
    private ComboBox<String> listLivres;

    @FXML
    private ComboBox<String> listLivresRe;

    List<Integer> listIdAbonne= new ArrayList<>();
    List<Integer> listIdAbonneRe= new ArrayList<>();

    List<Integer> listIdLivre= new ArrayList<>();
    List<Integer> listIdLivreRetour= new ArrayList<>();


    private int idLivreSelected;
    private int idLivreSelectedRetour;
    AbonneControllerFXML abonnes= new AbonneControllerFXML();
    LivreControllerFXML livres= new LivreControllerFXML();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListEmprunt();
        labelAbonne.setVisible(false);
        labelDate.setVisible(false);
        labelLivre.setVisible(false);
        labelLivreRe.setVisible(false);
        labelAbonneRe.setVisible(false);
        setListAbonneRetour();
    }
    @FXML
    void clearEventRetour(ActionEvent event) {
        clearRe();
    }

    @FXML
    void onBtnAdd(ActionEvent event) {
        boolean verif = true;
        if(idAbonneEmp.getText().isEmpty()) {
            verif=false;
            labelAbonne.setVisible(true);
        }
        if(listLivres.getSelectionModel().isEmpty()) {
            verif = false;
            labelLivre.setVisible(true);
        }
        if(dateRetour.getValue()==null) {
            verif = false;
            labelDate.setVisible(true);
        }
        if(verif) {
            insert();
            clear();
        }
    }

    @FXML
    void onBtnAddRe(ActionEvent event) {
        boolean verif = true;
        if( idAbonneRe.getText().isBlank()) {
            verif=false;
            labelAbonneRe.setVisible(true);
        }
        if(listLivresRe.getSelectionModel().isEmpty()) {
            verif = false;
            labelLivreRe.setVisible(true);
        }
        if(verif) {
            insertRetour();
            clearRe();
            setListAbonneRetour()   ;
        }
    }



    @FXML
    void myComboBoxAbonne(ActionEvent event) {
        if (listAbonneEmp.getSelectionModel().getSelectedItem() == null) {
            System.out.println("List vide");
        } else {
            int selectedIndex = listAbonneEmp.getSelectionModel().getSelectedIndex();
            idAbonneEmp.setText(String.valueOf(listIdAbonne.get(selectedIndex)));
        }
    }

    @FXML
    void myComboBoxAbonneRetour(ActionEvent event) {
        if (listAbonneRe.getSelectionModel().getSelectedItem() == null) {
            System.out.println("List vide");
        } else {
            int selectedIndex = listAbonneRe.getSelectionModel().getSelectedIndex();
            System.out.println(selectedIndex);
            idAbonneRe.setText(String.valueOf(listIdAbonneRe.get(selectedIndex)));
            ObservableList<Livre> livreObservableList = livres.getLivresForRetourByIdAbonne(Integer.parseInt(idAbonneRe.getText()));
            ObservableList<String> observableListLivres = FXCollections.observableArrayList();;
            for(Livre livre:livreObservableList){
                observableListLivres.add(livre.getTitre());
                listIdLivreRetour.add(livre.getId());
            }
            listLivresRe.setItems(observableListLivres );
        }
    }
    @FXML
    void myComboBoxLivre(ActionEvent event) {
       if( listLivres.getSelectionModel().getSelectedItem()==null){
           System.out.println("List vide");
       }else {
           int selectedIndexLivre = listLivres.getSelectionModel().getSelectedIndex();
           idLivreSelected = listIdLivre.get(selectedIndexLivre);
           System.out.println(idLivreSelected);
       }
    }

    @FXML
    void myComboBoxLivreRetour(ActionEvent event) {
        if( listLivresRe.getSelectionModel().getSelectedItem()==null){
            System.out.println("List vide");
        }else {
            int selectedIndexLivre = listLivresRe.getSelectionModel().getSelectedIndex();
            idLivreSelectedRetour = listIdLivreRetour.get(selectedIndexLivre);
            System.out.println(idLivreSelected);
        }
    }

    @FXML
    private void clearEventEmprunt(ActionEvent event) {
        clear();
    }


    private void insert() {
        //System.out.println("zz"+id+' '+nom+" "+prenom);
        con = Connexion.getCon();
        String insert = "INSERT INTO emprunt(idAbonne,idLivre,dateEmprunt,dateRetour) VALUES(?,?,?,?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.US);

        try {
            st = con.prepareStatement(insert);
            st.setString(1, idAbonneEmp.getText());
            st.setString(2, String.valueOf(idLivreSelected));
            st.setString(3,new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
            st.setString(4, (dateRetour.getValue()).format(formatter));
            st.executeUpdate();
            Alert a = new Alert(    Alert.AlertType.CONFIRMATION,"Insertion avec succès");
            a.show();
        } catch ( SQLException ex) {
            Logger.getLogger(AbonneControllerFXML.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }



    private void insertRetour() {
        //System.out.println("zz"+id+' '+nom+" "+prenom);
        con = Connexion.getCon();
        String insert = "INSERT INTO retour(idAbonne,idLivre,dateRetour) VALUES(?,?,?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd",Locale.US);

        try {
            st = con.prepareStatement(insert);
            st.setString(1, idAbonneRe.getText());
            st.setString(2, String.valueOf(idLivreSelectedRetour));
            st.setString(3,new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
            st.executeUpdate();
            Alert a = new Alert(    Alert.AlertType.CONFIRMATION,"Insertion avec succès");
            a.show();
        } catch ( SQLException ex) {
            Logger.getLogger(AbonneControllerFXML.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public  void setListEmprunt(){
        ObservableList<Abonne> abonneObservableList = abonnes.getAbonnes();
        ObservableList<String> observableList = FXCollections.observableArrayList();;
        for(Abonne abonne1:abonneObservableList){
            observableList.add(abonne1.getPrenom()+" "+abonne1.getNom());
            listIdAbonne.add(abonne1.getId());
        }
        listAbonneEmp.setItems(observableList);
        ObservableList<Livre> livreObservableList = livres.getLivresDisponible();
        ObservableList<String> observableListLivres = FXCollections.observableArrayList();;
        for(Livre livre:livreObservableList){
            observableListLivres.add(livre.getTitre());
            listIdLivre.add(livre.getId());
        }
        listLivres.setItems(observableListLivres );
    }
    public  void setListAbonneRetour(){
        // List Abonne
        ObservableList<Abonne> abonneObservableList = abonnes.getAbonnesForRetour();
        ObservableList<String> observableList = FXCollections.observableArrayList();;
        for(Abonne abonne1:abonneObservableList){
            observableList.add(abonne1.getPrenom()+" "+abonne1.getNom());
            listIdAbonneRe.add(abonne1.getId());
        }
        listAbonneRe.setItems(observableList);
        //list Livre

    }

    void clear() {
        labelDate.setVisible(false);
        labelLivre.setVisible(false);
        labelAbonne.setVisible(false);
        idAbonneEmp.setText(null);
        dateRetour.setValue(null);
        listLivres.getSelectionModel().clearSelection();
        listAbonneEmp.getSelectionModel().clearSelection();
    }
    void clearRe() {
        labelLivreRe.setVisible(false);
        labelAbonneRe.setVisible(false);
        idAbonneRe.setText(null);
        listLivresRe.getSelectionModel().clearSelection();
        listAbonneRe.getSelectionModel().clearSelection();
    }

}
