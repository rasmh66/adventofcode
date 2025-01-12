package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Puzzel8 extends abstractPuzzel {

    public class Node {
        private final String data;
        private String linkerNode = null;
        private String rechterNode = null;
//        private Set<Node> neighbors = new HashSet<>();

        public Node(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }

        public void addNodes(String lnode, String rnode) {
            this.linkerNode = lnode;
            this.rechterNode = rnode;
        }

        public String getLinkerNode() {
            return linkerNode;
        }

        public String getRechterNode() {
            return rechterNode;
        }

        @Override
        public String toString() {
            return "{" + data + ", linkerNode='" + linkerNode + '\'' + ", rechterNode='" + rechterNode + '\'' + '}';
        }
    }

    public Puzzel8(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        List<Node> network = new ArrayList<>();
        char[] sequence = null;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            sequence = br.readLine().toCharArray();
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {

                    String[] deelStr = line.split("=");
                    Node node = new Node(deelStr[0].trim());

                    String zonderHaakjes = deelStr[1].trim().substring(1, deelStr[1].length() - 2);
                    String[] nodes = zonderHaakjes.split(",");

                    node.addNodes(nodes[0].trim(), nodes[1].trim());
                    network.add(node);
                }
            }
        }
        System.out.println("sequence = " + Arrays.toString(sequence));
//        System.out.println("network = " + network);

        Node[] gevondenNodes = findBeginNode(network, "A");
        int[] tellerArray = new int[gevondenNodes.length];

        for (int i = 0; i < gevondenNodes.length; i++) {
            Node huidig = gevondenNodes[i];
            String volg = null;

            int teller = 1;
            int index = 0;
            while (true) {
                char c = sequence[index];
                switch (c) {
                    case 'R':
                        volg = huidig.getRechterNode();
                        break;
                    case 'L':
                        volg = huidig.getLinkerNode();
                        break;
                }
                if (volg.endsWith("Z")) {
                    break;
                } else {
                    huidig = findNode(network, volg);
                }
                teller++;
                index++;
                if (index == sequence.length) {
                    index = 0;
                }
            }
            System.out.println("teller = " + teller);
            tellerArray[i] = teller;
        }
        System.out.println("teller = " + arrayLCM(tellerArray));
    }

    protected Node[] findBeginNode(List<Node> lijst, String findStr) {
        List<Node> result = new ArrayList<>();
        for (Node n : lijst) {
            if (n.data.endsWith(findStr)) {
                result.add(n);
            }
        }
        return (Node[]) result.toArray(new Node[0]);
    }

    protected Node findNode(List<Node> lijst, String findStr) {
        for (Node n : lijst) {
            if (n.data.equals(findStr)) {
                return n;
            }
        }
        return null;
    }

    // Functie om het grootste gemene deler (GCD) van twee getallen te berekenen
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    // Functie om het kleinste gemene veelvoud (LCM) van twee getallen te berekenen
    private static int lcm(int a, int b) {
        if (a == 0 || b == 0) {
            return 0; // LCM is niet gedefinieerd voor 0
        } else {
            return Math.abs(a * (b / gcd(a, b)));
        }
    }

    // Functie om het LCM van een array met integers te berekenen
    public static int arrayLCM(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0; // LCM is niet gedefinieerd voor een lege array
        }
        int result = arr[0];

        for (int i = 1; i < arr.length; i++) {
            result = lcm(result, arr[i]);
        }

        return result;
    }

}

