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
    private static final String JDBC_URL = "jdbc:sqlite:/Users/taehyounkim/seoul-wifi-explorer/databases/seoul_wifi.db";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL);
    }

    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connection to SQLite has been established.");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Connection test failed.");
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(testConnection());
    }
}
