package puzzels.jaar2024;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Puzzel7 extends abstractPuzzel {

    public Puzzel7(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            long oplossing=0;
            String line;
            while ((line = br.readLine()) != null) {

                String[] splitStr = line.split(":");
                long totaal = Long.parseLong(splitStr[0]);
                int[] lijst = readIntegers(splitStr[1].trim(), " ");

//                System.out.println("splitStr = " + splitStr[0]);
//                System.out.println("lijst = " + lijst);

                int len = lijst.length-1;
                int macht2 = (int) Math.pow(2,len);
                for (int i = 0; i < macht2; i++) {

                    String binaryString = String.format("%"+len+"s", Integer.toBinaryString(i)).replace(' ', '0');
//                    System.out.println("i. = " + binaryString);

                    long result=0;
                    result += operatie(lijst[0], lijst[1], binaryString.charAt(0));
                    for (int j = 1; j < lijst.length-1; j++) {
                        result = operatie(result, lijst[j+1], binaryString.charAt(j));
                    }
                    if (totaal==result){
                        System.out.println("result = " + result);
//                        System.out.println("Gevonden: "+binaryString);
                        oplossing += result;
                        i=macht2;
                    }
                }
            }
            System.out.println("oplossing = " + oplossing);
        }
    }

    private long operatie(long huidige, int volgende, char charAt) {

        long result=0;
        switch (charAt) {
            case '0' -> result = huidige+volgende;
            case '1' -> result = huidige*volgende;
        }
        return result;
    }
}

