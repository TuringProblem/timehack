package com.application.javafxtest.data;

import java.sql.*;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:users.db";

    public DatabaseManager() {
    }

    private void intializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "email TEXT PRIMARY KEY," +
                     "password_hash TEXT NOT NULL," +
                     "salt TEXT NOT NULL," +
                     "is_new INTEGER NOT NULL)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmnt = conn.createStatement()) {
            stmnt.execute(sql);
        } catch (SQLException e) {
            System.out.printf("Error initializing Database: %s", e.getMessage());
        }

    }
}

