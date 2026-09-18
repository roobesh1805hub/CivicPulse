package com.smartcity.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/smart_city_db";

    private static final String USER = "root";

    private static final String PASSWORD =
            System.getenv("SMART_CITY_DB_PASSWORD");

    public static Connection getConnection() {

        try {

            return DriverManager.getConnection(
                    URL,
                    USER,
                    PASSWORD
            );

        } catch (SQLException e) {

            System.out.println(
                    "Database connection failed."
            );

            System.out.println(
                    "Database Error: " + e.getMessage()
            );

            return null;
        }
    }
}