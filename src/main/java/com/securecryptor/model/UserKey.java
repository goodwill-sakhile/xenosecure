package com.securecryptor.model;

import java.util.Base64;

/**
 * Represents a user's encryption key derived from a password.
 */
public class UserKey {
    private final byte[] keyBytes;
    private final String username;

    public UserKey(String username, byte[] keyBytes) {
        this.username = username;
        this.keyBytes = keyBytes;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getKeyBytes() {
        return keyBytes;
    }

    public String getEncodedKey() {
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    @Override
    public String toString() {
        return "UserKey{username='" + username + "', key=" + getEncodedKey() + "}";
    }
}
