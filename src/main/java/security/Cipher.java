package security;

public abstract class Cipher {
    public abstract String encrypt(String plainText);

    public abstract boolean verify(String plainText, String encryptedText);
}
