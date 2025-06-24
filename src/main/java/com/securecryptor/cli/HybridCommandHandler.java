package com.securecryptor.cli;

import com.securecryptor.crypto.HybridEncryptor;
import com.securecryptor.crypto.HybridDecryptor;
import com.securecryptor.model.KeyPairInfo;

import java.util.Scanner;

/**
 * Handles hybrid encryption/decryption via RSA + AES.
 */
public class HybridCommandHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final KeyPairInfo keyPairInfo;

    public HybridCommandHandler(KeyPairInfo keyPairInfo) {
        this.keyPairInfo = keyPairInfo;
    }

    public void run() {
        System.out.println("\n=== HYBRID ENCRYPTION MODE ===");
        System.out.println("1. Encrypt with hybrid");
        System.out.println("2. Decrypt with hybrid");
        System.out.print("Choose an option: ");
        String input = scanner.nextLine();

        try {
            if ("1".equals(input)) encryptFlow();
            else if ("2".equals(input)) decryptFlow();
            else System.out.println("Invalid choice.");
        } catch (Exception e) {
            System.err.println("Hybrid mode failed: " + e.getMessage());
        }
    }

    private void encryptFlow() throws Exception {
        System.out.print("Input file path: ");
        String in = scanner.nextLine();
        System.out.print("Output file path: ");
        String out = scanner.nextLine();
        HybridEncryptor.encryptFile(in, out, keyPairInfo.getPublicKey());
        System.out.println("Hybrid encryption complete.");
    }

    private void decryptFlow() throws Exception {
        System.out.print("Input file path: ");
        String in = scanner.nextLine();
        System.out.print("Output file path: ");
        String out = scanner.nextLine();
        HybridDecryptor.decryptFile(in, out, keyPairInfo.getPrivateKey());
        System.out.println("Hybrid decryption complete.");
    }
}
