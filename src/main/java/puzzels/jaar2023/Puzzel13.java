package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzel13 extends abstractPuzzel {

    public Puzzel13(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        List<char[]> matrix = new ArrayList<>();
        int result = 0;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            do {
                while ((line = br.readLine()) != null) {
                    if (line.length() == 0) {
                        break;
                    }
                    matrix.add(readChars(line));
                }
                char[][] charMatrix = convertListToCharArray(matrix);
//                printMatrix(charMatrix);

                int kolom = 0;
                int rows = searchHorizon(charMatrix);
                if (rows == 0) {
                    charMatrix = wisselKolommenMetRijen(charMatrix);
//                    printMatrix(charMatrix);
                    kolom = searchHorizon(charMatrix);
                }
                result += kolom + (rows * 100);
                System.out.println("r = " + rows + " " + kolom + " = " + result);

                matrix.clear();
            } while (line != null);
        }
        System.out.println("result = " + result);
    }

    private int searchHorizon(char[][] matrix) {
        boolean geenMatch = false;
        for (int i = 1; i < matrix.length; i++) {
            if (isEquals(matrix, i, i - 1)) {
                int achteruitIndex = i - 1;
                for (int vooruitIndex = i + 1; vooruitIndex < matrix.length; vooruitIndex++) {
                    achteruitIndex--;
                    if (achteruitIndex >= 0) {
                        if (!isEquals(matrix, vooruitIndex, achteruitIndex)) {
                            geenMatch = true;
                            break;
                        }
                    }
                }
                if (!geenMatch) {
                    return i;
                }
                geenMatch = false;
            }
        }
        return 0;
    }

    private static boolean isEquals(char[][] matrix, int i1, int i2) {
        if (Arrays.equals(matrix[i1], matrix[i2])) return true;

        int aantal = 0;
        int lengte = matrix[i1].length;

        for (int i = 0; i < lengte; i++) {
            if (matrix[i1][i] == matrix[i2][i]) {
                aantal++;
            }
        }
        return aantal == lengte - 1;
    }

    // Functie om kolommen met rijen te wisselen
    public static char[][] wisselKolommenMetRijen(char[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Maak een tijdelijke matrix aan om de gewisselde waarden op te slaan
        char[][] gewisseldeMatrix = new char[cols][rows];

        // Wissel kolommen met rijen
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gewisseldeMatrix[j][i] = matrix[i][j];
            }
        }

        // Kopieer de gewisselde waarden terug naar de oorspronkelijke matrix
//        for (int i = 0; i < cols; i++) {
//            System.arraycopy(gewisseldeMatrix[i], 0, matrix[i], 0, rows);
//        }
        return gewisseldeMatrix;
    }

    // Functie om de matrix af te drukken
    public static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (char element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // Function to convert List<char[]> to char[][]
    public static char[][] convertListToCharArray(List<char[]> charList) {
        return abstractPuzzel.convertListToCharArray(charList);
    }

}

