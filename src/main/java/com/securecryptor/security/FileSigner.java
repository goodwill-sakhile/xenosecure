package com.securecryptor.security;

import java.io.File;
import java.nio.file.Files;
import java.security.PrivateKey;
import java.security.Signature;

/**
 * Signs a file using the RSA private key.
 */
public class FileSigner {

    public static byte[] signFile(String filePath, PrivateKey privateKey) throws Exception {
        byte[] fileData = Files.readAllBytes(new File(filePath).toPath());
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(fileData);
        return signature.sign();
    }
}
