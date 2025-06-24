package com.securecryptor;

import com.securecryptor.cli.CommandLineParser;
import com.securecryptor.cli.HybridCommandHandler;
import com.securecryptor.model.KeyPairInfo;
import com.securecryptor.service.KeyManagementService;
import com.securecryptor.util.CliColors;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        CliColors.printBanner("Welcome to SecureCryptor");

        Map<String, String> options = CommandLineParser.parse(args);

        try {
            String username = System.getProperty("user.name");
            KeyManagementService keyService = new KeyManagementService();
            KeyPairInfo keys = keyService.loadOrGenerateKeys(username);

            if (options.containsKey("hybrid")) {
                new HybridCommandHandler(keys).run();
            } else if (options.containsKey("encrypt")) {
                System.out.println("Standard encryption feature coming soon.");
            } else {
                CliColors.printInfo("Use flags like --encrypt or --hybrid");
            }

        } catch (Exception e) {
            CliColors.printError("Fatal error: " + e.getMessage());
        }
    }
}
