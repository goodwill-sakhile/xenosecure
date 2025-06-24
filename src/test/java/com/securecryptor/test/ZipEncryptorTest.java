package com.securecryptor.test;

import com.securecryptor.fileops.ZipEncryptor;
import com.securecryptor.crypto.FileDecryptor;
import com.securecryptor.crypto.FileEncryptor;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZipEncryptorTest {

    @Test
    public void testZipEncryption() throws Exception {
        File input = TestFileUtils.createTempFile("Zip file content");
        File encrypted = File.createTempFile("zip_", ".enc");
        File decrypted = File.createTempFile("zip_", ".dec");

        byte[] key = "1234567890abcdef".getBytes();  // 16 bytes AES key
        ZipEncryptor.zipAndEncrypt(input.getAbsolutePath(), encrypted.getAbsolutePath(), key);
        FileDecryptor.decryptFile(encrypted.getAbsolutePath(), decrypted.getAbsolutePath(), key);

        assertTrue(decrypted.exists() && decrypted.length() > 0);
    }
}
