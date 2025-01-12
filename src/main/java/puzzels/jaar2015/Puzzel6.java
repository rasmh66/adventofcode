package puzzels.jaar2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Puzzel6 {
    private static final int C_JAAR = 2015;
    private static final String C_PUZZELNR = "6";

    public enum Stand {
        E_ON,
        E_OFF,
        E_TOGGLE
    }

    private static class Rechthoek {
        private Grid grid;
        private int x1 = 0;
        private int y1 = 0;
        private int x2 = 0;
        private int y2 = 0;

        public Rechthoek(Grid g, String sx1, String sy1, String sx2, String sy2) {
            grid = g;
            x1 = Integer.parseInt(sx1);
            y1 = Integer.parseInt(sy1);
            x2 = Integer.parseInt(sx2);
            y2 = Integer.parseInt(sy2);
            System.out.printf(" %d %d %d %d\n", x1, y1, x2, y2);
        }

        public void verander(Stand stand) {
            for (int i = y1; i <= y2; i++) {
                for (int j = x1; j <= x2; j++) {
                    grid.zetLight(i, j, stand);
                }
            }
        }
    }

    private static class Grid {
        //        private final Boolean[][] grid = new Boolean[1000][1000];
        private final int[][] grid = new int[1000][1000];

        public Grid() {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
                    grid[i][j] = 0;
//                    System.out.printf("%s", grid[i][j] ? "#":"0");
                }
//                System.out.println("\n");
            }
        }

        public long telGrid() {
            long aantal = 0;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid.length; j++) {
//                    if (grid[i][j]) {
//                        aantal++;
//                    }
                    aantal += grid[i][j];
//                    System.out.printf("%s", grid[i][j] ? "#":"0");
                }
//                System.out.println("\n");
            }
            return aantal;
        }

        public void zetLight(int i, int j, Stand stand) {
            if (stand == Stand.E_ON) {
                grid[i][j]++;
            } else if (stand == Stand.E_OFF) {
                grid[i][j]--;
                if (grid[i][j] < 0) grid[i][j] = 0;
            } else if (stand == Stand.E_TOGGLE) {
//                grid[i][j] = !grid[i][j];
                grid[i][j]++;
                grid[i][j]++;
            }
        }
    }

    public static void start() throws IOException {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input/" + C_JAAR + "/input" + C_PUZZELNR + ".txt";

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            Grid g = new Grid();

            String line;
            String[] str1;
            String[] str2;
            Rechthoek r;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");

                switch (parts[0]) {
                    case "toggle":
                        str1 = parts[1].split(",");
                        str2 = parts[3].split(",");
                        r = new Rechthoek(g, str1[0], str1[1], str2[0], str2[1]);
                        r.verander(Stand.E_TOGGLE);
                        break;
                    case "turn":
                        if (parts[1].equals("on")) {
                            str1 = parts[2].split(",");
                            str2 = parts[4].split(",");
                            r = new Rechthoek(g, str1[0], str1[1], str2[0], str2[1]);
                            r.verander(Stand.E_ON);
                        } else if (parts[1].equals("off")) {
                            str1 = parts[2].split(",");
                            str2 = parts[4].split(",");
                            r = new Rechthoek(g, str1[0], str1[1], str2[0], str2[1]);
                            r.verander(Stand.E_OFF);
                        } else {
                            System.out.printf("\nERROR input\n");
                        }
                        break;
                }
            }
            long l = g.telGrid();
            System.out.printf("\nAantal: %d\n", l);
        }
    }

}
