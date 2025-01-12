package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puzzel14 extends abstractPuzzel {

    public Puzzel14(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        List<char[]> matrix = new ArrayList<>();
        List<Long> longLijst = new ArrayList<>();
        int cycle = 1000000000;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                matrix.add(readChars(line));
            }
        }
        char[][] charMatrix = convertListToCharArray(matrix);
//        printMatrix(charMatrix);

        while (cycle-- > 1000000000 - 300) {
            charMatrix = verplaatsAllesNoord(charMatrix);
//        printMatrix(charMatrix);
            charMatrix = verplaatsAllesWest(charMatrix);
//        printMatrix(charMatrix);
            charMatrix = verplaatsAllesZuid(charMatrix);
//        printMatrix(charMatrix);
            charMatrix = verplaatsAllesOost(charMatrix);

            long hash = berekenHash(charMatrix);
            if (longLijst.contains(hash)) {
                int load = berekenLoad(charMatrix);
                System.out.println("cycle = " + cycle + "  " + load);
                longLijst.clear();
            } else {
                longLijst.add(hash);
            }
//            System.out.print(" " + hash);
//            if (cycle % 100 == 0) System.out.println();
//            System.out.println("cycle = " + cycle);
//            printMatrix(charMatrix);
        }
        int load = berekenLoad(charMatrix);

        System.out.println("load = " + load);
    }

    private long berekenHash(char[][] charMatrix) {
        long hash = 0;
        for (int i = 0; i < charMatrix.length; i++) {
            for (int j = 0; j < charMatrix[0].length; j++) {
                if (charMatrix[i][j] == 'O') {
                    hash += ((long) i * j);
                }
            }
        }
        return hash;
    }

    private int berekenLoad(char[][] charMatrix) {
        int load = 0;
        for (int i = 0; i < charMatrix.length; i++) {
            for (int j = 0; j < charMatrix[0].length; j++) {
                if (charMatrix[i][j] == 'O') {
                    load += (charMatrix.length - i);
                }
            }
        }
        return load;
    }

    private char[][] verplaatsAllesNoord(char[][] charMatrix) {
        int hoogte = charMatrix.length;
        int i = 1;
        while (i < hoogte) {
            if (verplaatsNoord(charMatrix, i)) {
                i = 1;
            } else {
                i++;
            }
        }
        return charMatrix;
    }

    private char[][] verplaatsAllesWest(char[][] charMatrix) {
        int hoogte = charMatrix.length;
        int i = 0;
        while (i < hoogte) {
            if (verplaatsWest(charMatrix, i)) i = -1;
            i++;
        }
        return charMatrix;
    }

    private char[][] verplaatsAllesZuid(char[][] charMatrix) {
        int hoogte = charMatrix.length;
        int i = 0;
        while (i < hoogte - 1) {
            if (verplaatsZuid(charMatrix, i)) i = -1;
            i++;
        }
        return charMatrix;
    }

    private char[][] verplaatsAllesOost(char[][] charMatrix) {
        int hoogte = charMatrix.length;
        int i = 0;
        while (i < hoogte) {
            if (verplaatsOost(charMatrix, i)) i = -1;
            i++;
        }
        return charMatrix;
    }

    private boolean verplaatsNoord(char[][] charMatrix, int i) {
        boolean isVerplaats = false;
        for (int j = 0; j < charMatrix[0].length; j++) {
            if (charMatrix[i][j] == 'O' && charMatrix[i - 1][j] == '.') {
                charMatrix[i][j] = '.';
                charMatrix[i - 1][j] = 'O';
                isVerplaats = true;
            }
        }
        return isVerplaats;
    }

    private boolean verplaatsWest(char[][] charMatrix, int i) {
        boolean isVerplaats = false;
        for (int j = 1; j < charMatrix[0].length; j++) {
            if (charMatrix[i][j] == 'O' && charMatrix[i][j - 1] == '.') {
                charMatrix[i][j] = '.';
                charMatrix[i][j - 1] = 'O';
                isVerplaats = true;
            }
        }
        return isVerplaats;
    }

    private boolean verplaatsZuid(char[][] charMatrix, int i) {
        boolean isVerplaats = false;
        for (int j = 0; j < charMatrix[0].length; j++) {
            if (charMatrix[i][j] == 'O' && charMatrix[i + 1][j] == '.') {
                charMatrix[i][j] = '.';
                charMatrix[i + 1][j] = 'O';
                isVerplaats = true;
            }
        }
        return isVerplaats;
    }

    private boolean verplaatsOost(char[][] charMatrix, int i) {
        boolean isVerplaats = false;
        for (int j = 0; j < charMatrix[0].length - 1; j++) {
            if (charMatrix[i][j] == 'O' && charMatrix[i][j + 1] == '.') {
                charMatrix[i][j] = '.';
                charMatrix[i][j + 1] = 'O';
                isVerplaats = true;
            }
        }
        return isVerplaats;
    }

}

