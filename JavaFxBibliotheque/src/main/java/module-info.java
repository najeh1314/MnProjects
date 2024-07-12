module com.example.javafxbibliotheque {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.javafxbibliotheque to javafx.fxml;
    exports com.example.javafxbibliotheque;
    exports com.example.javafxbibliotheque.db;
    opens com.example.javafxbibliotheque.db to javafx.fxml;
    exports com.example.javafxbibliotheque.controller;
    opens com.example.javafxbibliotheque.controller to javafx.fxml;
    exports com.example.javafxbibliotheque.model;
    opens com.example.javafxbibliotheque.model to javafx.fxml;
}