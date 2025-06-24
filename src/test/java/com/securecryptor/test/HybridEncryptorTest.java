package com.securecryptor.test;

import com.securecryptor.crypto.HybridEncryptor;
import com.securecryptor.crypto.HybridDecryptor;
import com.securecryptor.crypto.RSAEngine;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.security.KeyPair;

import static org.junit.jupiter.api.Assertions.*;

public class HybridEncryptorTest {

    @Test
    public void testHybridEncryptDecrypt() throws Exception {
        File input = TestFileUtils.createTempFile("Hybrid encryption test data.");
        File output = File.createTempFile("hybrid_", ".enc");
        File decrypted = File.createTempFile("hybrid_", ".dec");

        KeyPair keyPair = RSAEngine.generateKeyPair();
        HybridEncryptor.encryptFile(input.getAbsolutePath(), output.getAbsolutePath(), keyPair.getPublic());
        HybridDecryptor.decryptFile(output.getAbsolutePath(), decrypted.getAbsolutePath(), keyPair.getPrivate());

        String original = Files.readString(input.toPath());
        String recovered = Files.readString(decrypted.toPath());

        assertEquals(original, recovered, "Hybrid encrypted and decrypted data must match.");
    }
}
