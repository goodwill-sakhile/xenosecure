package com.securecryptor.fileops;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Random;

/**
 * Securely overwrites file content before deletion.
 */
public class FileShredder {

    public static boolean shred(String filePath, ShredLevel level) {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) return false;

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            long length = file.length();
            byte[] buffer = new byte[4096];
            Random random = new Random();

            for (int pass = 0; pass < level.getPasses(); pass++) {
                raf.seek(0);
                for (long i = 0; i < length; i += buffer.length) {
                    random.nextBytes(buffer);
                    raf.write(buffer, 0, (int) Math.min(buffer.length, length - i));
                }
            }

            raf.close();
            return file.delete();
        } catch (Exception e) {
            return false;
        }
    }
}
