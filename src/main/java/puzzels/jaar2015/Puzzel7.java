package puzzels.jaar2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.System.exit;

public class Puzzel7 {
    private static final int C_JAAR = 2015;
    private static final String C_PUZZELNR = "7_2";

    private Map<String, Map.Entry<e_commando, String[]>> vars = new TreeMap<String, Map.Entry<e_commando, String[]>>();
    static Map<String, Short> waardes = new HashMap<>();

    public enum e_commando {
        E_ASS,
        E_AND,
        E_OR,
        E_RSHIFT,
        E_LSHIFT,
        E_NOT
    }

    public void start() throws IOException {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input/" + C_JAAR + "/input" + C_PUZZELNR + ".txt";


        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            String[] s;
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");

                if (parts[0].equals("#")) {
                    System.out.printf("Comment\n");
                } else if (parts[0].equals("NOT")) {
                    System.out.printf("not: %s %s\n", parts[1], parts[3]);
                    s = new String[1];
                    s[0] = parts[1];
                    vars.put(parts[3], new AbstractMap.SimpleEntry<>(e_commando.E_NOT, s));
                } else {
                    switch (parts[1]) {
                        case "->":
                            System.out.printf("->: %s %s\n", parts[0], parts[2]);
                            s = new String[1];
                            s[0] = parts[0];
                            vars.put(parts[2], new AbstractMap.SimpleEntry<>(e_commando.E_ASS, s));
                            break;
                        case "AND":
                            System.out.printf("and: %s %s o:%s\n", parts[0], parts[2], parts[4]);
                            s = new String[2];
                            s[0] = parts[0];
                            s[1] = parts[2];
                            vars.put(parts[4], new AbstractMap.SimpleEntry<>(e_commando.E_AND, s));
                            break;
                        case "OR":
                            System.out.printf("or: %s %s o:%s\n", parts[0], parts[2], parts[4]);
                            s = new String[2];
                            s[0] = parts[0];
                            s[1] = parts[2];
                            vars.put(parts[4], new AbstractMap.SimpleEntry<>(e_commando.E_OR, s));
                            break;
                        case "RSHIFT":
                            System.out.printf("rs: %s %s o:%s\n", parts[0], parts[2], parts[4]);
                            s = new String[2];
                            s[0] = parts[0];
                            s[1] = parts[2];
                            vars.put(parts[4], new AbstractMap.SimpleEntry<>(e_commando.E_RSHIFT, s));
                            break;
                        case "LSHIFT":
                            System.out.printf("ls: %s %s o:%s\n", parts[0], parts[2], parts[4]);
                            s = new String[2];
                            s[0] = parts[0];
                            s[1] = parts[2];
                            vars.put(parts[4], new AbstractMap.SimpleEntry<>(e_commando.E_LSHIFT, s));
                            break;
                        default:
                            System.out.println("ERROR onbekend commando");
                            break;
                    }
                }
            }
            System.out.println("Vars:");
            for (String key :
                    vars.keySet()) {
                AbstractMap.SimpleEntry w = (AbstractMap.SimpleEntry) vars.get(key);
                System.out.printf("k:%s cmd:%s \n", key, w.getKey().toString());
            }

            Short d = handleCmd("a");
            System.out.printf("A: %d\n", d);

//            waardes.clear();
//            waardes.put("b", d);
//
//            Short d1 = handleCmd("a");
            Short d1 = d;
            System.out.printf("A1: %d\n", d1 & 0xffff);
            System.out.println(Integer.toHexString(d1 ));

        }
    }

    private Short handleCmd(String key) {

        Short waarde = -1;

        if (Character.isDigit(key.charAt(0))) {
            waarde = Short.parseShort(key);
            return waarde;
        }
        if (waardes.containsKey(key)) {
            return waardes.get(key);
        }

        AbstractMap.SimpleEntry w = searchKey(key);
        if (w == null) {
            System.out.printf("NULL: %s\n", key);
            exit(1);
        }

        e_commando cmd = (e_commando) w.getKey();
        String[] params = (String[]) w.getValue();
//        System.out.printf("cmd: %s %s\n", cmd.toString(), params[0]);

        switch (cmd) {
            case E_ASS:
//                System.out.printf("->: %s\n", params[0]);
                waarde = handleCmd(params[0]);
                break;
            case E_NOT:
//                System.out.printf("not: %s\n", params[0]);
                waarde = (short) (~handleCmd(params[0] ) & 0xffff);
                break;
            case E_AND:
//                System.out.printf("and: %s %s\n", params[0], params[1]);
                waarde = (short) (handleCmd(params[0]) & handleCmd(params[1]));
                break;
            case E_OR:
//                System.out.printf("or: %s\n", params[0]);
                waarde = (short) (handleCmd(params[0]) | handleCmd(params[1]));
                break;
            case E_RSHIFT:
//                System.out.printf("rs: %s\n", params[0]);
                waarde = (short) (handleCmd(params[0]) >> handleCmd(params[1]));
                break;
            case E_LSHIFT:
//                System.out.printf("ls: %s\n", params[0]);
                waarde = (short) (handleCmd(params[0]) << handleCmd(params[1]));
                break;
            default:
                System.out.println("ERROR onbekend commando");
                break;
        }
        System.out.printf("waarde: %d\n", waarde);
        waardes.put(key, waarde);
        return waarde;
    }


    private AbstractMap.SimpleEntry searchKey(String searchKey) {

        AbstractMap.SimpleEntry w = null;
        for (String key :
                vars.keySet()) {
            if (key.equals(searchKey)) {
                w = (AbstractMap.SimpleEntry) vars.get(key);
            }
//            System.out.printf("k:%s cmd:%s \n", key, w.getKey().toString());
        }
        return w;
    }
}
