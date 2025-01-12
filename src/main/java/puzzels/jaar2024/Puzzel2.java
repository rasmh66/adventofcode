package puzzels.jaar2024;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.apache.maven.surefire.shared.lang3.BooleanUtils.and;

public class Puzzel2 extends abstractPuzzel {


    public Puzzel2(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        int safeReports = 0;
        int index = 0;
        int som = 0;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringBuffer buffer = new StringBuffer(line);
                System.out.print("l:"+line);

                int[] report = readIntegers(line);

                int level = isReportsave(report);
                if (level == -1) {
                    safeReports++;
                    System.out.println(" : SAVE");
                } else {
                    report = removeLevel(report, level);
                    level = isReportsave(report);
                    if (level == -1) {
                        safeReports++;
                        System.out.println(" : SAVE");
                    } else {
                        System.out.println(" : UNSAVE");
                    }
                }

            }
            System.out.println("som = " + safeReports);
        }
    }

    private int[] removeLevel(int[] report, int level) {
        int[] newReport = new int[report.length - 1];
        int index=0;
        for (int i = 0; i < report.length; i++) {
            if (i != level) newReport[index++] = report[i];
        }
        return newReport;
    }

    private int isReportsave(int[] report) {

        Boolean decrease = null;
        Boolean increase = null;
        int vorige = report[0];
        for (int i = 1; i < report.length; i++) {
            int huidige = report[i];

            if (vorige > huidige) decrease=true;
            if (vorige < huidige) increase=true;
            if ((Boolean.TRUE.equals(decrease) && Boolean.TRUE.equals(increase)) ||
                (vorige==huidige)) return i-1;

            if (Math.abs((vorige-huidige)) > 3) {
//                System.out.printf("(%d,%d)", vorige, huidige);
                return i-1;
            }
            vorige=huidige;
        }
        return -1;
    }
}

