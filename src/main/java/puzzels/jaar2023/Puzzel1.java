package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Puzzel1 extends abstractPuzzel {

    public Puzzel1(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        String[] strNumbers = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        int som = 0;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringBuffer buffer = new StringBuffer(line);

                int first = -1;
                for (int i = 0; i < buffer.length(); i++) {
                    char c = buffer.charAt(i);
                    if (Character.isDigit(c)) {
                        first = Character.getNumericValue(c);
                        break;
                    } else {
                        for (int j = 0; j < strNumbers.length; j++) {
                            if (buffer.substring(i).startsWith(strNumbers[j])) {
                                first = j+1;
                                i = buffer.length();
                                break;
                            }
                        }
                    }
                }
                int second = -1;
                for (int i = buffer.length()-1; i >= 0; i--) {
                    char c = buffer.charAt(i);
                    if (Character.isDigit(c)) {
                        second = Character.getNumericValue(c);
                        break;
                    } else {
                        for (int j = 0; j < strNumbers.length; j++) {
                            if (buffer.substring(i).startsWith(strNumbers[j])) {
                                second = j+1;
                                i = 0;
                                break;
                            }
                        }
                    }
                }
                if ((first == -1) || (second == -1)) {
                    System.out.println("Error:" + line);
                    throw new RuntimeException();
                }
                int x = first*10 + second;
                System.out.println("x = " + x);

                som += x;
            }
            System.out.println("som = " + som);
        }
    }
}

