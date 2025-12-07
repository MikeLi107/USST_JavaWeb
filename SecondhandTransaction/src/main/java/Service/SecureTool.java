package Service;

import java.security.MessageDigest;
import java.util.Base64;

public class SecureTool {
    public static String md5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            return password;
        }
    }
}