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

    public static byte[] generateSalt() {
        Random random = new Random();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
    public static int[] hashWithSalt(String password, byte[] salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(salt);
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            byte[] combined =  new byte[salt.length + hashBytes.length];
            System.arraycopy(salt, 0, combined, 0, salt.length);
            System.arraycopy(hashBytes, 0, combined, salt.length, hashBytes.length);

            int[] result = new int[combined.length];
            for (int i = 0; i < result.length; i++) {
              result[i] = combined[i] & 0xff;
            }
            return result;
        } catch (NoSuchAlgorithmException e) {
          throw new RuntimeException("SHA-256 algorithmn is not available", e);
        }
    }

    public static boolean verifyPassword(String password, int[] storedHashWithSalt) {
      byte[] salt = new byte[16];
      for (int i = 0; i < 16; i++) {
        salt[i] = (byte) storedHashWithSalt[i];
      }

      int[] computedHash = hashWithSalt(password, salt);
      return java.util.Arrays.equals(computedHash, storedHashWithSalt);
    }
}
