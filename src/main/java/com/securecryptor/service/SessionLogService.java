package com.securecryptor.service;

import com.securecryptor.util.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * Tracks and logs active users during runtime.
 */
public class SessionLogService {

    private static final Set<String> activeUsers = new HashSet<>();

    public static void login(String username) {
        if (!activeUsers.contains(username)) {
            activeUsers.add(username);
            Logger.info("User logged in: " + username);
        }
    }

    public static void logout(String username) {
        if (activeUsers.contains(username)) {
            activeUsers.remove(username);
            Logger.info("User logged out: " + username);
        }
    }

    public static boolean isLoggedIn(String username) {
        return activeUsers.contains(username);
    }
}
