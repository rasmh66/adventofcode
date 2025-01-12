package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzel11 extends abstractPuzzel {

    public class Hulp {
        List<String> matrix = new ArrayList<>();
    }

    public Puzzel11(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        Hulp h = new Hulp();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                h.matrix.add(line);
            }
        }
        for (String str : h.matrix) {
            System.out.println("str = " + str);
        }
        addLegePunten(h);

        List<Point> charLijst = zoekChars(h);
        int afstand = 0;
        for (int i = 0; i < charLijst.size(); i++) {
            for (int j = i + 1; j < charLijst.size(); j++) {
                System.out.printf("(%d,%d)", i + 1, j + 1);
                int x = Math.abs((int) (charLijst.get(i).getX() - charLijst.get(j).getX()));
                int y = Math.abs((int) (charLijst.get(i).getY() - charLijst.get(j).getY()));
                System.out.println(" tot = " + (y + x));
                afstand += x + y;
            }
        }
//        System.out.println("\ncharLijst = " + charLijst);
        System.out.println("\nafstand = " + afstand);
    }

    private List<Point> zoekChars(Hulp h) {
        List<Point> charLijst = new ArrayList<>();
        for (int i = 0; i < h.matrix.size(); i++) {
            for (int j = 0; j < h.matrix.get(0).length(); j++) {
                if (h.matrix.get(i).charAt(j) == '#') {
                    charLijst.add(new Point(i, j));
                }
            }
        }
        return charLijst;
    }

    private void addLegePunten(Hulp h) {
        boolean[] kolomVol = new boolean[h.matrix.get(0).length()];

        for (int i = 0; i < h.matrix.size(); i++) {
            String regel = h.matrix.get(i);
            char[] dots = new char[regel.length()];
            Arrays.fill(dots, '.');

            if (!regel.contains("#")) {
                h.matrix.add(i++, Arrays.toString(dots));
            } else {
                char[] c = regel.toCharArray();
                for (int j = 0; j < c.length; j++) {
                    if (c[j] == '#') {
                        kolomVol[j] = true;
                    }
                }
            }
        }
        for (int i = kolomVol.length - 1; i >= 0; i--) {
            if (!kolomVol[i]) {
                addKolom(i, kolomVol.length, h);
            }
        }
    }

    private void addKolom(int i, int l, Hulp h) {
        List<String> hulpMatrix = new ArrayList<>(h.matrix.size());
        for (String str : h.matrix) {
            StringBuilder buf = new StringBuilder(str);
            buf.setLength(str.length() + l);
            buf.insert(i, '.');
            hulpMatrix.add(buf.toString());
        }
        h.matrix = hulpMatrix;
    }

}
