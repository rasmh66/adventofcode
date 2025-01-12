package puzzels.jaar2024;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzel4 extends abstractPuzzel {


    public Puzzel4(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        long som = 0;
        List<char[]> lijst = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringBuffer buffer = new StringBuffer(line);
//                System.out.println("l:"+line);

                lijst.add(line.toCharArray());
            }
            char[][] matrix = convertListToCharArray(lijst);

            String word = "XMAS";
            int found = searchWord(matrix, word);
            System.out.println("Aantal worden gevonden: " + found);
        }
    }
    public int searchWord(char[][] matrix, String word) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int len = word.length();
        int som=0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (searchFrom(matrix, word, i, j, 0, 1)) som++; // Horizontaal rechts
                if (searchFrom(matrix, word, i, j, 0, -1)) som++; // Horizontaal links
                if (searchFrom(matrix, word, i, j, 1, 0)) som++; // Verticaal naar beneden
                if (searchFrom(matrix, word, i, j, -1, 0)) som++; // Verticaal naar boven
                if (searchFrom(matrix, word, i, j, 1, 1)) som++; // Diagonaal naar rechtsonder
                if (searchFrom(matrix, word, i, j, -1, -1)) som++; // Diagonaal naar linksboven
                if (searchFrom(matrix, word, i, j, 1, -1)) som++; // Diagonaal naar linksonder
                if (searchFrom(matrix, word, i, j, -1, 1)) { // Diagonaal naar rechtsboven
//                    System.out.println("i,j:"+i+","+j);
                    som++;
                }
            }
        }
        return som;
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

