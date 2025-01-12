package puzzels.jaar2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Puzzel10 {
    private static final int C_JAAR = 2015;
    private static final String C_PUZZELNR = "1";

    public static void start() throws IOException {
//        String dir = System.getProperty("user.dir");
//        String fileName = dir + "/input/" + C_JAAR + "/input" + C_PUZZELNR + ".txt";

        String line = "1113222113";

        StringBuffer org = new StringBuffer(line);

        for (int i = 0; i < 50; i++) {
            StringBuffer buf = new StringBuffer();
            int tel = 1;
            char c = org.charAt(0);
            for (int j = 1; j < org.length(); j++) {
                if (c == org.charAt(j)) {
                    tel++;
                } else {
                    buf.append(tel);
                    buf.append(c);
                    c = org.charAt(j);
                    tel=1;
                }
            }
            buf.append(tel);
            buf.append(c);
//            System.out.println("Buf:"+buf.toString());
            org = buf;
        }
//        System.out.println("org:"+org.toString());
        System.out.println("len:"+org.length());
    }
}
