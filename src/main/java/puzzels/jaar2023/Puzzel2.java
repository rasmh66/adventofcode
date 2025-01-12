package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.Map;

public class Puzzel2 extends abstractPuzzel {

    public static final String BLUE = "blue";
    public static final String RED = "red";
    public static final String GREEN = "green";

    public Puzzel2(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            int oplossing = 0;
            while ((line = br.readLine()) != null) {

                String deelId[] = line.split(":");

                int s = Integer.parseInt(deelId[0].substring(4, deelId[0].length()).trim());
                Map<String, Integer> stringMap = new Hashtable<>();
                stringMap.put(BLUE, 1);
                stringMap.put(RED, 1);
                stringMap.put(GREEN, 1);

                String[] deelPart = deelId[1].trim().split(";");
                for (int i = 0; i < deelPart.length; i++) {
                    String[] deelMappen = deelPart[i].trim().split(",");
                    for (int j = 0; j < deelMappen.length; j++) {
                        String[] deelMap = deelMappen[j].trim().split(" ");
                        int newInt = 0;
                        Integer huidInt = 0;
                        switch (deelMap[1].trim()) {
                            case BLUE:
                                newInt = Integer.parseInt(deelMap[0].trim());
                                huidInt = stringMap.get(BLUE);
                                if (huidInt != null) {
                                    stringMap.put(BLUE, Integer.max(huidInt, newInt));
                                } else {
                                    stringMap.put(BLUE, newInt);
                                }
                                break;
                            case RED:
                                newInt = Integer.parseInt(deelMap[0].trim());
                                huidInt = stringMap.get(RED);
                                if (huidInt != null) {
                                    stringMap.put(RED, Integer.max(huidInt, newInt));
                                } else {
                                    stringMap.put(RED, newInt);
                                }
                                break;
                            case GREEN:
                                newInt = Integer.parseInt(deelMap[0].trim());
                                huidInt = stringMap.get(GREEN);
                                if (huidInt != null) {
                                    stringMap.put(GREEN, Integer.max(huidInt, newInt));
                                } else {
                                    stringMap.put(GREEN, newInt);
                                }
                                break;
                        }
                    }
                }
//                if (       (stringMap.get(RED) <= 12)
//                        && (stringMap.get(GREEN) <= 13)
//                        && (stringMap.get(BLUE) <= 14)) {
//                    oplossing += s;
//                }
                int power = stringMap.get(RED) * stringMap.get(GREEN) * stringMap.get(BLUE);
                oplossing += power;
            }
            System.out.println("oplossing = " + oplossing);
        }
    }
}

