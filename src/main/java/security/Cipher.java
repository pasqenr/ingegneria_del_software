package security;

/**
 * Abstract cipher that provide the ability to encrypt a message and verify that an encrypted message and a plain
 * message are the same.
 */
public abstract class Cipher {
    /**
     * Encrypt a plain text.
     *
     * @param plainText A plain text.
     * @return The encrypted text.
     */
    public abstract String encrypt(String plainText);

    /**
     * Check if the plain text and the encrypted text are the same.
     *
     * @param plainText Some plain text.
     * @param encryptedText Some encrypted text.
     * @return <code>true</code> if the texts are the same, <code>false</code> otherwise.
     */
    public abstract boolean verify(String plainText, String encryptedText);
}
