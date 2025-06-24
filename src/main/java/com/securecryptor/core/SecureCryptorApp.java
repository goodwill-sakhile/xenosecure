package com.securecryptor.core;

import com.securecryptor.crypto.FileEncryptor;
import com.securecryptor.crypto.FileDecryptor;
import com.securecryptor.util.PasswordUtils;
import com.securecryptor.model.UserKey;

import java.nio.file.Files;
import java.util.Scanner;

/**
 * Main entry point for SecureCryptor application.
 */
public class SecureCryptorApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Welcome to SecureCryptor ====");

        try {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            char[] password = scanner.nextLine().toCharArray();

            byte[] salt = PasswordUtils.generateSalt();
            byte[] key = PasswordUtils.hashPassword(password, salt);
            UserKey userKey = new UserKey(username, key);

            System.out.println("1. Encrypt a file");
            System.out.println("2. Decrypt a file");
            System.out.print("Choose an option: ");
            int option = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter input file path: ");
            String inputPath = scanner.nextLine();

            System.out.print("Enter output file path: ");
            String outputPath = scanner.nextLine();

            if (option == 1) {
                FileEncryptor.encryptFile(inputPath, outputPath, userKey.getKeyBytes());
                System.out.println("File encrypted successfully.");
            } else {
                FileDecryptor.decryptFile(inputPath, outputPath, userKey.getKeyBytes());
                System.out.println("File decrypted successfully.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Operation failed: " + e.getMessage());
        }
    }
}
