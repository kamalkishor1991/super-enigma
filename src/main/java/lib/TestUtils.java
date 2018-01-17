package lib;

import java.util.Random;

public class TestUtils {
    public static String genRandomText(int len) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String chars = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(random.nextInt(26)));
        }
        return sb.toString();
    }
}
