package com.example.javafxbibliotheque.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connexion {

    static String url = "jdbc:mysql://localhost:3306/mybibliotheque";
    static String user = "root";
    static String pass = "";
    static String driver = "com.mysql.jdbc.Driver";

    @SuppressWarnings("exports")
	public static Connection getCon() {
        Connection con = null;  

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Ok");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Connexion.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return con;

    }

}