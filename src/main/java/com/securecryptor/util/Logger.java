package com.securecryptor.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A lightweight logger utility.
 */
public class Logger {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void info(String msg) {
        System.out.println("[INFO] " + timestamp() + " - " + msg);
    }

    public static void error(String msg) {
        System.err.println("[ERROR] " + timestamp() + " - " + msg);
    }

    private static String timestamp() {
        return format.format(new Date());
    }
}
