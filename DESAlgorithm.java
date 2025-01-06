import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class DESAlgorithm {

    // Method to encrypt a plain text using DES
    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Method to decrypt an encrypted text using DES
    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            // Generate a DES key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecretKey secretKey = keyGenerator.generateKey();

            // Display the key
            System.out.println("Generated Key: " + Base64.getEncoder().encodeToString(secretKey.getEncoded()));

            // Input plain text from user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the text to encrypt: ");
            String plainText = scanner.nextLine();

            // Encrypt the plain text
            String encryptedText = encrypt(plainText, secretKey);
            System.out.println("Encrypted Text: " + encryptedText);

            // Decrypt the encrypted text
            String decryptedText = decrypt(encryptedText, secretKey);
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
