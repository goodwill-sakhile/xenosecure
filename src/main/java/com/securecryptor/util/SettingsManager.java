package com.securecryptor.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores runtime toggles and loaded configuration values.
 */
public class SettingsManager {

    private static final Map<String, String> settings = new HashMap<>();

    public static void set(String key, String value) {
        settings.put(key, value);
    }

    public static String get(String key, String defaultValue) {
        return settings.getOrDefault(key, defaultValue);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        if (!settings.containsKey(key)) return defaultValue;
        return Boolean.parseBoolean(settings.get(key));
    }

    public static int getInt(String key, int defaultValue) {
        try {
            return Integer.parseInt(settings.get(key));
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static void loadFromConfigFile() {
        String hybridDefault = ConfigLoader.get("enable.hybrid", "true");
        String logAudit = ConfigLoader.get("log.audit", "true");
        settings.put("enable.hybrid", hybridDefault);
        settings.put("log.audit", logAudit);
    }
}
