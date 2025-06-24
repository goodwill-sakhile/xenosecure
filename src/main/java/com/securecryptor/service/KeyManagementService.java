package com.securecryptor.service;

import com.securecryptor.model.KeyPairInfo;
import com.securecryptor.util.Base64Utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.*;

/**
 * Handles loading and saving RSA keys to disk.
 */
public class KeyManagementService {

    private static final String KEY_DIR = "keys/";

    public KeyPairInfo loadOrGenerateKeys(String username) throws Exception {
        File pubFile = new File(KEY_DIR + username + ".pub");
        File privFile = new File(KEY_DIR + username + ".priv");

        if (pubFile.exists() && privFile.exists()) {
            PublicKey pubKey = loadPublicKey(Files.readString(pubFile.toPath()));
            PrivateKey privKey = loadPrivateKey(Files.readString(privFile.toPath()));
            return new KeyPairInfo(pubKey, privKey);
        }

        KeyPair pair = KeyPairGenerator.getInstance("RSA").generateKeyPair();
        Files.createDirectories(Paths.get(KEY_DIR));
        Files.writeString(pubFile.toPath(), Base64Utils.encode(pair.getPublic().getEncoded()));
        Files.writeString(privFile.toPath(), Base64Utils.encode(pair.getPrivate().getEncoded()));

        return new KeyPairInfo(pair.getPublic(), pair.getPrivate());
    }

    private PublicKey loadPublicKey(String key) throws Exception {
        byte[] keyBytes = Base64Utils.decode(key);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePublic(spec);
    }

    private PrivateKey loadPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64Utils.decode(key);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        return KeyFactory.getInstance("RSA").generatePrivate(spec);
    }
}
