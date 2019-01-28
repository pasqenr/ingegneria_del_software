package security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCipher extends Cipher {
    @Override
    public String encrypt(String plainText) {
        byte[] digest = new byte[0];

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(plainText.getBytes(StandardCharsets.UTF_8));
            digest = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return toHexString(digest);
    }

    @Override
    public boolean verify(String plainText, String encryptedText) {
        String hashedString = encrypt(plainText);

        return hashedString.toLowerCase().equals(encryptedText.toLowerCase());
    }

    private String toHexString(byte[] digest) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : digest) {
            String hex = (Integer.toHexString(0xFF & b));

            // If the first byte is zero then it's automatically dropped so we need to restore it
            if(hex.length() == 1) {
                hexString.append('0');
            }

            hexString.append(hex);
        }

        return hexString.toString();
    }
}
