package puzzels.jaar2024;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

public class Puzzel1 extends abstractPuzzel {

    public Puzzel1(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        int index = 0;
        int som = 0;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringBuffer buffer = new StringBuffer(line);
                System.out.println("l:"+line);

                int leftInteger = Integer.parseInt(line.substring(0,line.indexOf(' ')));
                int rightInteger = Integer.parseInt(line.substring(line.length()-5));

                leftList.add(leftInteger);
                rightList.add(rightInteger);
            }
//            Collections.sort(leftList);
//            Collections.sort(rightList);

            for (int i = 0; i < leftList.size(); i++) {
                int similarity = 0;
                int left = leftList.get(i);
                for (int j = 0; j < rightList.size(); j++) {
                    if (left==rightList.get(j)) {
                        similarity++;
                    }
                }
                som += left*similarity;
//                System.out.println("tussen = " + som);
            }
            System.out.println("som = " + som);
        }
    }
}

