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

        new CommandHandler(userKey).process();

    } catch (Exception e) {
        e.printStackTrace();
        System.err.println("Operation failed: " + e.getMessage());
    }
}

}
