package com.securecryptor.util;

import java.io.File;

/**
 * Provides utility methods to inspect files.
 */
public class FileInfoUtil {

    public static long getFileSize(String path) {
        File file = new File(path);
        return file.exists() ? file.length() : -1;
    }

    public static boolean isFileTooLarge(String path, long maxBytes) {
        return getFileSize(path) > maxBytes;
    }

    public static boolean isFileExtension(String path, String ext) {
        return path != null && path.toLowerCase().endsWith(ext.toLowerCase());
    }
}
