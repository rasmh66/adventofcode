package puzzels.jaar2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HexFormat;
import java.util.regex.Pattern;

public class Puzzel8 {
    private final int C_JAAR = 2015;
    private final String C_PUZZELNR = "8";

    public void start() throws IOException {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input/" + C_JAAR + "/input" + C_PUZZELNR + ".txt";


        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            int strLen = 0;
            int strLenEscaped = 0;
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                strLen += line.length();

                String str = deescapeJava(line);
                strLenEscaped += str.length();
//                System.out.println("Esc:" + strLenEscaped);

                System.out.println(str);
            }

            System.out.println("Len:" + strLen);
            System.out.println("Esc:" + strLenEscaped);
            System.out.println("Minus:" + (strLenEscaped - strLen));
        }
    }

    private String deescapeJava(String line) {
        StringBuffer buf = new StringBuffer();
        char[] chars = line.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == (char)34) {  // "
                buf.append(chars[i]);
                buf.append((char)92);
                buf.append((char)34);
            } else if (chars[i] == (char)92) {
                if (chars[i+1] == (char)34) {  // \"
                    i++;
                    buf.append((char) 92);
                    buf.append((char) 92);
                    buf.append((char) 92);
                    buf.append((char) 34);
                } else {
                    buf.append((char) 92);
                    buf.append((char) 92);
                }
            } else {
                buf.append(chars[i]);
            }
        }

        return buf.toString();
    }

    private String unescapeJava(String line) {
        StringBuffer buf = new StringBuffer();
        char[] chars = line.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == (char)92) {
                i++;
                if (chars[i] == (char)92) {         // /
//                    i++;
                    buf.append((char)92);
                } else if (chars[i] == (char)34) {  // "
//                    i++;
                    buf.append((char)34);
                } else if (chars[i] == (char)120) { // x
                    i++;
                    String char1 = line.substring(i, i + 2);
                    i++;
                    System.out.println("c:"+char1);
                    Character c = Character.valueOf((char) HexFormat.fromHexDigits(char1));
                    buf.append(c);
                } else {
                    System.out.println("Enkele slah");
                    buf.append((char)92);
                }
            } else {
                buf.append(chars[i]);
            }
        }
        return buf.toString();
    }
}
