package com.securecryptor.service;

import com.securecryptor.model.UserKey;
import com.securecryptor.util.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages active encryption sessions and user context.
 */
public class EncryptionSessionService {

    private final Map<String, UserKey> activeSessions = new HashMap<>();

    public void startSession(String username, UserKey userKey) {
        activeSessions.put(username, userKey);
        Logger.info("Session started for user: " + username);
    }

    public void endSession(String username) {
        activeSessions.remove(username);
        Logger.info("Session ended for user: " + username);
    }

    public UserKey getUserKey(String username) {
        return activeSessions.get(username);
    }

    public boolean isActive(String username) {
        return activeSessions.containsKey(username);
    }
}
