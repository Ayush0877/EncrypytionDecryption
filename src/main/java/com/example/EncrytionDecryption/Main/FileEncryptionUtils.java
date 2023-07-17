package com.example.EncrytionDecryption.Main;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileEncryptionUtils {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    public static void encryptFile(File inputFile, File outputFile, String secretKey)
            throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        doCrypto(Cipher.ENCRYPT_MODE, inputFile, outputFile, secretKey);
    }

    public static void decryptFile(File inputFile, File outputFile, String secretKey)
            throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        doCrypto(Cipher.DECRYPT_MODE, inputFile, outputFile, secretKey);
    }

    private static void doCrypto(int cipherMode, File inputFile, File outputFile, String secretKey)
            throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(cipherMode, secretKeySpec);

        try (FileInputStream inputStream = new FileInputStream(inputFile);
             FileOutputStream outputStream = new FileOutputStream(outputFile);
             CipherOutputStream cipherOutputStream = new CipherOutputStream(outputStream, cipher)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                cipherOutputStream.write(buffer, 0, bytesRead);
            }
        }
    }
}
