package com.gestor.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

/**
 * @class SecurityService
 * @description Service class dedicated to handling sensitive data security using the SHA-512 hashing algorithm.
 * This class provides utility methods to transform plain text into secure hashes and verify them.
 */
public class SecurityService {

    /**
     * @method hashString
     * @description Generates the SHA-512 hash from a given plain text string.
     * @param {String} str - The raw input string to be hashed (usually a password).
     * @returns {String} A 128-character hexadecimal string representing the SHA-512 hash.
     * @throws {RuntimeException} Thrown if the SHA-512 algorithm is unavailable in the current JVM.
     * @public
     * @static
     */
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

    /**
     * @method verifyHash
     * @description Validates a plain text string against a pre-existing hash by comparing their SHA-512 representations.
     * @param {String} plainText - The unencrypted string provided by the user.
     * @param {String} hash - The stored hexadecimal hash to compare against.
     * @returns {boolean} True if the generated hash of the plain text matches the stored hash (case-insensitive).
     * @public
     * @static
     */
    public static boolean verifyHash(String plainText, String hash) {
        String hashedInput = hashString(plainText);
        return hashedInput.equalsIgnoreCase(hash);
    }
}