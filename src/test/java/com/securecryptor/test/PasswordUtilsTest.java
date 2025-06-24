package com.securecryptor.test;

import com.securecryptor.util.PasswordUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordUtilsTest {

    @Test
    public void testSaltAndHash() {
        char[] password = "securepass".toCharArray();
        byte[] salt = PasswordUtils.generateSalt();
        byte[] hashed1 = PasswordUtils.hashPassword(password, salt);
        byte[] hashed2 = PasswordUtils.hashPassword(password, salt);

        assertArrayEquals(hashed1, hashed2, "Hashes with same salt and password should match.");
    }

    @Test
    public void testBase64RoundTrip() {
        byte[] salt = PasswordUtils.generateSalt();
        String base64 = PasswordUtils.saltToBase64(salt);
        byte[] decoded = PasswordUtils.base64ToSalt(base64);

        assertArrayEquals(salt, decoded, "Base64 encoding/decoding should retain original bytes.");
    }
}
