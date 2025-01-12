package puzzels.jaar2022;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzel13 {

    public static final char RHAAK = ']';
    public static final char LHAAK = '[';
    public static final char COMMA = ',';

    private static class Packet {


        private enum E_TYPE {INT, LIST, LEEG}

        E_TYPE type = null;
        private Integer intValue;
        private final List<Packet> packetValues = new ArrayList<Packet>();

        public Packet(E_TYPE eType) {
            type = eType;
        }

        public void addPacket(Packet packet) {
            packetValues.add(packet);
        }

        public void addInteger(Integer value) {
            intValue = value;
            type = E_TYPE.INT;
        }

        public E_TYPE getType() {
            return type;
        }

        public int getLengte() {
            if (type == E_TYPE.LEEG) return 0;
            if (type == E_TYPE.INT) return 1;
            if (type == E_TYPE.LIST) return packetValues.size();
            return -1;
        }

        public Integer getIntValue() {
            return intValue;
        }

        public List<Packet> getPacketValues() {
            return packetValues;
        }

        @Override
        public String toString() {
            if (type == E_TYPE.LEEG) return "LEEG";
            if (type == E_TYPE.INT) return "i:" + intValue;

            return "p=" + Arrays.toString(packetValues.toArray());
        }
    }

    private Packet leesPacket(String str, Packet pac) throws ParseException {
        String subStr = str;
        int pos = 0;
        do {
            if (subStr.charAt(pos) == LHAAK) {
                if (subStr.charAt(pos + 1) == RHAAK) {
                    Packet newpac = new Packet(Packet.E_TYPE.LEEG);
                    if (pac == null) {
                        pac = newpac;
                    } else {
                        pac.addPacket(newpac);
                    }
                    pos++;
                    pos++;
                } else {
                    Packet newpac = new Packet(Packet.E_TYPE.LIST);
                    newpac = leesPacket(subStr.substring(pos + 1, searchEndHaak(subStr, pos + 1)), newpac);
                    if (pac == null) {
                        pac = newpac;
                    } else {
                        pac.addPacket(newpac);
                    }
                    subStr = subStr.substring(searchEndHaak(subStr, pos + 1) + 1);
                    pos = 0;

                    if (subStr.length() > 0 && subStr.charAt(pos) == COMMA) {
                        pos++;
                    }
                }
            } else if (subStr.charAt(pos) == RHAAK) {
                return pac;
            } else {
                if (subStr.charAt(pos) == COMMA) {
                    pos++;
                } else {
                    Pair pair = returnInteger(subStr, pos);
//                    int len = pair.offset;

                    Packet newpac = new Packet(Packet.E_TYPE.INT);
                    newpac.addInteger(pair.valueInt());
                    pac.addPacket(newpac);

                    if (pair.offset() == -1) {
                        pos = subStr.length();
//                        System.out.printf("\n ERROR: pair leeg:%s\n", subStr);
//                        System.exit(1);
                    } else {
                        pos= pair.offset();
                    }
                }
            }
        } while (pos < subStr.length());
        return pac;
    }

    private int searchEndHaak(String param, int startPos) {
        boolean gevonden = false;
        int pos = startPos;
        int diep = 0;
        do {
            if (param.charAt(pos) == RHAAK) {
                if (diep == 0) {
                    gevonden = true;
                } else diep--;
            } else if (param.charAt(pos) == LHAAK) {
                diep++;
            }
            pos++;
        } while (!gevonden);

        return pos - 1;
    }

    private Pair returnInteger(String s, int startPos) {
        int offset = -1;

        if (s == null)
            return null;
        else {
            char[] characters = s.toCharArray();
            Integer value = null;
            boolean isPrevDigit = false;
            for (int i = startPos; i < characters.length; i++) {
                if (isPrevDigit == false) {
                    if (Character.isDigit(characters[i])) {
                        isPrevDigit = true;
                        value = Character.getNumericValue(characters[i]);
                    }
                } else {
                    if (Character.isDigit(characters[i])) {
                        value = (value * 10) + Character.getNumericValue(characters[i]);
                    } else {
                        offset = i;
                        break;
                    }
                }
            }
            Pair p = new Pair(value, offset);
            return p;
        }
    }

    public void start() throws Exception {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input/2022/input13.txt"; // 3277
        File file = new File(fileName);

        InputStream in = new FileInputStream(file);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {

            String firstline;
            String secLine;
            int row = 1;
            int som = 0;
            while ((firstline = br.readLine()) != null) {
                secLine = br.readLine();
                String emptyLine = br.readLine();

                Packet pacFirst = leesPacket(firstline, null);
                Packet pacSec = leesPacket(secLine, null);

                System.out.printf("First : %s\n", pacFirst.toString());
                System.out.printf("Second: %s\n", pacSec.toString());

                Boolean right = bepaalRight(pacFirst, pacSec);
                if (right == null) right = true;

                if (right) {
                    som += row;
                    System.out.printf("######## Goed: %d\n", row);
                } else {
                    System.out.printf("######## Fout: %d\n", row);
                }
                row++;
            }
            System.out.printf("\nSom: %d\n", som);
        }
    }

    private Boolean bepaalRight(Packet pacFirst, Packet pacSec) {
        Boolean goed = null;
        int firstLengte = pacFirst.getLengte();
        int secLengte = pacSec.getLengte();

        Packet.E_TYPE firstType = pacFirst.getType();
        Packet.E_TYPE secType = pacSec.getType();

        for (int i = 0; i < firstLengte; i++) {
            if (i >= secLengte) return false;

            if (firstType == Packet.E_TYPE.INT && secType == Packet.E_TYPE.LIST) {
                pacFirst = convertToList(pacFirst);
                firstType = pacFirst.getType();
            }
            if (secType == Packet.E_TYPE.INT && firstType == Packet.E_TYPE.LIST) {
                pacSec = convertToList(pacSec);
                secType = pacSec.getType();
            }

            if (secType == Packet.E_TYPE.INT && firstType == Packet.E_TYPE.INT) {
                if (pacFirst.getIntValue() > pacSec.getIntValue()) {
                    goed = false;
                } else if (pacFirst.getIntValue() < pacSec.getIntValue()) {
                    goed = true;
                }
                System.out.printf("g:%s %d %d\t", goed != null ? goed.toString() : null, pacFirst.getIntValue(), pacSec.getIntValue());
            } else if (secType == Packet.E_TYPE.LIST && firstType == Packet.E_TYPE.LIST) {
                goed = bepaalRight(pacFirst.packetValues.get(i), pacSec.packetValues.get(i));
                if (goed != null) break;
            }
        }

        return goed;
    }

    private Packet convertToList(Packet pacFirst) {
        Packet newpac = new Packet(Packet.E_TYPE.LIST);
        newpac.addPacket(pacFirst);
        return newpac;
    }

    private record Pair(Integer valueInt, int offset) {
    }
}
