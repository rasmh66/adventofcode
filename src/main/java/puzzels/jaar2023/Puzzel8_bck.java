package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzel8_bck extends abstractPuzzel {

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
            return "{" + data +
                    ", linkerNode='" + linkerNode + '\'' +
                    ", rechterNode='" + rechterNode + '\'' +
                    '}';
        }
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
        System.out.println("network = " + network);

        Node[] huidigeNode = findBeginNode(network, "A");

        String[] volg = new String[huidigeNode.length];
        int teller = 1;
        int index = 0;
        while (true) {
            char c = sequence[index];
            switch (c) {
                case 'R':
                    for (int i = 0; i < volg.length; i++) {
                        volg[i] = huidigeNode[i].getRechterNode();
                    }
                    break;
                case 'L':
                    for (int i = 0; i < volg.length; i++) {
                        volg[i] = huidigeNode[i].getLinkerNode();
                    }
                    break;
            }
            boolean einde = true;
            for (int i = 0; i < volg.length; i++) {
                if (!volg[i].endsWith("Z")) {
                    einde = false;
                    break;
                }
            }
            if (einde) {
                break;
            } else {
                huidigeNode = findNode(network, volg);
            }
            teller++;
            index++;
            if (index == sequence.length) {
                index = 0;
            }
        }
        System.out.println("teller = " + teller);
    }

    public Puzzel8_bck(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    protected Node[] findBeginNode(List<Node> lijst, String findStr) {
        List<Node> result = new ArrayList<>();
        for (Node n :
                lijst) {
            if (n.data.endsWith(findStr)) {
                result.add(n);
            }
        }
        return (Node[]) result.toArray(new Node[0]);
    }

    protected Node[] findNode(List<Node> lijst, String[] findStr) {
        List<Node> result = new ArrayList<>();
        for (Node n :
                lijst) {
            for (int i = 0; i < findStr.length; i++) {
                if (n.data.equals(findStr[i])) {
                    result.add(n);
                }
            }
        }
//        if (result.size() != 2) {
//            System.out.println("Error len !");
//        }
        return (Node[]) result.toArray(new Node[0]);
    }
}

