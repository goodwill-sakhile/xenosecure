package com.securecryptor.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * Encrypts/decrypts config values using AES.
 */
public class EncryptedConfigManager {
    private static final String AES = "AES";
    private static final byte[] keyBytes = "MySecretKey12345".getBytes(); // 16 bytes

    public static void saveEncrypted(String filePath, String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, AES));
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        Files.write(Paths.get(filePath), Base64.getEncoder().encode(encrypted));
    }

    public static String loadDecrypted(String filePath) throws Exception {
        byte[] encrypted = Base64.getDecoder().decode(Files.readAllBytes(Paths.get(filePath)));
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyBytes, AES));
        return new String(cipher.doFinal(encrypted));
    }
}
