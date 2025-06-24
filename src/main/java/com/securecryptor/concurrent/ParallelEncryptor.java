package com.securecryptor.concurrent;

import com.securecryptor.crypto.FileEncryptor;
import com.securecryptor.util.Logger;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Encrypts multiple files in parallel using a thread pool.
 */
public class ParallelEncryptor {

    private final ExecutorService executor;

    public ParallelEncryptor(int threads) {
        this.executor = Executors.newFixedThreadPool(threads);
    }

    public void encryptFilesInFolder(String folderPath, byte[] key, String outputDir) {
        File dir = new File(folderPath);
        if (!dir.exists() || !dir.isDirectory()) {
            Logger.error("Invalid input directory: " + folderPath);
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isFile()) {
                executor.submit(() -> {
                    try {
                        String outputPath = outputDir + "/" + file.getName() + ".enc";
                        FileEncryptor.encryptFile(file.getAbsolutePath(), outputPath, key);
                        Logger.info("Encrypted: " + file.getName());
                    } catch (Exception e) {
                        Logger.error("Failed to encrypt " + file.getName() + ": " + e.getMessage());
                    }
                });
            }
        }

        executor.shutdown();
    }
}
