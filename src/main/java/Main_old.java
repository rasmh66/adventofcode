import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


//public class Main_old {
//    public static void main(String[] args) {
//
////        System.out.println("Start\n");
////        puzzels.TestQue tc = new puzzels.TestQue();
////        tc.puzzels.TestQue();
//
//        try {
//            puzzel5a();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    private static void puzzel7() throws Exception {
//        String dir = System.getProperty("user.dir");
//        String fileName = dir + "/input7.txt";
//        File file = new File(fileName);
//
//        StringBuilder sb = new StringBuilder();
//        InputStream in = new FileInputStream(file);
//        int totFilesystem = 70000000;
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
//
//            String line;
//            puzzels.Node<puzzels.Iod> hoofdNode = new puzzels.Node<puzzels.Iod>(new puzzels.Iod("main", puzzels.typeIod.D, 0));
//            puzzels.Node<puzzels.Iod> currentNode = hoofdNode;
//            while ((line = br.readLine()) != null) {
//
//                if (line.charAt(0) == C_COMMAND) {
////                    System.out.println("Line: "+ line);
//                    String cmd = line.substring(2, 4);
//                    String parameter = line.substring(4).trim();
////                    System.out.printf("Split: %s '%s'%n", cmd, parameter);
//
//                    switch (cmd) {
//                        case "cd":
//                            if (parameter.equals("/")) {
//                                currentNode = hoofdNode;
//                            } else if (parameter.equals("..")) {
//                                if (!currentNode.isRoot()) {
//                                    currentNode = currentNode.getParent();
//                                }
//                            } else {
//                                for (puzzels.Node<puzzels.Iod> subnode : currentNode.getChildren()) {
//                                    if (parameter.equals(subnode.getData().getNaam())) {
//                                        currentNode = subnode;
//                                    }
//                                }
//                            }
//                            break;
//                        case "ls":
//                            break;
//                        default:
//                            throw new Exception("Onbekend commando");
//                    }
//                } else {
//                    String[] waarden = line.split(" ");
//                    if (waarden[0].equals("dir")) {
//                        puzzels.Node<puzzels.Iod> subdir = new puzzels.Node<>(new puzzels.Iod(waarden[1], puzzels.typeIod.D, 0));
//                        currentNode.addChild(subdir);
//                    } else {
//                        Integer i = Integer.parseInt(waarden[0]);
//                        String bestandsnaam = waarden[1];
//                        puzzels.Node<puzzels.Iod> subfile = new puzzels.Node<>(new puzzels.Iod(bestandsnaam, puzzels.typeIod.F, i));
//                        currentNode.addChild(subfile);
//
//                        currentNode.verhoog(i);
////                        currentNode.setData(dat);
//                    }
//                }
//                System.out.printf("puzzels.Node: %s%n", currentNode);
//            }
//
//            System.out.println("########### Tree nodes:");
//            hoofdNode.printNode(1);
//            int deleteSpace = 30000000 - (totFilesystem - hoofdNode.getData().getGrote());
//            System.out.println("Delete: " + deleteSpace);
//
//            recursive(hoofdNode, deleteSpace);
//            Collections.sort(nodeToDelete);
//            System.out.println("Total: " + nodeToDelete);
//
//        }
//        in.close();
//    }
//
//    static int totalSize = 0;
//    static List<Integer> nodeToDelete = new ArrayList<>();
//
//    public static void recursive(puzzels.Node<puzzels.Iod> nodeParam, int deleteSpace) {
//
//        int groot = nodeParam.getData().getGrote();
//        puzzels.typeIod type = nodeParam.getData().getType();
//        if (groot > deleteSpace && type == puzzels.typeIod.D) nodeToDelete.add(groot);
//        // loop door alle nodes
//        for (puzzels.Node<puzzels.Iod> child :
//                nodeParam.getChildren()) {
//            recursive(child, deleteSpace);
//        }
////        return totalSize;
//    }
//
//    private static void puzzel6() throws Exception {
//        String dir = System.getProperty("user.dir");
//        String fileName = dir + "/input6.txt";
//        File file = new File(fileName);
//
//        StringBuilder sb = new StringBuilder();
//        InputStream in = new FileInputStream(file);
//        int pos;
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
//
//            int line;
//            pos = 0;
//            Queue<Character> compare = new LinkedList<Character>();
//            while ((line = br.read()) != -1) {
//                char a = (char) line;
//                pos++;
//                compare.add(a);
//
//                if (compare.size() > 13) {
//                    Object[] lijst = compare.toArray();
//                    System.out.println("lll:"+ Arrays.toString(lijst));
//
//                    if(lijst.length != 14) throw new Exception("Size array fout");
//
//                    if(checkLijst(lijst))
//                    {
//                        break;
//                    }
//                    compare.remove();
//                }
//                System.out.printf("c %d:%s%n", pos, compare);
//            }
//        }
//        in.close();
//
//        System.out.println("pos:"+pos);
//    }
//
//    private static boolean checkLijst(Object[] lijst) {
//        for (int j = 0; j < lijst.length; j++) {
//            for (int i = 0; i < lijst.length; i++) {
//                if(j!=i) {
//                    if(lijst[j] == lijst[i]) {
//                        return false;
//                    }
//                }
//            }
//        }
//        return true;
//    }
//
//    private static void puzzel5a() throws IOException {
//        String dir = System.getProperty("user.dir");
//        String fileName = dir + "/input5.txt";
//
//        ArrayList<LinkedList<Character>> lists = initFifo();
//        System.out.println("Begin:" + lists);
//        var lijst = lists.toArray();
//
//        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] sections = line.split(" ");
//                System.out.println("Line's:" + line);
////                System.out.println("Sec:" + sections[1]+" "+sections[3]+" "+sections[5]);
//
//                int aantal = Integer.parseInt(sections[1]);
//                int van = Integer.parseInt(sections[3]) - 1;
//                int naar = Integer.parseInt(sections[5]) -1;
//
//                LinkedList<Character> vvv = (LinkedList<Character>) lijst[van];
//                LinkedList<Character> nnn = (LinkedList<Character>) lijst[naar];
////                System.out.println("vvv:" + vvv);
////                System.out.println("nnn:" + nnn);
//
//                List<Character> col = new ArrayList<>();
//                for (int i = 0; i < aantal; i++) {
//                    var vanchar = vvv.remove();
//                    col.add(vanchar);
//                }
//                Collections.reverse(col);
//                for (Character a: col) {
//                    nnn.add(0, (a));
//                }
////                System.out.println("nnn:" + nnn);
////                System.out.println("New:" + lists.get(van));
////                System.out.println("New:" + lists.get(naar));
//
////                break;
//            }
//        }
//        System.out.println("End:" + lists);
//        for (LinkedList<Character> q :
//                lists) {
//            System.out.print(q.peekFirst());
//        }
//    }
//
//    private static ArrayList<LinkedList<Character>> initFifo() {
//        char arr[][] = new char[][]{
//                {'N','R','J','T','Z','B','D','F'},
//                {'H','J','N','S','R'},
//                {'Q','F','Z','G','J','N','R','C'},
//                {'Q','T','R','G','N','V','F'},
//                {'F','Q','T','L'},
//                {'N','G','R','B','Z','W','C','Q'},
//                {'M','H','N','S','L','C','F'},
//                {'J','T','M','Q','N','D'},
//                {'S','G','P'}
//        };
//        ArrayList<LinkedList<Character>> lijst = new ArrayList<LinkedList<Character>>();
////        ArrayList<Deque<Character>> lijst = new ArrayList<>();
//
//        for (int i = 0; i < 9; i++) {
//            LinkedList<Character> fifo = new LinkedList<>();
//            for (char c : arr[i]) fifo.add(c);
//            lijst.add(fifo);
////            System.out.println(fifo);
//        }
//        return lijst;


/*
[N]     [Q]         [N]
[R]     [F] [Q]     [G] [M]
[J]     [Z] [T]     [R] [H] [J]
[T] [H] [G] [R]     [B] [N] [T]
[Z] [J] [J] [G] [F] [Z] [S] [M]
[B] [N] [N] [N] [Q] [W] [L] [Q] [S]
[D] [S] [R] [V] [T] [C] [C] [N] [G]
[F] [R] [C] [F] [L] [Q] [F] [D] [P]
 1   2   3   4   5   6   7   8   9
 */

//    }


//    private static void puzzel5() throws IOException {
//        String dir = System.getProperty("user.dir");
//        String fileName = dir + "/input5.txt";
//
//        ArrayList<Deque<Character>> lists = initFifo();
//        System.out.println("Begin:" + lists);
//        var lijst = lists.toArray();
//
//        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] sections = line.split(" ");
//                System.out.println("Line's:" + line);
////                System.out.println("Sec:" + sections[1]+" "+sections[3]+" "+sections[5]);
//
//                int aantal = Integer.parseInt(sections[1]);
//                int van = Integer.parseInt(sections[3]) - 1;
//                int naar = Integer.parseInt(sections[5]) -1;
//
//                Deque<Character> vvv = (Deque<Character>) lijst[van];
//                Deque<Character> nnn = (Deque<Character>) lijst[naar];
//                System.out.println("vvv:" + vvv);
//                System.out.println("nnn:" + nnn);
//
//                for (int i = 0; i < aantal; i++) {
//                    var vanchar = vvv.remove();
////                    System.out.println("Van:" + vanchar);
////                    var arr = lists.get(naar);
//                    nnn.addFirst(vanchar);
//                }
////                System.out.println("nnn:" + nnn);
//                System.out.println("New:" + lists.get(van));
//                System.out.println("New:" + lists.get(naar));
//
//            }
//        }
//        System.out.println("End:" + lists);
//        for (Deque<Character> q :
//                lists) {
//            System.out.print(q.peekFirst());
//        }
//    }

//    private static ArrayList<Deque<Character>> initFifo() {
//        char arr[][] = new char[][]{
//                {'N','R','J','T','Z','B','D','F'},
//                {'H','J','N','S','R'},
//                {'Q','F','Z','G','J','N','R','C'},
//                {'Q','T','R','G','N','V','F'},
//                {'F','Q','T','L'},
//                {'N','G','R','B','Z','W','C','Q'},
//                {'M','H','N','S','L','C','F'},
//                {'J','T','M','Q','N','D'},
//                {'S','G','P'}
//        };
////        ArrayList<LinkedList<Character>> lijst = new ArrayList<LinkedList<Character>>();
//        ArrayList<Deque<Character>> lijst = new ArrayList<>();
//
//        for (int i = 0; i < 9; i++) {
//            Deque<Character> fifo = new LinkedList<>();
//            for (char c : arr[i]) fifo.add(c);
//            lijst.add(fifo);
////            System.out.println(fifo);
//        }
//    return lijst;
///*
//[N]     [Q]         [N]
//[R]     [F] [Q]     [G] [M]
//[J]     [Z] [T]     [R] [H] [J]
//[T] [H] [G] [R]     [B] [N] [T]
//[Z] [J] [J] [G] [F] [Z] [S] [M]
//[B] [N] [N] [N] [Q] [W] [L] [Q] [S]
//[D] [S] [R] [V] [T] [C] [C] [N] [G]
//[F] [R] [C] [F] [L] [Q] [F] [D] [P]
// 1   2   3   4   5   6   7   8   9
// */
//
//    }


//    public record ids(int first, int second) {}
//
//    private static void puzzel4() throws IOException {
//
//
//        String dir = System.getProperty("user.dir");
//        String fileName = dir + "/input4.txt";
//        int totaal = 0;
//
//        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] sections = line.split(",");
//                System.out.println("Line's:" + sections[0]+" "+sections[1]);
//
//                String[] ids1 = sections[0].split("-");
//                String[] ids2 = sections[1].split("-");
//
//                int id1 = Integer.parseInt(ids1[0]);
//                int id2 = Integer.parseInt(ids1[1]);
//                int id3 = Integer.parseInt(ids2[0]);
//                int id4 = Integer.parseInt(ids2[1]);
//
////                System.out.printf("Id1:%d ", id1);
////                System.out.printf("Id2:%d ", id2);
////                System.out.printf("Id3:%d ", id3);
////                System.out.printf("Id4:%d%n", id4);
//                if (id1 <= id3 && id2 >= id4) {
//                    totaal++;
//                } else if (id3 <= id1 && id4 >= id2) {
//                    totaal++;
//                }
////                System.out.println("Totaal:" + totaal);
//            }
//        }
//        System.out.println("Totaal:" + totaal);
//    }
//    private static void puzzel4a() throws IOException {
//
//        String dir = System.getProperty("user.dir");
//        String fileName = dir + "/input4.txt";
//        int totaal = 0;
//
//        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] sections = line.split(",");
//                System.out.println("Line's:" + sections[0]+" "+sections[1]);
//
//                String[] ids1 = sections[0].split("-");
//                String[] ids2 = sections[1].split("-");
//
//                int id1 = Integer.parseInt(ids1[0]);
//                int id2 = Integer.parseInt(ids1[1]);
//
//                int id3 = Integer.parseInt(ids2[0]);
//                int id4 = Integer.parseInt(ids2[1]);
//
////                System.out.printf("Id1:%d ", id1);
////                System.out.printf("Id2:%d ", id2);
////                System.out.printf("Id3:%d ", id3);
////                System.out.printf("Id4:%d%n", id4);
//                if (id1 <= id3 && id2 >= id4) {
//                    totaal++;
//                } else if (id3 <= id1 && id4 >= id2) {
//                    totaal++;
//                } else if (id2 >= id3 && id1 <= id4) {
//                    totaal++;
//                }
//                System.out.println("Totaal:" + totaal);
//            }
//        }
//        System.out.println("Totaal:" + totaal);
//    }
//
//    private static void puzzel3a() throws IOException {
//        String dir = System.getProperty("user.dir");
//        String fileName = dir + "/input3.txt";
//        int totaal = 0;
//
//        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
//            String firstline;
//            while ((firstline = br.readLine()) != null) {
//                String secondline = br.readLine();
//                String thirdline = br.readLine();
//
//                System.out.println("Line's:" + firstline+" "+secondline);
//
//                int prio = 0;
//                for (Character c: firstline.toCharArray()) {
//                    int pos1 = secondline.indexOf(c);
//                    int pos2 = thirdline.indexOf(c);
//                    if (pos1 != -1 && pos2 != -1) {
//                        if (Character.isLowerCase(c)) {
//                            prio = c - 'a' + 1;
//                        } else {
//                            prio = (c - 'A') + 27;
//                        }
//                        System.out.println(" c:" + c + " " + prio);
//                        break;
//                    }
//                }
//                totaal += prio;
//            }
//        }
//        System.out.println("Totaal:" + totaal);
//    }
//
//    private static void puzzel3() throws IOException {
//        String dir = System.getProperty("user.dir");
//        String fileName = dir + "/input3.txt";
//        int totaal = 0;
//
//        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String first = line.substring(0, line.length()/2);
//                String second = line.substring(line.length()/2);
//                System.out.print("Word's:" + first+" "+second);
//
//                int prio = 0;
//                for (Character c: first.toCharArray()) {
//                    int prioPos = second.indexOf(c);
//                    if (prioPos != -1) {
//                        if (Character.isLowerCase(c)) {
//                            prio = c - 'a' + 1;
//                        } else {
//                            prio = (c - 'A') + 27;
//                        }
//                        System.out.println(" c:" + c + " " + prio);
//                        break;
//                    }
//                }
//                totaal += prio;
//            }
//        }
//        System.out.println("Totaal:" + totaal);
//    }
//*/
//
///*
//    private static void puzzel2() throws IOException {
//        String dir = System.getProperty("user.dir");
//        String fileName = dir + "/input2.txt";
//        int totaal = 0;
//
//        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
////                String[] ch = line.split(" ");
//                char[] ch = line.toCharArray();
//                System.out.println("ch's:" + ch[0]+" "+ch[2]);
//
//                var result = switch (ch[0]) {
//                    case 'A' -> {
//                        if (ch[2] == 'X') {
//                            yield 1+3;
//                        } else if (ch[2] == 'Y') {
//                            yield 2+6;
//                        } else if (ch[2] == 'Z') {
//                            yield 3+0;
//                        } else {
//                            System.out.println("Error 1");
//                            yield 0;
//                        }
//                    }
//                    case 'B' -> {
//                        if (ch[2] == 'X') {
//                            yield 1+0;
//                        } else if (ch[2] == 'Y') {
//                            yield 2+3;
//                        } else if (ch[2] == 'Z') {
//                            yield 3+6;
//                        } else {
//                            System.out.println("Error 2");
//                            yield 0;
//                        }
//                    }
//                    case 'C' -> {
//                        if (ch[2] == 'X') {
//                            yield 1+6;
//                        } else if (ch[2] == 'Y') {
//                            yield 2+0;
//                        } else if (ch[2] == 'Z') {
//                            yield 3+3;
//                        } else {
//                            System.out.println("Error 3");
//                            yield 0;
//                        }
//                    }
//                    default -> 0;
//                };
//                totaal += result;
//            }
//        }
//        System.out.println("Totaal:" + totaal);
//    }
//    private static void puzzel2a() throws IOException {
//        String dir = System.getProperty("user.dir");
//        String fileName = dir + "/input2.txt";
//        int totaal = 0;
//
//        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
////                String[] ch = line.split(" ");
//                char[] ch = line.toCharArray();
//                System.out.println("ch's:" + ch[0]+" "+ch[2]);
//
//                var result = switch (ch[0]) {
//                    case 'A' -> {
//                        if (ch[2] == 'X') {
//                            yield 3+0;
//                        } else if (ch[2] == 'Y') {
//                            yield 1+3;
//                        } else if (ch[2] == 'Z') {
//                            yield 2+6;
//                        } else {
//                            System.out.println("Error 1");
//                            yield 0;
//                        }
//                    }
//                    case 'B' -> {
//                        if (ch[2] == 'X') {
//                            yield 1+0;
//                        } else if (ch[2] == 'Y') {
//                            yield 2+3;
//                        } else if (ch[2] == 'Z') {
//                            yield 3+6;
//                        } else {
//                            System.out.println("Error 2");
//                            yield 0;
//                        }
//                    }
//                    case 'C' -> {
//                        if (ch[2] == 'X') {
//                            yield 2+0;
//                        } else if (ch[2] == 'Y') {
//                            yield 3+3;
//                        } else if (ch[2] == 'Z') {
//                            yield 1+6;
//                        } else {
//                            System.out.println("Error 3");
//                            yield 0;
//                        }
//                    }
//                    default -> 0;
//                };
//                totaal += result;
//            }
//        }
//        System.out.println("Totaal:" + totaal);
//    }
//
//    private static void puzzel1() throws IOException {
//        int maxElf = 0;
//
//        String dir = System.getProperty("user.dir");
//        String fileName = dir + "/input1.txt";
//        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
//            String line;
//            int totaalElf = 0;
//            while ((line = br.readLine()) != null) {
//                System.out.println("Line:"+ line);
//
//                if (!line.isEmpty()) {
//                    int i = Integer.parseInt(line);
//                    totaalElf += i;
//                } else {
//                    System.out.println("Totaal elf:"+ totaalElf);
//                    if (totaalElf>maxElf) {
//                        maxElf = totaalElf;
//                    }
//                    totaalElf = 0;
//                }
//            }
//        }
//        System.out.println("Max:"+ maxElf); // 69501
//    }
//
//    private static void puzzel1a() throws IOException {
//        List<Integer> elfList = new ArrayList<>();
//
//        String dir = System.getProperty("user.dir");
//        String fileName = dir + "/input1.txt";
//        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
//            String line;
//            int totaalElf = 0;
//            while ((line = br.readLine()) != null) {
////                System.out.println("Line:"+ line);
//
//                if (!line.isEmpty()) {
//                    int i = Integer.parseInt(line);
//                    totaalElf += i;
//                } else {
//                    System.out.println("Totaal elf:"+ totaalElf);
//                    elfList.add(totaalElf);
//                    totaalElf = 0;
//                }
//            }
//            elfList.sort(Collections.reverseOrder());
//        }
//        System.out.println("Max0:"+ elfList.get(0));
//        System.out.println("Max1:"+ elfList.get(1));
//        System.out.println("Max2:"+ elfList.get(2));
//        int totaal = elfList.get(0) + elfList.get(1) + elfList.get(2);
//        System.out.println("Max totaal:"+ totaal);      // 191030
//    }
//*/
//}
