package puzzels.jaar2015;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Puzzel1 {
    private static final int C_JAAR = 2015;
    private static final String C_PUZZELNR = "1";

    public static void start() throws IOException {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input/" + C_JAAR + "/input" + C_PUZZELNR + ".txt";

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));

        int floor = 0;
        int position = 1;
        int c;
        while ((c = reader.read()) != -1) {
            char k = (char) c;

            if (c == '(') {
                floor++;
            } else if (c == ')') {
                floor--;
            }
            if (floor==-1){
                break;
            }else position++;
        }

//        System.out.println("Verdieping:" + floor);
        System.out.println("Pos:" + position);
    }
}
