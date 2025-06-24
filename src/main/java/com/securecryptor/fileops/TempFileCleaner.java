package com.securecryptor.fileops;

import java.io.File;
import java.util.Arrays;

/**
 * Securely deletes temporary files by overwriting before removal.
 */
public class TempFileCleaner {

    public static boolean secureDelete(File file) {
        if (!file.exists() || !file.isFile()) return false;

        try (var raf = new java.io.RandomAccessFile(file, "rw")) {
            long length = file.length();
            byte[] zeros = new byte[4096];
            Arrays.fill(zeros, (byte) 0);

            for (long pos = 0; pos < length; pos += zeros.length) {
                raf.write(zeros, 0, (int) Math.min(zeros.length, length - pos));
            }
            raf.close();
            return file.delete();
        } catch (Exception e) {
            return false;
        }
    }
}
