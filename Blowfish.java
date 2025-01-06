import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class Blowfish {

    // Method to encrypt plain text using Blowfish
    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Method to decrypt encrypted text using Blowfish
    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    // Generate a Blowfish key from a given 32-bit key
    public static SecretKey generateKey(String key) throws Exception {
        byte[] keyBytes = key.getBytes();
        return new SecretKeySpec(keyBytes, 0, Math.min(keyBytes.length, 16), "Blowfish");
    }

    public static void main(String[] args) {
        try {
            // Input the key and plain text
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter a 32-bit key (up to 16 characters): ");
            String inputKey = scanner.nextLine();

            // Generate Blowfish key
            SecretKey secretKey = generateKey(inputKey);
            System.out.println("Generated Key: " + Base64.getEncoder().encodeToString(secretKey.getEncoded()));

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
