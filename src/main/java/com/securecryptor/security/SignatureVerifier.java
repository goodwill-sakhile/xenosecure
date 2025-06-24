package com.securecryptor.security;

import java.io.File;
import java.nio.file.Files;
import java.security.PublicKey;
import java.security.Signature;

/**
 * Verifies the digital signature of a file.
 */
public class SignatureVerifier {

    public static boolean verifySignature(String filePath, byte[] signatureBytes, PublicKey publicKey) throws Exception {
        byte[] fileData = Files.readAllBytes(new File(filePath).toPath());
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(fileData);
        return signature.verify(signatureBytes);
    }
}
