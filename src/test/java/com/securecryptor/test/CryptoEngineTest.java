package com.securecryptor.test;

import com.securecryptor.crypto.CryptoEngine;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CryptoEngineTest {

    @Test
    public void testEncryptDecrypt() throws Exception {
        byte[] data = "Top Secret File".getBytes();
        byte[] key = CryptoEngine.generateIV();
        byte[] iv = CryptoEngine.generateIV();

        byte[] encrypted = CryptoEngine.encrypt(data, key, iv);
        byte[] decrypted = CryptoEngine.decrypt(encrypted, key, iv);

        assertTrue(Arrays.equals(data, decrypted), "Decrypted data should match original");
    }
}
