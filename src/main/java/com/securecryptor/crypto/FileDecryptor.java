package com.securecryptor.crypto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Handles decryption of files using AES.
 */
public class FileDecryptor {

    public static void decryptFile(String inputPath, String outputPath, byte[] key) throws Exception {
        byte[] allBytes = Files.readAllBytes(Paths.get(inputPath));
        byte[] iv = Arrays.copyOfRange(allBytes, 0, 16);
        byte[] encryptedData = Arrays.copyOfRange(allBytes, 16, allBytes.length);

        byte[] decryptedData = CryptoEngine.decrypt(encryptedData, key, iv);
        Files.write(Paths.get(outputPath), decryptedData);
    }
}
