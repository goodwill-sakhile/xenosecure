package com.securecryptor.cli;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses CLI arguments into key-value pairs.
 */
public class CommandLineParser {

    public static Map<String, String> parse(String[] args) {
        Map<String, String> options = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("--")) {
                String key = args[i].substring(2);
                String value = (i + 1 < args.length && !args[i + 1].startsWith("--")) ? args[++i] : "true";
                options.put(key, value);
            }
        }

        return options;
    }
}
