package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzel9 extends abstractPuzzel {

    public Puzzel9(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        List<int[]> lijst = new ArrayList<>();
        int history = 0;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {

                int[] arrayInt = readIntegers(line);
                lijst.add(arrayInt);

                int[] verschil = Arrays.copyOf(arrayInt, arrayInt.length);
                boolean doorgaan = true;
                while (doorgaan) {
                    verschil = berekenVerschil(verschil);
                    lijst.add(verschil);
                    for (int i :
                            verschil) {
                        doorgaan = false;
                        if (i != 0) {
                            doorgaan = true;
                            break;
                        }
                    }
                }
//                System.out.println("lijst = " + lijst);

                history += extrapolate(lijst);
                lijst.clear();
            }
        }
        System.out.println("history = " + history);
    }

    private int extrapolate(List<int[]> lijst) {
        int nieuw = 0;
        for (int i = lijst.size() - 2; i > -1; i--) {
            int[] rij = lijst.get(i);
            int eerst = rij[0];
            nieuw = eerst - nieuw;
        }
        System.out.println("nieuw = " + nieuw);
        return nieuw;
    }

    private int extrapolateLaast(List<int[]> lijst) {
        int nieuw = 0;
        for (int i = lijst.size() - 2; i > -1; i--) {
            int[] rij = lijst.get(i);
            int laatste = rij[rij.length - 1];
            nieuw += laatste;
        }
        System.out.println("nieuw = " + nieuw);
        return nieuw;
    }

    private int[] berekenVerschil(int[] arrayInt) {
        int[] verschil = new int[arrayInt.length - 1];

        for (int i = 1; i < arrayInt.length; i++) {
            verschil[i - 1] = arrayInt[i] - arrayInt[i - 1];
        }
        return verschil;
    }
}

