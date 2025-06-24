package com.securecryptor.fileops;

/**
 * Represents file shredding intensity.
 */
public enum ShredLevel {
    LOW(1),
    MEDIUM(3),
    HIGH(7);

    private final int passes;

    ShredLevel(int passes) {
        this.passes = passes;
    }

    public int getPasses() {
        return passes;
    }
}
