package com.capg.task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;

    // Database connection singleton, makes sure that you can't
    // initialize 2 Connections
    static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection("jdbc:sqlite:src/main/database/database");
        }

        return connection;
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

}