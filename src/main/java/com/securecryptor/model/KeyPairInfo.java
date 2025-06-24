package com.securecryptor.model;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Container for storing public/private RSA key pairs.
 */
public class KeyPairInfo {
    private final PublicKey publicKey;
    private final PrivateKey privateKey;

    public KeyPairInfo(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
