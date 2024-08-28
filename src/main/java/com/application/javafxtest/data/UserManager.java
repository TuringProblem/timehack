package com.application.javafxtest.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserManager {
    private DatabaseManager dbManager;

    public UserManager() {
        dbManager = new DatabaseManager();
    }

    public boolean addUser(String email, String password) {
        byte[] salt = SHA.generateSalt();
        int[] hashWithSalt = SHA.hashWithSalt(password, salt);
        int[] truncatedHashWithSalt = java.util.Arrays.copyOf(hashWithSalt, 40);
        return dbManager.addUser(email, truncatedHashWithSalt);
    }

    public boolean authenticateUser(String email, String password) {
        int[] storedHashWithSalt = dbManager.getUserHashWithSalt(email);
        if (storedHashWithSalt != null) {
            return SHA.verifyPassword(password, storedHashWithSalt);
        }
        return false;
    }
    public boolean userExists(String email) { return dbManager.getUser(email) != null;}
    public User getUser(String email) { return dbManager.getUser(email); }
    public void setUserAsExisting(String email) { dbManager.updateUserStatus(email, false); }

}
