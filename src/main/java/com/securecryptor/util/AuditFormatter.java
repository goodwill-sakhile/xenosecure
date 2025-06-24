package com.securecryptor.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Formats audit log entries.
 */
public class AuditFormatter {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String format(String action, String user, String filePath) {
        String timestamp = sdf.format(new Date());
        return String.format("[%s] Action='%s', User='%s', File='%s'", timestamp, action, user, filePath);
    }
}
