package com.example.javafxbibliotheque;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class SideBarController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane borderPane;

    private void loadPage(String page) throws IOException {
        Parent root = null;
        String s = page+"-view.fxml";
        root = FXMLLoader.load(getClass().getResource( s));
        borderPane.setCenter(root);
    }
    @FXML
    void livrePage(ActionEvent event) throws IOException {
        loadPage("livre");
    }
    @FXML
    void abonnePage(ActionEvent event) throws IOException {
        loadPage("abonne");
    }

    @FXML
    void empruntPage(ActionEvent event) throws IOException {
        loadPage("emprunt");
    }
    @FXML
    void historiquePage(ActionEvent event) throws IOException {
        loadPage("historique");
    }

}
