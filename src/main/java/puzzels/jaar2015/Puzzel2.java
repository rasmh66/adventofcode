package puzzels.jaar2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Puzzel2 {
    private static final int C_JAAR = 2015;
    private static final String C_PUZZELNR = "2";

    public static void start() throws IOException {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input/" + C_JAAR + "/input" + C_PUZZELNR + ".txt";


        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            int total = 0;
            String line;
            while ((line = br.readLine()) != null) {
                String[] dim = line.split("x");
                int l = Integer.parseInt(dim[0]);
                int w = Integer.parseInt(dim[1]);
                int h = Integer.parseInt(dim[2]);
//                System.out.printf("%s %s %s\n", dim[0],dim[1],dim[2]);
//                int kant1 = l * w;
//                int kant2 = l * h;
//                int kant3 = w * h;
//                total += 2 * kant1 + 2 * kant2 + 2 * kant3 + Math.min(Math.min(kant1, kant2), kant3);

                int kl1, kl2;

                if (l <= w) {
                    kl1 = l;
                    kl2 = Math.min(w, h);
                } else if (w <= h) {
                    kl1 = w;
                    kl2 = Math.min(l, h);
                } else {
                    kl1 = h;
                    kl2 = Math.min(w, l);
                }
                total += (kl1 + kl1 + kl2 + kl2 + (l * w * h));
            }
            System.out.printf("Tot: %d", total);
        }
    }
}
