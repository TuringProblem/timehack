package com.application.javafxtest.data;

import java.sql.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Override
 * @since 08/27/2024 @ 20:20
 * @see <a href="https://github.com/TuringProblem">GitHub Profile</a>
 */

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:users.db";

    public DatabaseManager() {
        try {
            Class.forName("org.sqlite.JDBC");
            intializeDatabase();
        } catch (ClassNotFoundException e) {
            System.out.printf("JDBC Driver not found: %s\n", e.getMessage());
        }
    }

    private void intializeDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "email TEXT PRIMARY KEY," +
                     "hash_with_salt NOT NULL," +
                     "is_new INTEGER NOT NULL)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmnt = conn.createStatement()) {
            stmnt.execute(sql);
        } catch (SQLException e) {
            System.out.printf("Error initializing Database: %s\n", e.getMessage());
        }
    }

    public boolean addUser(String email, int[] hashWithSalt) {
        String sql = "INSERT INTO users(email, hash_with_salt,is_new) VALUES(?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmnt = conn.prepareStatement(sql)) {
            pstmnt.setString(1, email);
            pstmnt.setString(2, intArrayToString(hashWithSalt));
            pstmnt.setInt(3, 1);
            pstmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.printf("Can't add to Database: %s\n", e.getMessage());
            return false;
        }
    }
    public User getUser(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmnt = conn.prepareStatement(sql)) {
            pstmnt.setString(1, email);
            ResultSet rs = pstmnt.executeQuery();

            if (rs.next()) {
                return new User (rs.getString("email"), stringToIntArray(rs.getString("hash_with_salt")), rs.getInt("is_new") == 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int[] getUserHashWithSalt(String email) {
        String sql = "SELECT hash_with_salt FROM users WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmnt = conn.prepareStatement(sql)) {
            pstmnt.setString(1, email);
            ResultSet rs = pstmnt.executeQuery();
            if (rs.next()) {
                return stringToIntArray(rs.getString("hash_with_salt"));
            }
        } catch (SQLException e) {
            System.out.printf("Error getting user hash: %s\n", e.getMessage());
        }
        return null;
    }

    public boolean isNewUser(String email) {
        String sql = "SELECT is_new FROM users WHERE email = ?";
         try (Connection conn = DriverManager.getConnection(DB_URL);
         PreparedStatement pstmnt = conn.prepareStatement(sql)) {
             pstmnt.setString(1, email);
             ResultSet rs = pstmnt.executeQuery();

             if (rs.next()) {
                 return rs.getInt("is_new") == 1;
             }
         } catch (SQLException e) {
             System.out.printf("Error checking if user is new: %s\n", e.getMessage());
         }
         return false;
    }

    public void updateUserStatus(String email, boolean isNew) {
        String sql = "UPDATE users SET is_new = ? WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmnt = conn.prepareStatement(sql)) {
            pstmnt.setInt(1, isNew ? 1 : 0);
            pstmnt.setString(2, email);
            pstmnt.executeUpdate();
        } catch (SQLException e) {
            System.out.printf("Error updating user status: %s\n", e.getMessage());
        }
    }
    private String intArrayToString(int[] array) { return Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(",")); }
    private int[] stringToIntArray(String s) { return Arrays.stream(s.split(",")).mapToInt(Integer::parseInt).toArray();}
}

