package com.gestor.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class SecurityService {
    public static String hashString(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            byte[] hashBytes = messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder hexString = new StringBuilder();

            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);

                if (hex.length() == 1) hexString.append("0");

                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Critical Error: SHA-512 algorithm not found.", e);
        }
    }

    public static boolean verifyHash(String plainText, String hash) {
        String hashedInput = hashString(plainText);
        return hashedInput.equalsIgnoreCase(hash);
    }
}