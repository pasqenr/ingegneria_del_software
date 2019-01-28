import org.junit.jupiter.api.Test;
import security.HashCipher;

import static org.junit.jupiter.api.Assertions.*;

class HashCipherTest {
    private String[] testHashes = {
            "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", // password
            "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", // test
            "5d5b09f6dcb2d53a5fffc60c4ac0d55fabdf556069d6631545f42aa6e3500f2e"  // sha256
    };

    @Test
    void testEncrypt() {
        HashCipher hashChiper = new HashCipher();

        assertEquals(testHashes[0], hashChiper.encrypt("password"));
        assertEquals(testHashes[1], hashChiper.encrypt("test"));
        assertEquals(testHashes[2], hashChiper.encrypt("sha256"));
    }

    @Test
    void testVerify() {
        HashCipher hashChiper = new HashCipher();

        assertTrue(hashChiper.verify("password", hashChiper.encrypt("password")));
        assertTrue(hashChiper.verify("test", hashChiper.encrypt("test")));
        assertTrue(hashChiper.verify("sha256", hashChiper.encrypt("sha256")));

        assertFalse(hashChiper.verify("password", hashChiper.encrypt("chiper")));
    }
}
