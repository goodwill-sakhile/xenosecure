package com.securecryptor.crypto;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Handles encryption of files using AES.
 */
public class FileEncryptor {

    public static void encryptFile(String inputPath, String outputPath, byte[] key) throws Exception {
        byte[] iv = CryptoEngine.generateIV();
        byte[] inputData = Files.readAllBytes(Paths.get(inputPath));
        byte[] encryptedData = CryptoEngine.encrypt(inputData, key, iv);

        Files.write(Paths.get(outputPath), iv, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        Files.write(Paths.get(outputPath), encryptedData, StandardOpenOption.APPEND);
    }
}
