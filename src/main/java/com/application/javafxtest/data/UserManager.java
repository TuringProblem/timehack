package com.application.javafxtest.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class UserManager {
    private DatabaseManager dbManager;

    public UserManager() {
        dbManager = new DatabaseManager();
    }

    public String generateResetToken(String email) {
        if (!userExists(email)) {
            return null;
        }
        String token = UUID.randomUUID().toString();
        Instant expiry = Instant.now().plus(1, ChronoUnit.HOURS);
        return dbManager.saveResetToken(email, token, expiry) ? token : null;
    }

    public boolean isValisResetToken(String token) { return dbManager.isValidResetToken(token); }

    public boolean resetPassword(String token, String newPassword) {
        if (!isValisResetToken(token)) {
            return false;
        }
        String email = dbManager.getEmailByResetToken(token);
        if (email == null) {
            return false;
        }
        String newHashWithSalt = SHA.hashWithSalt(newPassword, SHA.generateSalt());
        return dbManager.updatePasswordAndClearToken(email, newHashWithSalt);
    }
    public boolean addUser(String email, String password) {
        byte[] salt = SHA.generateSalt();
        String hashWithSalt = SHA.hashWithSalt(password, salt);
        return dbManager.addUser(email, hashWithSalt);
    }

    public boolean authenticateUser(String email, String password) {
        String storedHashWithSalt = dbManager.getUserHashWithSalt(email);
        if (storedHashWithSalt != null) {
            return SHA.verifyPassword(password, storedHashWithSalt);
        }
        return false;
    }
    public boolean userExists(String email) { return dbManager.getUser(email) != null;}
    public User getUser(String email) { return dbManager.getUser(email); }
    public void setUserAsExisting(String email) { dbManager.updateUserStatus(email, false); }

}
