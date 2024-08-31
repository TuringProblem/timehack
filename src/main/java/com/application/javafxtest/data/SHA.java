package com.application.javafxtest.data;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

/**
 * @author Override
 * @since 08/27/2024 @19:53
 * @see <a href="https://github.com/TuringProblem">GitHub Profile</a>
 */

public class SHA {
    private static final int STORED_LENGTH = 40;

    public static byte[] generateSalt() {
        Random random = new Random();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    public static String hashWithSalt(String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt);
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            byte[] combined =  new byte[salt.length + hashBytes.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(hashBytes, 0, combined, salt.length, hashBytes.length);

            String base64Encoded = Base64.getEncoder().encodeToString(combined);
            return base64Encoded.substring(0, Math.min(base64Encoded.length(), STORED_LENGTH));
        } catch (NoSuchAlgorithmException e) {
          throw new RuntimeException("SHA-256 algorithmn is not available", e);
        }
    }

    public static boolean verifyPassword(String password, String storedHashWithSalt) {
        try {
            byte[] decodedStored = Base64.getDecoder().decode(storedHashWithSalt);
            byte[] salt = Arrays.copyOfRange(decodedStored, 0, 16);

            String computedHash = hashWithSalt(password, salt);
            return computedHash.equalsIgnoreCase(storedHashWithSalt);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getCause());
            return false;
        }
    }
}
