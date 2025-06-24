package com.securecryptor.util;

import java.util.Base64;

/**
 * Utility class for Base64 encoding/decoding.
 */
public class Base64Utils {

    public static String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] decode(String base64) {
        return Base64.getDecoder().decode(base64);
    }
}
