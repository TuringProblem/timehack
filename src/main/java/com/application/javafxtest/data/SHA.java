package com.application.javafxtest.data;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

/**
 * @author Override
 * @since 08/27/2024 @19:53
 * @see <a href="https://github.com/TuringProblem">GitHub Profile</a>
 */

public class SHA {

    public static String generateSalt() {
        Random random = new Random();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hash(String data, String salt) {

        try {
            MessageDigest message = MessageDigest.getInstance("SHA-256");
            String saltedData = salt + data;
            byte[] hashBytes = message.digest(saltedData.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithmn is not working", e);
        }
    }

    public static void printPasswordWithEncryption(String key) {
        String salt = generateSalt();
        String hashedKey = hash(key, salt);
        System.out.printf("Password Key: %s", hashedKey);
    }

    public static void showData() {
        String originalExample = "my_secure_password";
        String userNameExample = "dannyDuncan69";
        String salt = generateSalt();
        String hashedString = hash(originalExample, salt);

        System.out.printf("Original: %s\n", originalExample);
        System.out.printf("Salt: %s\n", salt);
        System.out.printf("Original with Hash and Salt: %s\n", hashedString);
    }

}
