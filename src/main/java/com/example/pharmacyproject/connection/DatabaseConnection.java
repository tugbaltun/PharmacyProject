package com.example.pharmacyproject.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    private String jdbcURL = "jdbc:postgresql://localhost:5432/pharmacy";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "46tA80";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return connection;
    }
}
