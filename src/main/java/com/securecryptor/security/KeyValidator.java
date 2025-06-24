package com.securecryptor.security;

import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Validates public key format and strength.
 */
public class KeyValidator {

    public static boolean isValidRSAKey(PublicKey key) {
        if (!(key instanceof RSAPublicKey)) return false;
        RSAPublicKey rsaKey = (RSAPublicKey) key;
        return rsaKey.getModulus().bitLength() >= 2048;
    }
}
