package spring.security.demo.utils;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

// Public class with staticc method which generates secret key for JWT
public class KeyGeneratorUtility {
    public static KeyPair generateRsaKey(){

        // Generate RSA key pair
        KeyPair keyPair; 

        try {
            // Instance of KeyPairGenerator, initialized with RSA algorithm
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);

            // Generate key pair
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to generate RSA key pair", e);
        }
        
        return keyPair;
    }
}
