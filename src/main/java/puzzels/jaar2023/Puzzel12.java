package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puzzel12 extends abstractPuzzel {

    static int oplossingsAantal = 0;

    public Puzzel12(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        int arrangementen = 0;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
//            while ((line = br.readLine()) != null) {

            line = "???.??? 2,1";
//            line = "?###???????? 3,2,1";
//        line = "##?#.?...#... 4,1,1";
            String[] delen = line.split(" ");

            int[] getallen = readIntegers(delen[1].trim());

            List<String> oplosList = new ArrayList<>();
            for (int i = 0; i < delen[0].length(); i++) {
                if (delen[0].charAt(i) == '?') {
                    StringBuilder sb = new StringBuilder(delen[0]);
                    sb.setCharAt(i, '#');

                    String oplos = vindOplossing(sb.toString(), getallen, oplosList);

                }
            }
            System.out.println("oplos = " + oplosList.size());
            arrangementen += oplosList.size();
//            }
        }
        System.out.println("Aantal = " + arrangementen);
    }

    private String vindOplossing(String puzzel, int[] getallen, List<String> oplosList) {
        StringBuilder sb = new StringBuilder(puzzel);
        int aantal = 0;
        int indexGetal = 0;

//        System.out.print(" " + puzzel);
        if (!puzzel.contains("?")) {
            if (isGoedeOplossing(puzzel, getallen, oplosList)) {
                oplosList.add(puzzel);
                System.out.println("GOED   = " + puzzel);
                return puzzel;
            } else {
                return null;
            }
        } else {
            for (int i = 0; i < puzzel.length(); i++) {
                if (puzzel.charAt(i) == '.') {
                    ;
                } else if (puzzel.charAt(i) == '?') {
                    sb.setCharAt(i, '#');
                    String s = vindOplossing(sb.toString(), getallen, oplosList);
                    if (s != null) { //&& !s.contains("?")
                        return s;
                    } else {
                        sb.setCharAt(i, '.');
                        s = vindOplossing(sb.toString(), getallen, oplosList);
                        if (s != null && !s.contains("?")) {
                            return s;
                        }
                    }
                } else if (puzzel.charAt(i) == '#') {
//                aantal++;
                    ;
                }
            }
        }
//        System.out.println("sb = " + sb.toString());
        return null;
    }

    private boolean isGoedeOplossing(String puzzel, int[] getallen, List<String> oplosList) {
//        System.out.print("puzzel = " + puzzel);
        if (oplosList.contains(puzzel)) return false;

        String zonderPunten = puzzel
                .replaceAll("^\\.+", "")
                .replaceAll("\\.+$", "");

        String[] bits = zonderPunten.split("\\.+");
        if (bits.length != getallen.length) return false;
//        System.out.println("        bits = " + Arrays.toString(bits));

        for (int i = 0; i < getallen.length; i++) {
            if (bits[i].length() != getallen[i]) return false;
        }
        return true;
    }
}