package puzzels.jaar2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import puzzels.jaar2016.Puntenlijst;

public class Puzzel01 extends puzzels.abstractPuzzel {

    public Puzzel01(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        var punten = new Puntenlijst();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {

//                line = "R4, L3, R2, R3";
                handleLine(punten, line);
            }
        }
    }

    protected void handleLine(Puntenlijst points, String line) {
        String[] item = line.split(",");

        for (String direc :
                item) {
//                    System.out.printf("%s;", direc.trim());
            addPoint(direc.trim(), points);

            if (points.getOplossing() != null) {
                System.out.println("oplossing = " + points.getOplossing());
                System.out.println("som = " + points.getSom());
                return;
            }
        }
//        System.out.println("puntenLijst = " + puntenLijst);
    }

    protected void addPoint(String direc, Puntenlijst points) {
//        System.out.println("direc = " + direc);
//        System.out.println("direc.substring(1, 1) = " + direc.substring(1));
        int len = Integer.parseInt(direc.substring(1));
        char dir = direc.charAt(0);
        switch (dir) {
            case 'R' -> points.addRight(len);
            case 'L' -> points.addLeft(len);
        }
    }
}