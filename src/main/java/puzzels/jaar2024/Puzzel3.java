package puzzels.jaar2024;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Puzzel3 extends abstractPuzzel {


    public Puzzel3(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        long som = 0;
        boolean overslaan = false;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringBuffer buffer = new StringBuffer(line);
//                System.out.println("l:"+line);

                Pattern pattern = Pattern.compile("mul\\(.*?\\)|do\\(\\)|don't\\(\\)");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    System.out.print("Gevonden: " + matcher.group());
                    String group = matcher.group();

                    System.out.println(group.substring(0,3));
                    switch (group.substring(0,3)) {
                        case "do(":
                            overslaan=false;
                            break;
                        case "don":
                            overslaan=true;
                            break;
                        case "mul":
                            if (!overslaan) {
                                som = getSom(som, matcher);
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            System.out.println("som = " + som);
        }
    }

    private long getSom(long som, Matcher matcher) {
        String substr = matcher.group().substring(4);
        if (substr.contains("mul(")) substr = substr.substring(substr.indexOf("mul(")+4);

        substr = substr.substring(0, substr.length()-1);
        int[] rij = readIntegers(substr);
        som += rij[0]*rij[1];
        System.out.println("    subsom:"+ rij[0]*rij[1]);
        return som;
    }

}

