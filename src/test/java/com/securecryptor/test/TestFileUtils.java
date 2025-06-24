package com.securecryptor.test;

import java.io.File;
import java.io.FileWriter;

/**
 * Utility to create temporary test files.
 */
public class TestFileUtils {

    public static File createTempFile(String content) throws Exception {
        File file = File.createTempFile("test_", ".tmp");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        }
        return file;
    }
}
