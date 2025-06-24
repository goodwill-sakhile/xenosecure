package com.securecryptor.crypto;

import com.securecryptor.util.Base64Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.KeyPair;
import java.security.PublicKey;

/**
 * Encrypts a file using AES, then encrypts the AES key with RSA.
 */
public class HybridEncryptor {

    public static void encryptFile(String inputPath, String outputPath, PublicKey publicKey) throws Exception {
        byte[] fileData = Files.readAllBytes(new File(inputPath).toPath());
        byte[] aesKey = CryptoEngine.generateIV();  // Use AES key as IV for simplicity here
        byte[] encryptedFile = CryptoEngine.encrypt(fileData, aesKey, aesKey);
        String encryptedKey = RSAEngine.encrypt(Base64Utils.encode(aesKey), publicKey);

        try (FileOutputStream fos = new FileOutputStream(outputPath)) {
            fos.write(encryptedKey.getBytes());
            fos.write("\n".getBytes());
            fos.write(encryptedFile);
        }
    }
}
