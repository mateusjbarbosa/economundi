package com.backend.economundi.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Connection conn;
    private static final String URL = "jdbc:postgresql://ec2-54-235-104-136.compute-1.amazonaws.com:5432/d1879849b6kvan";
    private static final String USER = "jjzteazvhqtblu";
    private static final String PASS = "efc2716b937813ea5e77cfad02f20787fd934da14f9d1c99833e5ed216199b45";

    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return conn;
    }
}
