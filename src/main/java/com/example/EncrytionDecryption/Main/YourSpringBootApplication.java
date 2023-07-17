package com.example.EncrytionDecryption.Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;

@SpringBootApplication
public class YourSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(YourSpringBootApplication.class, args);

        // Encrypt a file
        String inputFile = "";
        String encryptedFile = "";
        String secretKey = "";
        try {
            FileEncryptionUtils.encryptFile(new File(inputFile), new File(encryptedFile), secretKey);
            System.out.println("File encrypted successfully.");
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }

        // Decrypt a file
        String decryptedFile = "";
        try {
            FileEncryptionUtils.decryptFile(new File(encryptedFile), new File(decryptedFile), secretKey);
            System.out.println("File decrypted successfully.");
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}
