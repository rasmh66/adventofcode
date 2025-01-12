package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class Puzzel4b extends abstractPuzzel {

    public Puzzel4b(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        Map<Integer, Integer> copies = new TreeMap<Integer, Integer>();
        int totpoints = 0;

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] deelId = line.split(":");
                int kaartnummer = Integer.parseInt(deelId[0].substring(4).trim());

                String[] nummers = deelId[1].split("\\|");

                int[] winnendenrs = readIntegers(nummers[0].trim());
                int[] mijnnrs = readIntegers(nummers[1].trim());

                System.out.println("kaartnummer = " + kaartnummer);

                addKaart(copies, kaartnummer);

                int aantal = 0;
                for (int m :
                        mijnnrs) {
                    for (int w :
                            winnendenrs) {
                        if (m == w) {
                            aantal += 1;
                        }
                    }
                }
                System.out.println("aantal = " + aantal);
                for (int j = 1; j < aantal + 1; j++) {
                    for (int i = 1; i < copies.get(kaartnummer) + 1; i++) {
                        addKaart(copies, kaartnummer + j);
                    }
                }
            }
            System.out.println("copies = " + copies);
            for (Integer val :
                    copies.values()) {
                totpoints += val;
            }
            System.out.println("totpoints = " + totpoints);
        }
    }

    private void addKaart(Map<Integer, Integer> copies, int nummer) {
//        System.out.println("nummer = " + nummer);
        if (copies.containsKey(nummer)) {
            int vorige = copies.get(nummer);
            copies.replace(nummer, vorige + 1);
        } else {
            copies.put(nummer, Integer.valueOf(1));
        }
//        System.out.println("copies = " + copies);
    }

}

