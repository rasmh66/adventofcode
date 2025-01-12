package puzzels.jaar2016;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Puzzel03 extends abstractPuzzel {

    public class Driehoek {
        int zijde1, zijde2, zijde3;
        Driehoek(int i1, int i2, int i3){
            zijde1=i1;
            zijde2=i2;
            zijde3=i3;
        }

        Boolean isGeldig(){
            if ((zijde1<zijde2+zijde3 && zijde2<zijde1+zijde3 && zijde3<zijde1+zijde2)) {
                System.out.printf("%d %d: %d %d\n",zijde1,zijde2,zijde1+zijde2,zijde3);
                return true;
            }
            return false;
        }
    }

    public Puzzel03(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            int total=0;
            int[][] matrix = new int[3][3];

            String line;
            int teller = 0;
            while ((line = br.readLine()) != null) {
                int i1 = Integer.parseInt(line.substring(0,5).trim());
                int i2 = Integer.parseInt(line.substring(6,11).trim());
                int i3 = Integer.parseInt(line.substring(11,15).trim());
                System.out.println("i: "+i1+" "+i2+" "+i3);

                matrix[teller][0] = i1;
                matrix[teller][1] = i2;
                matrix[teller][2] = i3;
                teller++;

                if (teller == 3){
                    Driehoek dh1 = new Driehoek(matrix[teller-3][0],matrix[teller-2][0],matrix[teller-1][0]);
                    Driehoek dh2 = new Driehoek(matrix[teller-3][1],matrix[teller-2][1],matrix[teller-1][1]);
                    Driehoek dh3 = new Driehoek(matrix[teller-3][2],matrix[teller-2][2],matrix[teller-1][2]);
//                    System.out.println("dh1:"+dh1);
                    teller=0;
                    if (dh1.isGeldig())  total++;
                    if (dh2.isGeldig())  total++;
                    if (dh3.isGeldig())  total++;
                }
            }
            System.out.println("Tot:"+total);
        }
    }
}
