package com.securecryptor.cli;

import com.securecryptor.crypto.FileDecryptor;
import com.securecryptor.crypto.FileEncryptor;
import com.securecryptor.model.UserKey;

import java.util.Scanner;

/**
 * Handles user input commands and routes operations.
 */
public class CommandHandler {
    private final Scanner scanner = new Scanner(System.in);
    private final UserKey userKey;

    public CommandHandler(UserKey userKey) {
        this.userKey = userKey;
    }

    public void showMenu() {
        System.out.println("\n==== SECURECRYPTOR MENU ====");
        System.out.println("1. Encrypt file");
        System.out.println("2. Decrypt file");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    public void process() {
        while (true) {
            showMenu();
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1" -> handleEncrypt();
                    case "2" -> handleDecrypt();
                    case "3" -> {
                        System.out.println("Exiting SecureCryptor...");
                        return;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private void handleEncrypt() throws Exception {
        System.out.print("Enter input file path: ");
        String input = scanner.nextLine();
        System.out.print("Enter output file path: ");
        String output = scanner.nextLine();

        FileEncryptor.encryptFile(input, output, userKey.getKeyBytes());
        System.out.println("Encryption complete.");
    }

    private void handleDecrypt() throws Exception {
        System.out.print("Enter input file path: ");
        String input = scanner.nextLine();
        System.out.print("Enter output file path: ");
        String output = scanner.nextLine();

        FileDecryptor.decryptFile(input, output, userKey.getKeyBytes());
        System.out.println("Decryption complete.");
    }
}
