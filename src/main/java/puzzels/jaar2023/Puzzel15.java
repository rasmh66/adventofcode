package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Puzzel15 extends abstractPuzzel {

    public Puzzel15(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        long power = 0;
        List<Map<String, Integer>> boxes = new ArrayList<>(256);
        for (int i = 0; i < 256; i++) {
            Map<String, Integer> lens = new LinkedHashMap<String, Integer>();
            boxes.add(i, lens);
        }

        Scanner sc = new Scanner(new File(fileName));
//        Scanner sc = new Scanner(new String("rn,cm"));
        sc.useDelimiter(",");

        while (sc.hasNext()) {
            String woord = sc.next();
//            System.out.println("woord = " + woord);

            String[] s = woord.split("[-=]");
            String label = s[0];
            int focus = 0;
            if (!woord.contains("-")) {
                focus = Integer.parseInt(s[1]);
            }
            int woordHash = getWoordHash(label);

            Map<String, Integer> lens = null;

            if (woord.contains("-")) {
                if (boxes.size() > woordHash) {
                    lens = boxes.get(woordHash);
                    if (lens.containsKey(label)) {
                        lens.remove(s[0]);
                    }
                }
            } else if (woord.contains("=")) {
                if (boxes.size() > woordHash) {
                    lens = boxes.get(woordHash);
                    if (lens.containsKey(label)) {
                        lens.replace(label, Integer.parseInt(s[1]));
                    } else {
                        lens.put(label, focus);
                    }
                } else {
                    lens = new LinkedHashMap<String, Integer>();
                    lens.put(label, focus);
                    boxes.add(woordHash, lens);
                }
            } else throw new RuntimeException("Fout woord");
        }
//        System.out.println("totalHash = " + totalHash);
        System.out.println("boxes = " + boxes);
        for (int i = 0; i < 256; i++) {
            Map<String, Integer> lens = boxes.get(i);
            power += berekenPower(lens, i + 1);
        }
        System.out.println("power = " + power);
    }

    private long berekenPower(Map<String, Integer> lens, int boxposition) {
        long result = 0;
        if (lens.size() == 0) return 0;
        int lensPos = 1;
        for (Integer focus :
                lens.values()) {
            result += (long) boxposition * lensPos++ * focus;
//            System.out.printf("%d %d %d ", boxposition, lensPos, focus);
        }
//        System.out.println(" result = " + result);
        return result;
    }

    private int getWoordHash(String woord) {
        int woordHash = 0;
        for (char c :
                woord.toCharArray()) {
            woordHash = berekenHash(woordHash, c);
        }
        System.out.println("Index = " + woordHash);
        return woordHash;
    }

    private int berekenHash(int total, char c) {
        int hash = total;
//        System.out.println("hash = " + hash);
        hash += c;
        hash *= 17;
        hash = hash % 256;
//        System.out.println("hash = " + hash);
        return hash;
    }
}

