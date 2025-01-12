package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Puzzel4 extends abstractPuzzel {

    public Puzzel4(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            int totpoints = 0;
            String line;
            while ((line = br.readLine()) != null) {

                String[] deelId = line.split(":");
                int kaartnummer = Integer.parseInt(deelId[0].substring(4).trim());

                String[] nummers = deelId[1].split("\\|");

                int[] winnendenrs = readIntegers(nummers[0].trim());
                int[] mijnnrs = readIntegers(nummers[1].trim());

                System.out.println("kaartnummer = " + kaartnummer);
//                System.out.println("mijnnrs = " + Arrays.toString(mijnnrs));
//                System.out.println("winnendenrs = " + Arrays.toString(winnendenrs));

                int points = 1;
                for (int m :
                        mijnnrs) {
                    for (int w :
                            winnendenrs) {
                        if (m == w) {
                            points *= 2;
                        }
                    }
                }
//                System.out.println("points = " + points);
                if (points != 1) {
                    totpoints += points/2;
                }
                System.out.println("tot = " + totpoints);
            }
            System.out.println("totpoints = " + totpoints);
        }
    }

}

