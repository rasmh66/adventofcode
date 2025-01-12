package puzzels.jaar2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Puzzel02 extends puzzels.abstractPuzzel {

    public Puzzel02(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    private char translate(int input) {
        char output = switch (input) {
            case 1,2,3,4,5,6,7,8,9 -> (char) (input+'0');
            case 10 -> 'A';
            case 11 -> 'B';
            case 12 -> 'C';
            case 13 -> 'D';
            default -> {
                System.out.println("Error:"+input);
                throw new RuntimeException();
            }
        };
        return output;
    }

    public void start() throws IOException {
        int current = 5;
        int[] code = new int[6];
        int index = 0;

        int[][] logarr = new int[14][4];
        InitArray(logarr);
        Map<Character,Character> keypad = new HashMap<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (char c :
                        line.toCharArray()) {
                    int d = switch (c) {
                        case 'U' -> 0;
                        case 'D' -> 1;
                        case 'L' -> 2;
                        case 'R' -> 3;
                        default -> {
                            System.out.println("Error");
                            throw new RuntimeException();
                        }
                    };

                    int tempcurrent = logarr[current][d];
                    if (tempcurrent != 0) current = tempcurrent;
//                    System.out.printf("%c:%d\n",c,current);
                }
                // line end
                System.out.printf("%d: %d\n",index,current);
                code[index++] = current;
            }
            System.out.println("code = " + translate(code[0])
                    +translate(code[1])
                    +translate(code[2])
                    +translate(code[3])
                    +translate(code[4])
            );
        }
    }

    /*
         1
       2 3 4
     5 6 7 8 9
       A B C
         D
     A=10
     B=11
     C=12
     D=13
     */
    private void InitArray(int[][] logarr) {
        // U-D-L-R = 0-1-2-3
        logarr[1][0] = 0;
        logarr[1][1] = 3;
        logarr[1][2] = 0;
        logarr[1][3] = 0;

        logarr[2][0] = 0;
        logarr[2][1] = 6;
        logarr[2][2] = 0;
        logarr[2][3] = 3;

        logarr[3][0] = 1;
        logarr[3][1] = 7;
        logarr[3][2] = 2;
        logarr[3][3] = 4;

        logarr[4][0] = 0;
        logarr[4][1] = 8;
        logarr[4][2] = 3;
        logarr[4][3] = 0;

        logarr[5][0] = 0;
        logarr[5][1] = 0;
        logarr[5][2] = 0;
        logarr[5][3] = 6;

        logarr[6][0] = 2;
        logarr[6][1] = 10;
        logarr[6][2] = 5;
        logarr[6][3] = 7;

        logarr[7][0] = 3;
        logarr[7][1] = 11;
        logarr[7][2] = 6;
        logarr[7][3] = 8;

        logarr[8][0] = 4;
        logarr[8][1] = 12;
        logarr[8][2] = 7;
        logarr[8][3] = 9;

        logarr[9][0] = 0;
        logarr[9][1] = 0;
        logarr[9][2] = 8;
        logarr[9][3] = 0;

        logarr[10][0] = 6;
        logarr[10][1] = 0;
        logarr[10][2] = 0;
        logarr[10][3] = 0;

        logarr[11][0] = 7;
        logarr[11][1] = 13;
        logarr[11][2] = 10;
        logarr[11][3] = 12;

        logarr[12][0] = 8;
        logarr[12][1] = 0;
        logarr[12][2] = 11;
        logarr[12][3] = 0;

        logarr[13][0] = 11;
        logarr[13][1] = 0;
        logarr[13][2] = 0;
        logarr[13][3] = 0;

    }
/*
    private void InitArray(int[][] logarr) {
        // 1 2 3
        // 4 5 6
        // 7 8 9
        // U-D-L-R = 0-1-2-3
        logarr[1][0] = 0;
        logarr[1][1] = 4;
        logarr[1][2] = 0;
        logarr[1][3] = 2;

        logarr[2][0] = 0;
        logarr[2][1] = 5;
        logarr[2][2] = 1;
        logarr[2][3] = 3;

        logarr[3][0] = 0;
        logarr[3][1] = 6;
        logarr[3][2] = 2;
        logarr[3][3] = 0;

        logarr[4][0] = 1;
        logarr[4][1] = 7;
        logarr[4][2] = 0;
        logarr[4][3] = 5;

        logarr[5][0] = 2;
        logarr[5][1] = 8;
        logarr[5][2] = 4;
        logarr[5][3] = 6;

        logarr[6][0] = 3;
        logarr[6][1] = 9;
        logarr[6][2] = 5;
        logarr[6][3] = 0;

        logarr[7][0] = 4;
        logarr[7][1] = 0;
        logarr[7][2] = 0;
        logarr[7][3] = 8;

        logarr[8][0] = 5;
        logarr[8][1] = 0;
        logarr[8][2] = 7;
        logarr[8][3] = 9;

        logarr[9][0] = 6;
        logarr[9][1] = 0;
        logarr[9][2] = 8;
        logarr[9][3] = 0;

    }
 */
}
