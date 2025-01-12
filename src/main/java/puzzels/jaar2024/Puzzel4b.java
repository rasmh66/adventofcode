package puzzels.jaar2024;

import puzzels.abstractPuzzel;
import puzzels.common.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Puzzel4b extends abstractPuzzel {


    public Puzzel4b(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        long som = 0;
        List<char[]> lijst = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
//                System.out.println("l:"+line);
                lijst.add(line.toCharArray());
            }
            char[][] matrix = convertListToCharArray(lijst);

            String word = "MAS";
            Map<Coordinate, Integer> foundPunten = searchWord(matrix, word);
            for (Integer aantal: foundPunten.values() ) {
                if (aantal == 2) som++;
            }
            System.out.println("Aantal worden gevonden: " + som);
        }
    }
    public Map<Coordinate, Integer> searchWord(char[][] matrix, String word) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        Map<Coordinate, Integer> puntenA = new HashMap<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (searchFrom(matrix, word, i, j, 1, 1)) { // Diagonaal naar rechtsonder
                    Coordinate c = new Coordinate(i+1,j+1);
                    if (puntenA.containsKey(c)) {
                        puntenA.put(c,puntenA.get(c)+1);
                    } else {
                        puntenA.put(c, 1);
                    }
                }
                if (searchFrom(matrix, word, i, j, -1, -1)) { // Diagonaal naar linksboven
                    Coordinate c = new Coordinate(i-1,j-1);
                    if (puntenA.containsKey(c)) {
                        puntenA.put(c,puntenA.get(c)+1);
                    } else {
                        puntenA.put(c, 1);
                    }
                }
                if (searchFrom(matrix, word, i, j, 1, -1)) { // Diagonaal naar linksonder
                    Coordinate c = new Coordinate(i+1,j-1);
                    if (puntenA.containsKey(c)) {
                        puntenA.put(c,puntenA.get(c)+1);
                    } else {
                        puntenA.put(c, 1);
                    }
                }
                if (searchFrom(matrix, word, i, j, -1, 1)) { // Diagonaal naar rechtsboven
                    Coordinate c = new Coordinate(i-1,j+1);
                    if (puntenA.containsKey(c)) {
                        puntenA.put(c,puntenA.get(c)+1);
                    } else {
                        puntenA.put(c, 1);
                    }
                }
            }
        }
        return puntenA;
    }

    public boolean searchFrom(char[][] matrix, String word, int row, int col, int rowDir, int colDir) {
        int len = word.length();
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int k = 0; k < len; k++) {
            int newRow = row + k * rowDir;
            int newCol = col + k * colDir;

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || matrix[newRow][newCol] != word.charAt(k)) {
                return false;
            }
        }
        return true;
    }
}

