package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzel10 extends abstractPuzzel {

    private static int step;
    private static int startX = -1;
    private static int startY = -1;

    public enum WR {
        NOORD,
        OOST,
        ZUID,
        WEST,
        GEEN,
        START
    }

    protected static class Pipe {
        WR begin;
        WR eind;

        public Pipe(WR begin, WR eind) {
            this.begin = begin;
            this.eind = eind;
        }

        @Override
        public String toString() {
            return "P{" +
                    "begin=" + begin +
                    ", eind=" + eind +
                    '}';
        }

        public WR gaNaar(WR input) {
            if (this.begin == input) return this.eind;
            if (this.eind == input) return this.begin;
            return WR.GEEN;
        }
    }

    public static class Returnwaarde {
        Pipe[][] pipes = null;
        int startX = 0;
        int startY = 0;

        public Returnwaarde(Pipe[][] pipes, int startX, int startY) {
            this.pipes = pipes;
            this.startX = startX;
            this.startY = startY;
        }
    }

    public Puzzel10(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        List<String> strLijst = new ArrayList<>();
        Pipe[][] plattegrond = null;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            int rij = 0;
            while ((line = br.readLine()) != null) {
                strLijst.add(line);
            }
            Returnwaarde r = convertToPipe(strLijst);
            plattegrond = r.pipes;
            if (r.startX != -1) {
                startX = r.startX;
                startY = r.startY;
            }
        }
        System.out.printf("start = %d,%d", startX, startY);
        System.out.println("plattegrond = " + Arrays.deepToString(plattegrond));

        step = 0;
        step(plattegrond, startX, startY, WR.GEEN);

        System.out.println("step = " + step);
        System.out.println("oplos = " + step / 2);
    }

    private Returnwaarde convertToPipe(List<String> lijst) {
        int x = -1;
        int y = -1;

        Pipe[][] pipes = new Pipe[lijst.size()][];
        for (int i = 0; i < lijst.size(); i++) {
            Pipe[] rij = new Pipe[lijst.get(i).length()];
            for (int j = 0; j < lijst.get(i).length(); j++) {

                char c = lijst.get(i).charAt(j);
                switch (c) {
                    case '|':
                        rij[j] = new Pipe(WR.NOORD, WR.ZUID);
                        break;
                    case '-':
                        rij[j] = new Pipe(WR.OOST, WR.WEST);
                        break;
                    case 'L':
                        rij[j] = new Pipe(WR.NOORD, WR.OOST);
                        break;
                    case 'J':
                        rij[j] = new Pipe(WR.NOORD, WR.WEST);
                        break;
                    case '7':
                        rij[j] = new Pipe(WR.WEST, WR.ZUID);
                        break;
                    case 'F':
                        rij[j] = new Pipe(WR.OOST, WR.ZUID);
                        break;
                    case '.':
                        rij[j] = new Pipe(WR.GEEN, WR.GEEN);
                        break;
                    case 'S':
                        rij[j] = new Pipe(WR.START, WR.START);
                        x = i;
                        y = j;
                        break;
                    default:
                        System.out.println("ERROR = " + c);
                }
            }
            pipes[i] = Arrays.copyOf(rij, rij.length);
        }

        return new Returnwaarde(pipes, x, y);
    }

    private void step(Pipe[][] matrix, int rij, int kolom, WR komtVan) {
        step++;
        System.out.printf("(%d,%d)\n", rij, kolom);
        if (komtVan != WR.GEEN && rij == startX && kolom == startY) {
            // terug bij S
            return;
        }

        // ga naar Zuid

        if (komtVan != WR.ZUID) {
            Pipe p = getMatrix(matrix, rij - 1, kolom);
            WR naar = p.gaNaar(WR.ZUID);
            if (p.begin == WR.START) return;
            if (naar == WR.NOORD) {
                step(matrix, rij - 1, kolom, WR.NOORD);
                return;
            }
            if (naar == WR.OOST) {
                step++;
                step(matrix, rij - 1, kolom + 1, WR.OOST);
                return;
            }
            if (naar == WR.WEST) {
                step++;
                step(matrix, rij - 1, kolom - 1, WR.WEST);
                return;
            }
        }
        if (komtVan != WR.NOORD) {
            Pipe p = getMatrix(matrix, rij + 1, kolom);
            if (p.begin == WR.START) return;
            WR naar = p.gaNaar(WR.NOORD);
            if (naar == WR.OOST) {
                step++;
                step(matrix, rij + 1, kolom + 1, WR.OOST);
                return;
            }
            if (naar == WR.ZUID) {
                step(matrix, rij + 1, kolom, WR.ZUID);
                return;
            }
            if (naar == WR.WEST) {
                step++;
                step(matrix, rij + 1, kolom - 1, WR.WEST);
                return;
            }
        }
        if (komtVan != WR.OOST) {
            Pipe p = getMatrix(matrix, rij, kolom + 1);
            if (p.begin == WR.START) return;
            WR naar = p.gaNaar(WR.WEST);
            if (naar == WR.NOORD) {
                step++;
                step(matrix, rij - 1, kolom + 1, WR.NOORD);
                return;
            }
            if (naar == WR.ZUID) {
                step++;
                step(matrix, rij + 1, kolom + 1, WR.ZUID);
                return;
            }
            if (naar == WR.OOST) {
                step(matrix, rij, kolom + 1, WR.WEST);
                return;
            }
        }
        if (komtVan != WR.WEST) {
            Pipe p = getMatrix(matrix, rij, kolom - 1);
            if (p.begin == WR.START) return;
            WR naar = p.gaNaar(WR.OOST);
            if (naar == WR.NOORD) {
                step++;
                step(matrix, rij - 1, kolom - 1, WR.NOORD);
                return;
            }
            if (naar == WR.ZUID) {
                step++;
                step(matrix, rij + 1, kolom - 1, WR.ZUID);
                return;
            }
            if (naar == WR.WEST) {
                step(matrix, rij, kolom - 1, WR.WEST);
                return;
            }
        }
        System.out.println("Hier gekomen !");
    }
//    private void step(Pipe[][] matrix, int rij, int kolom, WR komtVan) {
//        step++;
//        System.out.printf("(%d,%d)\n", rij, kolom);
//        if (komtVan != WR.GEEN && rij == startX && kolom == startY) {
//            // terug bij S
//            return;
//        }
//
//        if (komtVan != WR.ZUID) {
//            Pipe p = getMatrix(matrix, rij - 1, kolom);
//            WR naar = p.gaNaar(WR.ZUID);
//            if (p.begin == WR.START) return;
//            if (naar == WR.NOORD) {
//                step(matrix, rij - 1, kolom, WR.NOORD);
//                return;
//            }
//            if (naar == WR.OOST) {
//                step++;
//                step(matrix, rij - 1, kolom + 1, WR.OOST);
//                return;
//            }
//            if (naar == WR.WEST) {
//                step++;
//                step(matrix, rij - 1, kolom - 1, WR.WEST);
//                return;
//            }
//        }
//        if (komtVan != WR.NOORD) {
//            Pipe p = getMatrix(matrix, rij + 1, kolom);
//            if (p.begin == WR.START) return;
//            WR naar = p.gaNaar(WR.NOORD);
//            if (naar == WR.OOST) {
//                step++;
//                step(matrix, rij + 1, kolom + 1, WR.OOST);
//                return;
//            }
//            if (naar == WR.ZUID) {
//                step(matrix, rij + 1, kolom, WR.ZUID);
//                return;
//            }
//            if (naar == WR.WEST) {
//                step++;
//                step(matrix, rij + 1, kolom - 1, WR.WEST);
//                return;
//            }
//        }
//        if (komtVan != WR.OOST) {
//            Pipe p = getMatrix(matrix, rij, kolom + 1);
//            if (p.begin == WR.START) return;
//            WR naar = p.gaNaar(WR.WEST);
//            if (naar == WR.NOORD) {
//                step++;
//                step(matrix, rij - 1, kolom + 1, WR.NOORD);
//                return;
//            }
//            if (naar == WR.ZUID) {
//                step++;
//                step(matrix, rij + 1, kolom + 1, WR.ZUID);
//                return;
//            }
//            if (naar == WR.OOST) {
//                step(matrix, rij, kolom + 1, WR.WEST);
//                return;
//            }
//        }
//        if (komtVan != WR.WEST) {
//            Pipe p = getMatrix(matrix, rij, kolom - 1);
//            if (p.begin == WR.START) return;
//            WR naar = p.gaNaar(WR.OOST);
//            if (naar == WR.NOORD) {
//                step++;
//                step(matrix, rij - 1, kolom - 1, WR.NOORD);
//                return;
//            }
//            if (naar == WR.ZUID) {
//                step++;
//                step(matrix, rij + 1, kolom - 1, WR.ZUID);
//                return;
//            }
//            if (naar == WR.WEST) {
//                step(matrix, rij, kolom - 1, WR.WEST);
//                return;
//            }
//        }
//        System.out.println("Hier gekomen !");
//    }

    private static Pipe getMatrix(Pipe[][] matrix, int rij, int kolom) {
        if (rij < matrix.length && kolom < matrix[0].length) return matrix[rij][kolom];
        return new Pipe(WR.GEEN, WR.GEEN);
    }
}

