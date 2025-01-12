package puzzels.jaar2015;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

public class Puzzel3 {
    private static final int C_JAAR = 2015;
    private static final String C_PUZZELNR = "3";

    private record Vector(Integer xCor, Integer Ycor) {
    }

    public static void start() throws IOException {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input/" + C_JAAR + "/input" + C_PUZZELNR + ".txt";

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            Vector santa = new Vector(0, 0);
            Vector robo = new Vector(0, 0);
            HashSet<Vector> houses = new HashSet<Vector>();
            houses.add(santa);

            int pos = 0;
            int c;
            while ((c = br.read()) != -1) {
                char k = (char) c;

                Vector newVector = null;
                if (pos % 2 == 0) {
                    newVector = getVector(santa, k);
                    santa = newVector;
                }else {
                    newVector = getVector(robo, k);
                    robo = newVector;
                }
                houses.add(newVector);
                pos++;
            }

            System.out.printf("Aantal houses: %d\n", houses.size());
        }
    }

    @Nullable
    private static Vector getVector(Vector paramVector, char k) {
        Vector newVetor = null;
        switch (k) {
            case '^':
                newVetor = new Vector(paramVector.xCor(), paramVector.Ycor() + 1);
                break;
            case '>':
                newVetor = new Vector(paramVector.xCor() + 1, paramVector.Ycor());
                break;
            case '<':
                newVetor = new Vector(paramVector.xCor() - 1, paramVector.Ycor());
                break;
            case 'v':
                newVetor = new Vector(paramVector.xCor(), paramVector.Ycor() - 1);
                break;
        }
        return newVetor;
    }
}
