package com.securecryptor.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads configuration from config.properties.
 */
public class ConfigLoader {

    private static final String CONFIG_FILE = "config.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (Exception e) {
            Logger.error("Failed to load config: " + e.getMessage());
        }
    }

    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(properties.getProperty(key));
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
