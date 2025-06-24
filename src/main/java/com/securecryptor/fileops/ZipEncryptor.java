package com.securecryptor.fileops;

import com.securecryptor.crypto.FileEncryptor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Zips a file and encrypts the resulting zip archive.
 */
public class ZipEncryptor {

    public static void zipAndEncrypt(String inputFilePath, String encryptedZipPath, byte[] key) throws Exception {
        File zipFile = File.createTempFile("secure_", ".zip");

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile));
             FileInputStream fis = new FileInputStream(inputFilePath)) {

            File inputFile = new File(inputFilePath);
            zos.putNextEntry(new ZipEntry(inputFile.getName()));

            byte[] buffer = new byte[4096];
            int read;
            while ((read = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, read);
            }

            zos.closeEntry();
        }

        FileEncryptor.encryptFile(zipFile.getAbsolutePath(), encryptedZipPath, key);
        Files.deleteIfExists(zipFile.toPath());
    }
}
