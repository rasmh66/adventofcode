package puzzels.jaar2022;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class puzzel8 {
    private static final char C_COMMAND = '$';
    public static final int C_GROOT = 100000;

//    public static void mainXXX(String[] args) {
//
////        System.out.println("Start\n");
////        puzzels.TestQue tc = new puzzels.TestQue();
////        tc.puzzels.TestQue();
//        try {
//            puzzels.puzzel8();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    /*
    private static void puzzels.puzzel8() throws IOException {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input/input8.txt";
        File file = new File(fileName);

        StringBuilder sb = new StringBuilder();
        InputStream in = new FileInputStream(file);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            Integer[][] bos = init(br);

            int scenicTeller = 0;
            int maxlijnen = bos.length;
            int maxrij = bos[0].length;
//            boolean visible = false;
            for (int i = 0; i < maxlijnen; i++) {

                for (int j = 0; j < maxrij; j++) {
                    int boom = bos[i][j];

                    int tellerl = bekijkLinks(bos, i, j);
                    int tellerr = bekijkRechts(bos, i, j);
                    int tellerb = bekijkBoven(bos, i, j);
                    int tellero = bekijkOnder(bos, i, j);

                    int tot = tellero*tellerb*tellerr*tellerl;
                    if (tot > scenicTeller) scenicTeller = tot;
                }
            }
            System.out.println("Teller:" + scenicTeller);
        }
    }

    private static int bekijkOnder(Integer[][] bos, int i, int j) {
        if (i == bos.length-1) return 0;
        if (j == bos[i].length-1) return 0;
        int hoogte = bos[i][j];

        int teller = 0;
        for (int k = i+1; k < bos.length; k++) {
            teller++;
            if (bos[k][j] >= hoogte) return teller;
        }
        return teller;
    }

    private static int bekijkBoven(Integer[][] bos, int i, int j) {
        if (i == bos.length-1) return 0;
//        if (j == 0) return true;
        int hoogte = bos[i][j];

        int teller = 0;
        for (int k = i-1; k >= 0; k--) {
            teller++;
            if (bos[k][j] >= hoogte) return teller;
        }
        return teller;
    }

    private static int bekijkRechts(Integer[][] bos, int i, int j) {
//        if (i == 0) return true;
        if (j == bos[i].length-1) return 0;
        int hoogte = bos[i][j];
        int teller = 0;
        for (int k = j+1; k < bos[i].length; k++) {
            teller++;
            if (bos[i][k] >= hoogte) return teller;
        }
        return teller;
    }

    private static int bekijkLinks(Integer[][] bos, int i, int j) {
        if (i == 0) return 0;
        if (j == 0) return 0;
        int hoogte = bos[i][j];

        int teller = 0;
        for (int k = (j-1); k >= 0; k--) {
            teller++;
            if (bos[i][k] >= hoogte) return teller;
        }
        return teller;
    }

    private static void puzzels.puzzel8() throws IOException {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input8.txt";
        File file = new File(fileName);

        StringBuilder sb = new StringBuilder();
        InputStream in = new FileInputStream(file);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            Integer[][] bos = init(br);

            int teller = 0;
            int maxlijnen = bos.length;
            int maxrij = bos[0].length;
            boolean visible = false;
            for (int i = 0; i < maxlijnen; i++) {

                for (int j = 0; j < maxrij; j++) {
                    int boom = bos[i][j];

                    visible = bekijkLinks(bos, i, j);
                    if (!visible) {
                        visible = bekijkRechts(bos, i, j);
                        if (!visible) {
                            visible = bekijkBoven(bos, i, j);
                            if (!visible) {
                                visible = bekijkOnder(bos, i, j);
                            }
                        }
                    }
                    if (visible) teller++;
                    visible = false;
                }
            }
            System.out.println("Teller:" + teller);
        }
    }

    private static boolean bekijkOnder(Integer[][] bos, int i, int j) {
        if (i == bos.length-1) return true;
        if (j == bos[i].length-1) return true;
        int hoogte = bos[i][j];
        for (int k = i+1; k < bos.length; k++) {
            if (bos[k][j] >= hoogte) return false;
        }
        return true;
    }

    private static boolean bekijkBoven(Integer[][] bos, int i, int j) {
        if (i == bos.length-1) return true;
//        if (j == 0) return true;
        int hoogte = bos[i][j];

        for (int k = i-1; k >= 0; k--) {
            if (bos[k][j] >= hoogte) return false;
        }
        return true;
    }

    private static boolean bekijkRechts(Integer[][] bos, int i, int j) {
//        if (i == 0) return true;
        if (j == bos[i].length-1) return true;
        int hoogte = bos[i][j];
        for (int k = j+1; k < bos[i].length; k++) {
            if (bos[i][k] >= hoogte) return false;
        }
        return true;
    }

    private static boolean bekijkLinks(Integer[][] bos, int i, int j) {
        if (i == 0) return true;
        if (j == 0) return true;
        int hoogte = bos[i][j];

        int k;
        for (k = (j-1); k >= 0; k--) {
            if (bos[i][k] >= hoogte) return false;
        }
        return true;
    }

    private static Integer[][] init(BufferedReader br) throws IOException {
        Integer[] bosline;
        List<Integer[]> multiDimArray = new ArrayList<Integer[]>();
        String line;
        int rij = 0;
        while ((line = br.readLine()) != null) {
            int len = line.length();
            bosline = new Integer[len];
            for (int i = 0; i < line.length(); i++) {
                bosline[i] = Integer.parseInt(String.valueOf(line.charAt(i)));
            }
            multiDimArray.add(bosline);
        }
        Integer[][] bos = multiDimArray.toArray(new Integer[multiDimArray.size()][]);
        System.out.println("Bos:");
        multiDimArray.forEach(integers -> System.out.println(Arrays.stream(integers).toList()));

        return bos;
    }
*/
}

