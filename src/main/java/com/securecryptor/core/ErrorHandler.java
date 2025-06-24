package com.securecryptor.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Maps system error codes to user-friendly messages.
 */
public class ErrorHandler {

    private static final Map<String, String> errors = new HashMap<>();

    static {
        errors.put("KEY_GEN_FAIL", "Unable to generate cryptographic key.");
        errors.put("ENCRYPTION_FAIL", "Encryption failed. File might be corrupted or inaccessible.");
        errors.put("DECRYPTION_FAIL", "Decryption failed. Key mismatch or invalid file.");
        errors.put("CONFIG_MISSING", "Configuration not found. Falling back to default settings.");
    }

    public static void handle(String errorCode) {
        System.err.println("[Error] " + errors.getOrDefault(errorCode, "Unknown error occurred."));
    }
}
