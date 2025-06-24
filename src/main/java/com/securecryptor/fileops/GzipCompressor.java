package com.securecryptor.fileops;

import java.io.*;
import java.util.zip.GZIPOutputStream;

/**
 * Compresses files into GZIP format before encryption.
 */
public class GzipCompressor {

    public static void compressFile(String inputPath, String outputPath) throws IOException {
        try (
            FileInputStream fis = new FileInputStream(inputPath);
            FileOutputStream fos = new FileOutputStream(outputPath);
            GZIPOutputStream gzos = new GZIPOutputStream(fos)
        ) {
            byte[] buffer = new byte[4096];
            int read;
            while ((read = fis.read(buffer)) != -1) {
                gzos.write(buffer, 0, read);
            }
        }
    }
}
