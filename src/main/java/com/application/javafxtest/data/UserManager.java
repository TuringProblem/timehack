package com.application.javafxtest.data;

public class UserManager {
    private DatabaseManager dbManager;

    public UserManager() {
        dbManager = new DatabaseManager();
    }
    public boolean addUser(String email, String password) {
        String salt = SHA.generateSalt();
        String hashedPassword = SHA.hash(password, salt);
        hashedPassword = hashedPassword.substring(0, Math.min(hashedPassword.length(), 25));
        return dbManager.addUser(email, hashedPassword, salt, true);
    }


}
