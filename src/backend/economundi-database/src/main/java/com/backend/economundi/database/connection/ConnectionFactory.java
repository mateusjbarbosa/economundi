package com.backend.economundi.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Connection conn;
//    private static final String URL = "jdbc:postgresql://ec2-54-83-55-125.compute-1.amazonaws.com:5432/d1hs6sa6cul39d";
//    private static final String USER = "ysblxtghtfkpgc";
//    private static final String PASS = "a868c3f911af9082d689e17fd8f338d9da693db6811a578968c92444f3e4d4c6";

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
