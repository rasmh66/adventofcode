package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Puzzel3b extends abstractPuzzel {

//    public static class Point{
//        int x;
//        int y;
//
//        public Point(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        @Override
//        public String toString() {
//            return "{" + x + "," +
//                     y + '}';
//        }
//    }
    public static class Resultaat {
        boolean geldig;
        int row;
        int kolom;

        public Resultaat(boolean geldig, int row, int kolom) {
            this.geldig = geldig;
            this.row = row;
            this.kolom = kolom;
        }
    }

    public Puzzel3b(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        Map<Integer, Point> oplossing = new Hashtable<>();
        List<String> list = new ArrayList<String>();

        int maxRow = 0;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        }
        char[][] array = new char[list.size()][];
        int ind = 0;
        for (String s :
                list) {
            char[] x = s.toCharArray();
            array[ind++] = x;
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
                if (Character.isDigit(array[i][j])) {
                    Resultaat resultaat = checkSymbol(array, i, j);
                    if (resultaat.geldig) {
                        String gevondenGetal = extraherenGetal(array[i], j);

                        oplossing.put(Integer.parseInt(gevondenGetal),new Point(resultaat.row, resultaat.kolom));
                        // schuif j door tot na cijfer
                        while (j < array[i].length && Character.isDigit(array[i][j])) {
                            j++;
                        }
                    }
                }
            }
            System.out.println();
        }

        System.out.println("oplossing = " + oplossing);
        Map<Point, Long> countingMap = oplossing.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.counting()));
        System.out.println("countingMap = " + countingMap);

        // Filter op basis van count == 2 en verzamel in een nieuwe map
        Map<Point, Integer> resultaat = countingMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> oplossing.entrySet().stream()
                        .filter(e -> e.getValue().equals(entry.getKey()))
                        .map(Map.Entry::getKey)
                        .reduce(1, (a, b) -> a * b)));

        // Resultaten afdrukken
        resultaat.forEach((punt, som) -> System.out.println("Punt: " + punt + ", Som: " + som));

        int somVanIntegers = countingMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 2)
                .map(entry -> oplossing.entrySet().stream()
                        .filter(e -> e.getValue().equals(entry.getKey()))
                        .map(Map.Entry::getKey)
                        .reduce(1, (a, b) -> a * b))
                .reduce(0, Integer::sum);

        System.out.println("De som van de integers waarvan het aantal overeenkomsten gelijk is aan 2 is: " + somVanIntegers);

    }

    private Resultaat checkSymbol(char[][] matrix, int rij, int kolom) {
        int[] rijStappen = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] kolomStappen = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int k = 0; k < rijStappen.length; k++) {
            int nieuweRij = rij + rijStappen[k];
            int nieuweKolom = kolom + kolomStappen[k];

            if (isGeldig(matrix, nieuweRij, nieuweKolom)) {
                return new Resultaat(true, nieuweRij, nieuweKolom);
            }
        }
        return new Resultaat(false, -1, -1);
    }

    private boolean isGeldig(char[][] matrix, int rij, int kolom) {
        return rij >= 0
                && rij < matrix.length
                && kolom >= 0
                && kolom < matrix[0].length
                && (matrix[rij][kolom] == '*')
                ;
    }

    public String extraherenGetal(char[] input, int startPositie) {
        // Controleer of de startpositie geldig is
        if (startPositie >= 0 && startPositie < input.length) {
            // Zoek naar links naar cijfers
            StringBuilder getalBuilder = new StringBuilder();
            for (int i = startPositie; i >= 0; i--) {
                char huidigKarakter = input[i];

                // Voeg cijfers toe aan het getal
                if (Character.isDigit(huidigKarakter)) {
                    getalBuilder.insert(0, huidigKarakter);
                } else {
                    // Stop zodra een niet-cijferkarakter wordt bereikt
                    break;
                }
            }

            // Zoek naar rechts naar cijfers
            for (int i = startPositie + 1; i < input.length; i++) {
                char huidigKarakter = input[i];

                // Voeg cijfers toe aan het getal
                if (Character.isDigit(huidigKarakter)) {
                    getalBuilder.append(huidigKarakter);
                } else {
                    // Stop zodra een niet-cijferkarakter wordt bereikt
                    break;
                }
            }

            return getalBuilder.toString();
        }

        return null; // Ongeldige startpositie
    }
}

