package demo;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Encrypt passwords
 */
@Component
public class PasswordEncoder implements org.springframework.security.crypto.password.PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return this.encode(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        String encryptedPassword = this.encode(charSequence);
        return s.equals(encryptedPassword);
    }

    /**
     * Encrypt passwords.
     * @param uncryptedPassword Uncrypted password
     * @return Crypted password
     */
    public String encode(String uncryptedPassword) {
        return encodeSHA1(encodeMD5(uncryptedPassword));
    }

    /**
     * Encrypt password in SHA-1
     * @param uncryptedPassword Uncrypted password
     * @return Crypted password
     */
    public String encodeSHA1(String uncryptedPassword) {
        MessageDigest messageDigest=null;
        try {
            messageDigest = MessageDigest.getInstance("SHA");
            return byteArrayToHexString(messageDigest.digest(uncryptedPassword.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encrypt password in MD5
     * @param uncryptedPassword Uncrypted password
     * @return Crypted password
     */
    public String encodeMD5(String uncryptedPassword) {
        MessageDigest messageDigest=null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            return byteArrayToHexString(messageDigest.digest(uncryptedPassword.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Convert encoding result to String.
     * @param b bytes
     * @return String
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer result = new StringBuffer();
        for (int i=0; i < b.length; i++) {
            result.append(
                    Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 ));
        }
        return result.toString();
    }

}
