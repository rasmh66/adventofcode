package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puzzel6 extends abstractPuzzel {

    public Puzzel6(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {
        List<Long> array = new ArrayList<Long>();
        long uitkomst = 1;

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] deelStr = line.split(":");
//                int[] rij = readIntegers(deelStr[1].trim());
                String result = deelStr[1].replaceAll("\\s", "");
                long rij = Long.parseLong(result);

                array.add(rij);
            }
            long time = array.get(0);
            long distance = array.get(1);

//            for (long i = 0; i < time.length; i++) {
            long aantal = bepaalWinkansen(time, distance);
            uitkomst *= aantal;
//            }
        }
        System.out.println("uitkomst = " + uitkomst);
    }

    private long bepaalWinkansen(long time, long dist) {
        // (time - x) * x > dist
        long aantal = 0;
        for (long x = 0; x < time; x++) {
            if ((time - x) * x > dist) aantal++;
        }
        System.out.println("aantal = " + aantal);
        return aantal;
    }
}

