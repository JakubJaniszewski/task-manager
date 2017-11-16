package com.capg.task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;

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