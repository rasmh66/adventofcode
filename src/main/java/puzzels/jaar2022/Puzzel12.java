package puzzels.jaar2022;

import java.io.*;
import java.util.ArrayList;

public class Puzzel12 {

    private static final int C_MAXROW = 5;
    private static final int C_MAXKOLOM = 8;
    private Character[][] matrix = new Character[C_MAXROW][C_MAXKOLOM];
    vector vectorBegin = null;
    vector vectorEnd = null;
    ArrayList<vector> route = new ArrayList<vector>();

    private class vector {
        int x;
        int y;

        vector(int a, int b) {
            x = a;
            y = b;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "vector{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    boolean stepRechts(vector currentPos) {
        int x = currentPos.getX();
        int y = currentPos.getY();
        char a = matrix[y][x];

        if (currentPos == vectorEnd) return true;
        if (x + 1 == C_MAXKOLOM) return false;

        // rechts
        if (matrix[y][x + 1] >= a) {
            var v = new vector(y, x + 1);
            route.add(v);
            stepRechts(v);
        }
        return false;
    }

    boolean stepLinks(vector currentPos) {
        int x = currentPos.getX();
        int y = currentPos.getY();
        char a = matrix[y][x];

        if (currentPos == vectorEnd) return true;
        if (x - 1 == -1) return false;

        // rechts
        if (matrix[y][x - 1] >= a) {
            var v = new vector(y, x - 1);
            route.add(v);
            stepLinks(v);
        }
        return false;
    }
    boolean stepBoven(vector currentPos) {
        int x = currentPos.getX();
        int y = currentPos.getY();
        char a = matrix[y][x];

        if (currentPos == vectorEnd) return true;
        if (y - 1 == -1) return false;

        // rechts
        if (matrix[y-1][x] >= a) {
            var v = new vector(y-1, x);
            route.add(v);
            stepBoven(v);
        }
        return false;
    }
    boolean stepOnder(vector currentPos) {
        int x = currentPos.getX();
        int y = currentPos.getY();
        char a = matrix[y][x];

        if (currentPos == vectorEnd) return true;
        if (y + 1 == C_MAXROW) return false;

        // rechts
        if (matrix[y+1][x] >= a) {
            var v = new vector(y+1, x);
            route.add(v);
            stepOnder(v);
        }
        return false;
    }

    public void start() throws Exception {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input/input12a.txt";
        File file = new File(fileName);

        InputStream in = new FileInputStream(file);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            int index = -1;
            vector vectorCurrent = null;

            String line;
            int row = 0;
            while ((line = br.readLine()) != null) {
//                String[] str = line.trim().split(" ");
                for (int kolom = 0; kolom < C_MAXKOLOM; kolom++) {
                    matrix[row][kolom] = line.charAt(kolom);
                    if (matrix[row][kolom] == 'S') vectorBegin = new vector(kolom, row);
                    if (matrix[row][kolom] == 'E') vectorEnd = new vector(kolom, row);
                }
                row++;
            }

            System.out.printf("Van : %s\n", vectorBegin.toString());
            System.out.printf("Naar: %s\n", vectorEnd.toString());

            vectorCurrent = vectorBegin;
            stepRechts(vectorCurrent);

//            System.out.printf("Lengte: %d", index);
        }
    }
}
