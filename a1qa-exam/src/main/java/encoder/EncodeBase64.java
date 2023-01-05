package encoder;

import java.util.Base64;

public class EncodeBase64 {

    public static String encodeByteArray(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}
