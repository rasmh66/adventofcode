package puzzels.jaar2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Puzzel5 {
    private static final int C_JAAR = 2015;
    private static final String C_PUZZELNR = "5";

    public static void start() throws IOException {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input/" + C_JAAR + "/input" + C_PUZZELNR + ".txt";


        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            int total = 0;
            String line;
            while ((line = br.readLine()) != null) {

                Boolean nice = false;
                if (!line.contains("ab") &&
                        !line.contains("cd") &&
                        !line.contains("pq") &&
                        !line.contains("xy")) {

                    int teller = 0;
                    for (char c :
                            line.toCharArray()) {
                        if ("aeiou".contains(String.valueOf(c))) {
                            teller++;
                        }
                    }
                    if (teller >= 3) {
                        nice = checkDubbel(line);
                    } else nice = false;
                }
                //
                if (nice) total++;

            }
            System.out.println("Tot: " + total);
        }
    }

    private static Boolean checkDubbel(String line) {
        char[] chars = line.toCharArray();
        for (int i = 1; i < chars.length; i++) {
                if (chars[i] == chars[i-1]) return true;
        }
        return false;
    }
}
