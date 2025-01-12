package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Puzzel5 extends abstractPuzzel {

    public Puzzel5(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        long[] seednrs = null;
        long uitkomst = Long.MAX_VALUE;
        List<long[]> soilnrs = new ArrayList<long[]>();
        List<long[]> fertnrs = new ArrayList<long[]>();
        List<long[]> waternrs = new ArrayList<long[]>();
        List<long[]> lightnrs = new ArrayList<long[]>();
        List<long[]> tempnrs = new ArrayList<long[]>();
        List<long[]> humidnrs = new ArrayList<long[]>();
        List<long[]> locnrs = new ArrayList<long[]>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] deelStr = line.split(":");

                switch (deelStr[0].trim()) {
                    case "seeds":
                        seednrs = readLong(deelStr[1].trim());
                        break;
                    case "seed-to-soil map":
                        while (!(line = br.readLine()).isEmpty()) {
//                            System.out.println(line);
                            soilnrs.add(readLong(line));
                        }
                        break;
                    case "soil-to-fertilizer map":
                        while (!(line = br.readLine()).isEmpty()) {
//                            System.out.println(line);
                            fertnrs.add(readLong(line));
                        }
                        break;
                    case "fertilizer-to-water map":
                        while (!(line = br.readLine()).isEmpty()) {
//                            System.out.println(line);
                            waternrs.add(readLong(line));
                        }
                        break;
                    case "water-to-light map":
                        while (!(line = br.readLine()).isEmpty()) {
//                            System.out.println(line);
                            lightnrs.add(readLong(line));
                        }
                        break;
                    case "light-to-temperature map":
                        while (!(line = br.readLine()).isEmpty()) {
//                            System.out.println(line);
                            tempnrs.add(readLong(line));
                        }
                        break;
                    case "temperature-to-humidity map":
                        while (!(line = br.readLine()).isEmpty()) {
//                            System.out.println(line);
                            humidnrs.add(readLong(line));
                        }
                        break;
                    case "humidity-to-location map":
                        while (!(line = br.readLine()).isEmpty()) {
//                            System.out.println(line);
                            locnrs.add(readLong(line));
                        }
                        break;
                    case "":
                        break;
                    default:
                        System.out.println("Onbekende commando");
                }
            }
        }
        for (int i = 0; i < seednrs.length; i += 2) {
            uitkomst = getUitkomst(seednrs, i + 1, i, soilnrs, fertnrs, waternrs, lightnrs, tempnrs, humidnrs, locnrs, uitkomst);
        }
        System.out.println("uitkomst = " + uitkomst);
    }

    private long getUitkomst(long[] seednrs, int x, int x1, List<long[]> soilnrs, List<long[]> fertnrs, List<long[]> waternrs, List<long[]> lightnrs, List<long[]> tempnrs, List<long[]> humidnrs, List<long[]> locnrs, long uitkomst) {
        for (long seed = 0; seed < seednrs[x]; seed++) {
            long soil = bepaalVolgende(seed + seednrs[x1], soilnrs);
            long fert = bepaalVolgende(soil, fertnrs);
            long water = bepaalVolgende(fert, waternrs);
            long light = bepaalVolgende(water, lightnrs);
            long temp = bepaalVolgende(light, tempnrs);
            long hunid = bepaalVolgende(temp, humidnrs);
            long loc = bepaalVolgende(hunid, locnrs);

//            System.out.println("loc = " + loc);
            uitkomst = Math.min(uitkomst, loc);
        }
        return uitkomst;
    }

    private long bepaalVolgende(long input, List<long[]> list) {
        for (long[] rij :
                list) {
            long l = bepaalDest(input, rij[1], rij[0], rij[2]);
            if (l != -1) return l;
        }
        return input;
    }

    private long bepaalDest(long input, long src, long dest, long range) {
        long res = -1;

        if (input >= src && input <= src + range) {
            long len = input - src;
            res = dest + len;
        }
        ;

        return res;
    }
}

