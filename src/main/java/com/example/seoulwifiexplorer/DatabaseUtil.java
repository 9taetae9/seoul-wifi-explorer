package com.example.seoulwifiexplorer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    private static final String JDBC_URL = "jdbc:sqlite:seoulwifi.db";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL);
    }
}
