package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puzzel16 extends abstractPuzzel {
    
    public Puzzel16(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        List<char[]> matrix = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                matrix.add(readChars(line));
            }
        }
        char[][] charMatrix = convertListToCharArray(matrix);
        printMatrix(charMatrix);

        int x, y = 0;


    }
}

