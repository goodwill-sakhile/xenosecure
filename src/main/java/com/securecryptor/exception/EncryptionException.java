package com.securecryptor.exception;

/**
 * Thrown when an encryption operation fails.
 */
public class EncryptionException extends Exception {
    public EncryptionException(String message) {
        super(message);
    }

    public EncryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
