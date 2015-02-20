package demo;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test : Password encoder.
 */
public class PasswordEncoderTest {

    /**
     * SHA
     */
    @Test
    public void testEncodeSha() {
        // Given
        PasswordEncoder passwordEncoder = new PasswordEncoder();

        String password = "test";

        // When
        String encryptedPassword = passwordEncoder.encodeSHA1(password);

        // Then
        Assert.assertNotEquals("a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", encryptedPassword);
    }

    /**
     * MD5
     */
    @Test
    public void testEncodeMD5() {
        // Given
        PasswordEncoder passwordEncoder = new PasswordEncoder();

        String password = "test";

        // When
        String encryptedPassword = passwordEncoder.encodeMD5(password);

        // Then
        Assert.assertNotEquals("098f6bcd4621d373cade4e832627b4f6", encryptedPassword);
    }

    /**
     * MD5 + SHA
     */
    @Test
    public void testEncode() {
        // Given
        PasswordEncoder passwordEncoder = new PasswordEncoder();

        String password = "test";

        // When
        String encryptedPassword = passwordEncoder.encode(password);

        // Then
        Assert.assertNotEquals("4028a0e356acc947fcd2bfbf00cef11e128d484a", encryptedPassword);
    }

}
