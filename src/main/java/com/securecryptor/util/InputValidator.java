package com.securecryptor.util;

import java.io.File;

/**
 * Utility to validate user input.
 */
public class InputValidator {

    public static boolean fileExists(String path) {
        return new File(path).exists();
    }

    public static boolean isValidPath(String path) {
        try {
            File file = new File(path);
            return file.getParentFile() != null;
        } catch (Exception e) {
            return false;
        }
    }
}
