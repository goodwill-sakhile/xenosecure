package com.securecryptor.crypto;

import com.securecryptor.util.Base64Utils;

import java.io.File;
import java.nio.file.Files;
import java.security.PrivateKey;

/**
 * Decrypts a hybrid-encrypted file (RSA-encrypted AES key + AES file body).
 */
public class HybridDecryptor {

    public static void decryptFile(String inputPath, String outputPath, PrivateKey privateKey) throws Exception {
        byte[] fullData = Files.readAllBytes(new File(inputPath).toPath());
        int newlineIndex = findNewline(fullData);

        byte[] encryptedKeyBytes = new byte[newlineIndex];
        System.arraycopy(fullData, 0, encryptedKeyBytes, 0, newlineIndex);
        String encryptedKey = new String(encryptedKeyBytes);
        String decryptedKeyBase64 = RSAEngine.decrypt(encryptedKey, privateKey);
        byte[] aesKey = Base64Utils.decode(decryptedKeyBase64);

        byte[] encryptedData = new byte[fullData.length - newlineIndex - 1];
        System.arraycopy(fullData, newlineIndex + 1, encryptedData, 0, encryptedData.length);

        byte[] decryptedData = CryptoEngine.decrypt(encryptedData, aesKey, aesKey);
        Files.write(new File(outputPath).toPath(), decryptedData);
    }

    private static int findNewline(byte[] data) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == '\n') return i;
        }
        throw new RuntimeException("No newline separator found in hybrid-encrypted file.");
    }
}
