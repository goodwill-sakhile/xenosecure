package com.securecryptor.exception;

/**
 * Thrown when a decryption operation fails.
 */
public class DecryptionException extends Exception {
    public DecryptionException(String message) {
        super(message);
    }

    public DecryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
