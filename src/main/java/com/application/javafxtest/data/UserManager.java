package com.application.javafxtest.data;

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
    public boolean isNewUser(String email) { return dbManager.isNewUser(email); }
    public void setUserAsExisting(String email) { dbManager.updateUserStatus(email, false); }

}
