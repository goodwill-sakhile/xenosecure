package com.securecryptor.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Stores metadata about encrypted/decrypted files.
 */
public class FileMetadata {
    private final String filename;
    private final long sizeBytes;
    private final long timestamp;

    public FileMetadata(String filename, long sizeBytes) {
        this.filename = filename;
        this.sizeBytes = sizeBytes;
        this.timestamp = System.currentTimeMillis();
    }

    public String getFilename() {
        return filename;
    }

    public long getSizeBytes() {
        return sizeBytes;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getFormattedTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
    }

    @Override
    public String toString() {
        return "FileMetadata{" +
                "filename='" + filename + '\'' +
                ", sizeBytes=" + sizeBytes +
                ", timestamp=" + getFormattedTimestamp() +
                '}';
    }
}
