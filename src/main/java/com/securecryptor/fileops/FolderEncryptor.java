package com.securecryptor.fileops;

import com.securecryptor.crypto.FileEncryptor;

import java.io.File;

/**
 * Encrypts every file in a given directory.
 */
public class FolderEncryptor {

    public static void encryptFolder(String folderPath, byte[] key, String outputFolderPath) throws Exception {
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) throw new IllegalArgumentException("Not a valid folder.");

        File[] files = folder.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isFile()) {
                String outPath = outputFolderPath + "/" + file.getName() + ".enc";
                FileEncryptor.encryptFile(file.getAbsolutePath(), outPath, key);
            }
        }
    }
}
