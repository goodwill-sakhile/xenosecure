package com.securecryptor.fileops;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * Overwrites file contents before deletion to prevent recovery.
 */
public class FileCleaner {

    public static boolean shredFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) return false;

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            long length = file.length();
            byte[] randomData = new byte[4096];

            for (long pos = 0; pos < length; pos += randomData.length) {
                Arrays.fill(randomData, (byte) 0);
                raf.write(randomData, 0, (int) Math.min(randomData.length, length - pos));
            }

            raf.close();
            return file.delete();
        } catch (Exception e) {
            return false;
        }
    }
}
