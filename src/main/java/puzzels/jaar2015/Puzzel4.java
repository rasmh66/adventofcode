package puzzels.jaar2015;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Puzzel4 {

    public static void start() throws IOException {

        String input = "iwrupvqb";
        try {
            int teller = 1;
            boolean gevonden = false;
            do {

                String volStr = input + teller;
//                System.out.println("v = " + volStr);

                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update(StandardCharsets.UTF_8.encode(volStr));
                final byte[] resultByte = md5.digest();
                final String output = bytesToHex(resultByte);

//                System.out.printf("o = %s\n", output);
//                String strBI = output;
//                System.out.printf("%s ", strBI);

                if (output.startsWith("000000")) {
//                if (teller > 100) {
                    gevonden = true;
                } else {
                    teller++;
                }
                if (teller % 100000 == 0) {
                    System.out.printf("%d\n", teller);
                }

            } while (!gevonden);

            System.out.println("teller = " + teller);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
    public static String bytesToHex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }
}
