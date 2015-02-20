package demo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Encrypt passwords
 */
public class PasswordEncoder {

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
            messageDigest.update(uncryptedPassword.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String encryptedPassword = (new BigInteger(messageDigest.digest())).toString(16);
        return encryptedPassword;
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
            messageDigest.update(uncryptedPassword.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String encryptedPassword = (new BigInteger(messageDigest.digest())).toString(16);
        return encryptedPassword;
    }
}
