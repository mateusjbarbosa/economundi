package com.backend.economundi.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Connection conn;
    private static final String URL = "jdbc:postgresql://localhost:5432/"
            + "economundi";
    private static final String USER = "postgres";
    private static final String PASS = "123456";

    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return conn;
    }
}
