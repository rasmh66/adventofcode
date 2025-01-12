package puzzels.jaar2024;

import puzzels.abstractPuzzel;
import puzzels.common.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Puzzel5 extends abstractPuzzel {


    public Puzzel5(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        long som = 0;
        Map<Integer,List<Integer>> rules = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            line = br.readLine();
            while (!line.isEmpty()) {
                String[] str = line.split("\\|");
                rules.computeIfAbsent(Integer.parseInt(str[0]), k -> new ArrayList<>()).add(Integer.parseInt(str[1]));
                line = br.readLine();
            }
            while ((line = br.readLine()) != null) {
                System.out.print("l:"+line);
                int[] update = readIntegers(line);

                boolean valid = bepaalOrder(rules, update);
                System.out.println(" :"+valid);
                if (valid) {
                    int middel = bepaalMiddelste(update);
                    System.out.println("middel = " + middel);
                    som += middel;
                }
            }
            System.out.println("map:"+rules.toString());
            System.out.println("Aantal worden gevonden: " + som);
        }
    }

    private int bepaalMiddelste(int[] update) {
        int len = update.length;
        int middel = (int) Math.ceil(len/2);
        return update[middel];
    }

    private boolean bepaalOrder(Map<Integer, List<Integer>> rules, int[] update) {

        for (Map.Entry<Integer, List<Integer>> entry : rules.entrySet()) {
            Integer key = entry.getKey();
            if (Arrays.stream(update).anyMatch(i -> i == key)) {
                List<Integer> values = entry.getValue();
                for (Integer value :
                        values) {
                    if (!checkOrder(update, key, value)) return false;
                }
//                System.out.println("Sleutel: " + key + ", Waarden: " + values);
            }
        }
        return true;
    }

    public static boolean checkOrder(int[] array, int first, int second) {
        boolean foundFirst = false;

        for (int num : array) {
            if (num == first) {
                foundFirst = true;
            }
            if (num == second) {
                return foundFirst;
            }
        }
        return true;
    }
}

