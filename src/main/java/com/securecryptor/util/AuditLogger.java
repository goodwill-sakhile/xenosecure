package com.securecryptor.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logs critical encryption activity to audit.log
 */
public class AuditLogger {
    private static final String AUDIT_FILE = "audit.log";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void log(String action, String user, String file) {
        try (PrintWriter out = new PrintWriter(new FileWriter(AUDIT_FILE, true))) {
            String timestamp = sdf.format(new Date());
            out.printf("[%s] Action: %s, User: %s, File: %s%n", timestamp, action, user, file);
        } catch (Exception e) {
            Logger.error("Audit log failed: " + e.getMessage());
        }
    }
}
