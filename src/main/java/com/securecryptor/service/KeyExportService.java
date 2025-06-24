package com.securecryptor.service;

import com.securecryptor.util.Base64Utils;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;

/**
 * Provides export/import functionality for RSA keys.
 */
public class KeyExportService {

    public static void exportKey(Key key, String filePath) throws Exception {
        String encoded = Base64Utils.encode(key.getEncoded());
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(encoded);
        }
    }

    public static byte[] importKey(String filePath) throws Exception {
        return Files.readAllBytes(Paths.get(filePath));
    }
}
