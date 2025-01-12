package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Puzzel3 extends abstractPuzzel {

    public Puzzel3(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        List<Integer> oplossing = new ArrayList<>();
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
                    if (checkSymbol(array, i, j)) {
                        String gevondenGetal = extraherenGetal(array[i], j);

                        oplossing.add(Integer.parseInt(gevondenGetal));
                        // schuif j door tot na cijfer
                        while (j < array[i].length && Character.isDigit(array[i][j])) {
                            j++;
                        }
                    }
                }
            }
            System.out.println();
        }

        long uitkomst = 0;
        System.out.println("oplossing = " + oplossing);
        for (int getal :
                oplossing) {
            uitkomst += getal;
        }
        final long uitkomst2 = oplossing
                .stream()
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("uitkomst = " + uitkomst);
        System.out.println("uitkomst2 = " + uitkomst2);
    }

    private boolean checkSymbol(char[][] matrix, int rij, int kolom) {
        int[] rijStappen = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] kolomStappen = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int k = 0; k < rijStappen.length; k++) {
            int nieuweRij = rij + rijStappen[k];
            int nieuweKolom = kolom + kolomStappen[k];

            if (isGeldig(matrix, nieuweRij, nieuweKolom)) {
                return true;
            }
        }
        return false;
    }

    private boolean isGeldig(char[][] matrix, int rij, int kolom) {
        return rij >= 0
                && rij < matrix.length
                && kolom >= 0
                && kolom < matrix[0].length
                && (!Character.isDigit(matrix[rij][kolom]) && !(matrix[rij][kolom] == '.'))
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

