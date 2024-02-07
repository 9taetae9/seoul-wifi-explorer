package com.example.seoulwifiexplorer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private static final String JDBC_URL = "jdbc:sqlite:/Users/taehyounkim/seoulwifi.db";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL);
    }
}
