package puzzels.jaar2022;

import java.io.*;
import java.util.*;

import static java.lang.Math.abs;

public class Puzzel9 {

    private static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
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
            return "{" + x + "," + y + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return getX() == point.getX() && getY() == point.getY();
        }

        @Override
        public int hashCode() {
            return Objects.hash(getX(), getY());
        }

        public Point copy() {
            return new Point(this.x, this.y);
        }
    }

    private static class Snake {
        LinkedHashMap<Integer, Point> inhoud = new LinkedHashMap<>(10);

        public Snake() {
            for (int i = 0; i < 10; i++) {
                Point temp = new Point(0, 0);
                inhoud.put(i, temp);
            }
        }

        public Point getKey(Integer key) {
            return inhoud.get(key);
        }

        public void setKey(Integer key, Point inhoud) {
            this.inhoud.put(key, inhoud);
        }

        private void movePoint(Integer key, Point waarde) {
            inhoud.put(key, waarde);
        }

        public int lengte() {
            return inhoud.size();
        }

        public void print() {
            int max = 15;
            for (int i = max; i > -max - 1; i--) {
                for (int j = -max; j < max; j++) {
                    int w = zoekWaarde(j, i);
                    if (w != -1) {
                        System.out.printf("%d", w);
                    } else if (i == 0 && j == 0) {
                        System.out.printf("s");
                    } else {
                        System.out.printf(".");
                    }
                }
                System.out.printf("\n");
            }
        }


        private int zoekWaarde(int x, int y) {
            for (int k = 0; k < inhoud.size(); k++) {
                Point p = inhoud.get(k);
                if (p.getX() == x && p.getY() == y) {
                    return k;
                }
            }
            return -1;
        }

        @Override
        public String toString() {
            return "Snake{\n" +
                    "inhoud=" + inhoud +
                    '}';
        }

    }

    public void start() throws Exception {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input/input9.txt";
        File file = new File(fileName);

        StringBuilder sb = new StringBuilder();
        InputStream in = new FileInputStream(file);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

//            point head = new point(0, 0);
//            point tail = new point(0, 0);
            Set<Point> tailLijst = new HashSet<>();
            Snake snake = new Snake();

            String line;
            while ((line = br.readLine()) != null) {
                int len = line.length();
                String[] str = line.split(" ");
                String direction = str[0];
                int posities = Integer.parseInt(str[1]);
                System.out.printf("d:%s %d\n", direction, posities);

                for (int i = 0; i < posities; i++) {

                    switch (direction) {
                        case "R":
                            moveRicht(snake);
                            break;
                        case "L":
                            moveLeft(snake);
                            break;
                        case "U":
                            moveUp(snake);
                            break;
                        case "D":
                            moveDown(snake);
                            break;
                        default:
                            System.out.println("ERROR directions onbekend");
                    }
                    moveTail(snake);
                    tailLijst.add(snake.getKey(9).copy());
                }
                System.out.printf("H: %s", snake);
                System.out.printf(" n: %s\n", tailLijst);
//                snake.print();
            }
//            snake.print();
            System.out.printf("Teller: %d", tailLijst.size());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isSkip(Point head, Point tail) {
        int dy = abs(head.getY() - tail.getY());
        int dx = abs(head.getX() - tail.getX());

//        if (dy < 2 && dx < 2) return true;
        // touching then true
        if (dy == 1 && dx == 1) return true;
        if (dy == 0 && dx == 1) return true;
        if (dy == 1 && dx == 0) return true;
        if (dy == 0 && dx == 0) return true;

        return false;
    }

    private static void moveDown(Snake snake) {

        Point head = snake.getKey(0);
        head.setY(head.getY() - 1);

    }

    private static void moveUp(Snake snake) {

        Point head = snake.getKey(0);
        head.setY(head.getY() + 1);
    }

    private static void moveLeft(Snake snake) {
        Point head = snake.getKey(0);
        head.setX(head.getX() - 1);
    }

    private static void moveRicht(Snake snake) {

        Point head = snake.getKey(0);
        head.setX(head.getX() + 1);
    }

    private static void moveTail(Snake snake) {

        for (int i = 1; i < snake.lengte(); i++) {
            Point first = snake.getKey(i - 1);
            Point second = snake.getKey(i);

            int hy = first.getY();
            int hx = first.getX();
            int ty = second.getY();
            int tx = second.getX();
            int dx = hx - tx;

            if (!isSkip(first, second)) {
                if (hy == ty) {
                    if (dx > 0) {
                        second.setX(tx + 1);
                    } else if (dx < 0) {
                        second.setX(tx - 1);
                    } else {
                        System.out.println("ERROR tail");
                    }
                } else if (hy < ty) {
                    if (dx > 0) {
                        // diagonaal onder rechts
                        second.setX(tx + 1);
                        second.setY(ty - 1);
                    } else if (dx < 0) {
                        // diagonaal onder links
                        second.setX(tx - 1);
                        second.setY(ty - 1);
                    } else {
                        second.setY(ty - 1);
                    }
                } else if (hy > ty) {
                    if (dx > 0) {
                        // diagonaal boven rechts
                        second.setX(tx + 1);
                        second.setY(ty + 1);
                    } else if (dx < 0) {
                        // diagonaal boven links
                        second.setX(tx - 1);
                        second.setY(ty + 1);
                    } else {
                        second.setY(ty + 1);
                    }
                }
            }
        }
    }

}
