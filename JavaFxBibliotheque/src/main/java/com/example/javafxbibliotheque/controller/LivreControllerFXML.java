package com.example.javafxbibliotheque.controller;

import com.example.javafxbibliotheque.db.Connexion;
import com.example.javafxbibliotheque.model.Abonne;
import com.example.javafxbibliotheque.model.Livre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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

public class LivreControllerFXML  implements Initializable {

    PreparedStatement st = null;
    ResultSet rs = null;
    Connection con = null;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button bdelete;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button bsave;

    @FXML
    private Button bupdate;

    @FXML
    private TableColumn<Livre, Integer> colId;

    @FXML
    private TableColumn<Livre, String> colTitre;

    @FXML
    private TableColumn<Livre, String> colEditeur;

    @FXML
    private TableColumn<Livre, String> colType;

    @FXML
    private TextField editeur;

    @FXML
    private TextField filter;

    @FXML
    private Label labelEditeur;

    @FXML
    private Label labelTitre;

    @FXML
    private Label labelType;

    @FXML
    private TableView<Livre> table;

    @FXML
    private TextField titre;

    @FXML
    private ComboBox<String> type;
    ObservableList<Livre> dataList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> typesLivre
                = FXCollections.observableArrayList( "Sentimentaux",
                "Apprentissage",
                "Aventures",
                "Philosophique",
                "Historique");
        type.setItems(typesLivre);
        type.setValue("Historique");
        labelEditeur.setVisible(false);
        labelType.setVisible(false);
        labelTitre.setVisible(false);
        searchLivre();
    }
    @FXML
    void clearEvent(ActionEvent event) {
        clear();
    }

    @FXML
    void onBtnAdd(ActionEvent event) {
        if(titre.getText().isBlank()&& editeur.getText().isBlank()) {
            labelTitre.setVisible(true);
            labelType.setVisible(true);
        } else if(titre.getText().isEmpty()) {
            labelTitre.setVisible(true);
        }
        else if(editeur.getText().isEmpty()) {
            labelEditeur.setVisible(true);
        }
        else {
            insert();
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
    }

    private int idSelected;

    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        Livre livre = table.getSelectionModel().getSelectedItem();
        idSelected  =  livre.getId();
        titre.setText(livre.getTitre());
        editeur.setText(livre.getEditeur());
        type.getSelectionModel().select(livre.getType());
        bsave.setDisable(true);
    }

    public ObservableList<Livre> getLivres() {
        ObservableList<Livre> list = FXCollections.observableArrayList();

        String select = "SELECT * from livre";
        con = Connexion.getCon();
        try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
                Livre livre = new Livre();
                livre.setId(rs.getInt("id"));
                livre.setTitre(rs.getString("titre"));
                livre.setEditeur(rs.getString("editeur"));
                livre.setType(rs.getString("type"));
                list.add(livre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbonneControllerFXML.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;

    }


    public ObservableList<Livre> getLivresDisponible() {
        ObservableList<Livre> list = FXCollections.observableArrayList();

        String select = "SELECT livre.id ,  livre.titre\n" +
                "FROM livre\n" +
                "LEFT JOIN emprunt ON livre.id = emprunt.idLivre\n" +
                "LEFT JOIN retour ON livre.id = retour.idLivre\n" +
                "WHERE emprunt.idLivre IS NOT NULL AND retour.idLivre IS NOT NULL\n" +
                "UNION\n" +
                "SELECT livre.id,livre.titre\n" +
                "FROM livre\n" +
                "LEFT JOIN emprunt ON livre.id = emprunt.idLivre\n" +
                "WHERE emprunt.idLivre IS NULL\n";
        con = Connexion.getCon();
        try {
            st = con.prepareStatement(select);
            rs = st.executeQuery();
            while (rs.next()) {
                Livre livre = new Livre();
                livre.setId(rs.getInt("id"));
                livre.setTitre(rs.getString("titre"));
                list.add(livre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbonneControllerFXML.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<Livre> getLivresForRetourByIdAbonne(int idAbonne) {
        ObservableList<Livre> list = FXCollections.observableArrayList();

        String select = " SELECT livre.id,  livre.titre FROM livre\n" +
                "        JOIN emprunt ON livre.id = emprunt.idLivre\n" +
                "        WHERE emprunt.idAbonne =?";

                con = Connexion.getCon();
        try {
            st = con.prepareStatement(select);
            st.setInt(1, idAbonne);
            rs = st.executeQuery();
            while (rs.next()) {
                Livre livre = new Livre();
                livre.setId(rs.getInt("id"));
                livre.setTitre(rs.getString("titre"));
                list.add(livre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbonneControllerFXML.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return list;

    }



    public void affiche() {
        ObservableList<Livre> list = getLivres();
        colId.setCellValueFactory(new PropertyValueFactory<Livre, Integer>("id"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Livre, String>("titre"));
        colEditeur.setCellValueFactory(new PropertyValueFactory<Livre, String>("editeur"));
        colType.setCellValueFactory(new PropertyValueFactory<Livre, String>("type"));
        table.setItems(list);
    }

    private void insert() {
        con = Connexion.getCon();
        String insert = "INSERT INTO livre (titre,editeur,type)VALUES(?,?,?)";
        try {
            st = con.prepareStatement(insert);
            st.setString(1, titre.getText());
            st.setString(2, editeur.getText());
            st.setString(3, type.getSelectionModel().getSelectedItem());
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(AbonneControllerFXML.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
    void clear() {
        filter.setText(null);
        titre.setText(null);
        editeur.setText(null);
        bsave.setDisable(false);
        labelEditeur.setVisible(false);
        labelType.setVisible(false);
        labelTitre.setVisible(false);
    }

    public void delete() {
        con = Connexion.getCon();
        String delete = "DELETE FROM livre  where id = ?";
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
                = "UPDATE livre SET titre =?,editeur = ?,type = ? where id =?";
        try {
            st = con.prepareStatement(update);
            st.setString(1, titre.getText());
            st.setString(2, editeur.getText());
            st.setString(3, type.getSelectionModel().getSelectedItem());
            st.setInt(4, this.idSelected);
            st.executeUpdate();
            affiche();
        } catch (SQLException ex) {
            Logger.getLogger(AbonneControllerFXML.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    public void searchLivre(){
        colId.setCellValueFactory(new PropertyValueFactory<Livre,Integer>("id"));
        colTitre.setCellValueFactory(new PropertyValueFactory<Livre,String>("titre"));
        colEditeur.setCellValueFactory(new PropertyValueFactory<Livre,String>("editeur"));
        colType.setCellValueFactory(new PropertyValueFactory<Livre,String>("type"));

        dataList = getLivres();
        table.setItems(dataList);
        FilteredList<Livre> filteredData = new FilteredList<>(dataList, b -> true);
        filter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(livre -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (livre.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                } else if (livre.getEditeur().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (livre.getType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (String.valueOf(livre.getId()).indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<Livre> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);
    }
}
