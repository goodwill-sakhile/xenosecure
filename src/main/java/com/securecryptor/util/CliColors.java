package com.securecryptor.util;

/**
 * ANSI color codes for CLI printing.
 */
public class CliColors {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String CYAN = "\u001B[36m";

    public static void printInfo(String message) {
        System.out.println(GREEN + "[INFO] " + message + RESET);
    }

    public static void printError(String message) {
        System.err.println(RED + "[ERROR] " + message + RESET);
    }

    public static void printBanner(String message) {
        System.out.println(CYAN + "=== " + message + " ===" + RESET);
    }
}
