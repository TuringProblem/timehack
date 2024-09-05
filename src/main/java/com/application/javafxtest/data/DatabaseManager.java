package com.application.javafxtest.data;

import com.application.javafxtest.model.Lifestyles;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public boolean addUser(String email, String hashWithSalt) {
        String sql = "INSERT INTO users(email, hash_with_salt,is_new) VALUES(?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmnt = conn.prepareStatement(sql)) {
            pstmnt.setString(1, email);
            pstmnt.setString(2, hashWithSalt);
            pstmnt.setInt(3, 1);
            pstmnt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.printf("Can't add to Database: %s\n", e.getMessage());
            return false;
        }
    }

    public void addLifeStyle(Lifestyles lifestyle) {
        String sql = "INSERT INTO lifestyles (creator_id, title, description, content, is_public) VALUES (?, ?, ?, ?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmnt = conn.prepareStatement(sql)) {
            pstmnt.setInt(1, lifestyle.getCreatorId());
            pstmnt.setString(2, lifestyle.getTitle());
            pstmnt.setString(3, lifestyle.getDescription());
            pstmnt.setString(4, lifestyle.getContent());
            pstmnt.setBoolean(5, lifestyle.isPublic());
            pstmnt.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error adding lifestyle: " + e.getMessage());
        }
    }

    public List<Lifestyles> getPublicLifestyles() {
        List<Lifestyles> lifestylesList = new ArrayList<>();
        String sql = "SELECT * FROM lifestyles WHERE is_public = true";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Lifestyles lifestyle = new Lifestyles(
                rs.getInt("id"),
                rs.getInt("creator_id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("content"),
                rs.getBoolean("is_public"));
                lifestylesList.add(lifestyle);
            }

        } catch (SQLException e) {
            System.out.println("Error getting public lifestyles: " + e.getMessage());
        }
        return lifestylesList;
    }

    public User getUser(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmnt = conn.prepareStatement(sql)) {
            pstmnt.setString(1, email);
            ResultSet rs = pstmnt.executeQuery();

            if (rs.next()) {
                return new User (rs.getString("email"), rs.getString("hash_with_salt"), rs.getInt("is_new") == 1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getUserHashWithSalt(String email) {
        String sql = "SELECT hash_with_salt FROM users WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement pstmnt = conn.prepareStatement(sql)) {
            pstmnt.setString(1, email);
            ResultSet rs = pstmnt.executeQuery();
            if (rs.next()) {
                return rs.getString("hash_with_salt");
            }
        } catch (SQLException e) {
            System.out.printf("Error getting user hash: %s\n", e.getMessage());
        }
        return null;
    }

    public boolean emailExists(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmnt = conn.prepareStatement(sql)) {
            pstmnt.setString(1, email);
            try (ResultSet rs = pstmnt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.printf("Error checking if email exists: %s\n", e.getMessage());
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

    public boolean saveResetToken(String email, String token, Instant expiry) {
       String sql = "UPDATE users SET reset_token = ?, reset_token_expiry = ? WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmnt = conn.prepareStatement(sql)) {
            pstmnt.setString(1, token);
            pstmnt.setTimestamp(2, Timestamp.from(expiry));
            pstmnt.setString(3, email);
            int affectedRows = pstmnt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.printf("Error loading %s\n", e.getMessage());
            return false;
        }
    }

    public boolean isValidResetToken(String token) {
        String sql = "SELECT reset_token_expiry FROM users WHERE reset_token = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmnt = conn.prepareStatement(sql)) {
            pstmnt.setString(1, token);
            ResultSet rs = pstmnt.executeQuery();
                if (rs.next()) {
                    Timestamp expiry = rs.getTimestamp("reset_token_expiry");
                    return expiry != null && expiry.toInstant().isAfter(Instant.now());
                }
            } catch (SQLException e) {
                System.out.printf("Error checking reset token: %s\n", e.getMessage());
            }
            return false;
        }

        public String getEmailByResetToken(String token) {
        String sql = "SELECT email FROM users WHERE reset_token = ?";
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement pstmnt = conn.prepareStatement(sql)) {
               pstmnt.setString(1, token);
               ResultSet rs = pstmnt.executeQuery();
               if (rs.next()) {
                   return rs.getString("email");
               }
            }catch (SQLException e) {
                System.out.printf("Error getting email by reset token: %s\n", e.getMessage());
            }
            return null;
        }
        public boolean updatePasswordAndClearToken(String email, String newHashWithSalt) {
            String sql = "UPDATE users SET hash_with_salt = ?, reset_token = NULL, reset_token_expiry = NULL WHERE email = ?";
            try (Connection conn = DriverManager.getConnection(DB_URL);
                 PreparedStatement pstmnt = conn.prepareStatement(sql)) {
                pstmnt.setString(1, newHashWithSalt);
                pstmnt.setString(2, email);
                int affectedRows = pstmnt.executeUpdate();
                return affectedRows > 0;
            } catch (SQLException e) {
                System.out.printf("Error updating password and clearing token: %s\n");
                return false;
            }
        }
}

