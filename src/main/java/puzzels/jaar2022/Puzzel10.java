package puzzels.jaar2022;

//import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Puzzel10 {

    public class Cpu {
        private static final char C_LIGHT = '#';
        private static final char C_DARK = '.';
        private int cycle = -1;
        private int sprite = 1;
        List<Integer> totaal = new ArrayList<>();
        int mod = 20;
        private Character[][] CRT = new Character[6][40];

        public int getCycle() {
            return cycle;
        }

        public int getSprite() {
            return sprite;
        }

        public List<Integer> getTotaal() {
            return totaal;
        }

        public void command(String cmd, int param) {

            switch (cmd) {
                case "noop":
                    cycle++;
                    fillCRT(cycle, sprite);
//                    checkModCycle();
                    break;
                case "addx":
                    cycle++;
                    fillCRT(cycle, sprite);
//                    checkModCycle();
                    cycle++;
                    fillCRT(cycle, sprite);
//                    checkModCycle();
                    sprite += param;
                    break;
                default:
                    System.out.println("Onbekend cmd");
                    break;
            }
            System.out.printf("c:%d w:%d\n", cycle, sprite);
//            if (totaal.size() > 0) {
//                System.out.printf("\t\tt:%d\n", totaal.get(totaal.size() - 1));
//            } else {
//                System.out.printf("\n");
//            }
        }

        private void checkModCycle() {
            if (cycle % mod == 0) {
                totaal.add(cycle * sprite);

                mod += 40;
            }
        }

        private void fillCRT(int cycle, int sprite) {
            int row = cycle / 40;
            int kolom = cycle % 40;
            if (kolom == sprite - 1 || kolom == sprite || kolom == sprite + 1) {
                CRT[row][kolom] = C_LIGHT;
            } else {
                CRT[row][kolom] = C_DARK;
            }
        }

        private void drawCRT() {
            for (int i = 0; i < CRT.length; i++) {
                for (int j = 0; j < CRT[0].length; j++) {
                    System.out.printf("%c", CRT[i][j]);
                }
                System.out.printf("\n");
            }
        }
    }

    public void start() throws Exception {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input/input10.txt";
        File file = new File(fileName);

        StringBuilder sb = new StringBuilder();
        InputStream in = new FileInputStream(file);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            Cpu processor = new Cpu();
            String line;
            while ((line = br.readLine()) != null) {
                int len = line.length();
                String[] str = line.split(" ");
                String cmd = str[0];
                int posities = 0;
                if (str.length == 2) {
                    posities = Integer.parseInt(str[1]);
                }
//                System.out.printf("d:%s %d \t\t", cmd, posities);

                processor.command(cmd, posities);
            }
            processor.drawCRT();

//            int tot = 0;
//            for (Integer i :
//                    processor.getTotaal()) {
//                tot += i;
//                System.out.printf("Deel tot: %d\n", i);
//            }
//            System.out.printf("Tot: %d\n", tot);
        }
    }
}
