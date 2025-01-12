package puzzels.jaar2023;

import puzzels.abstractPuzzel;

import java.io.IOException;
import java.util.*;

public class Puzzel11t extends abstractPuzzel {

    class Node implements Comparable<Node> {
        int x, y; // positie van de node
        int g, h; // kost tot nu toe, heuristische waarde
        Node parent; // ouder node

        // constructor
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
            this.g = 0;
            this.h = 0;
            this.parent = null;
        }

        // methode om de totale kost (g + h) te berekenen
        public int getF() {
            return g + h;
        }

        // vergelijk nodes op basis van hun totale kost
        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.getF(), other.getF());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", g=" + g +
                    ", h=" + h +
                    '}';
        }
    }

    public Puzzel11t(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    public void start() throws IOException {

        test();
//        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//            }
//        }
    }


    public List<Node> findPath(int[][] grid, Node start, Node goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Set<Node> closedSet = new HashSet<>();

        openSet.add(start);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.equals(goal)) {
                // pad gevonden, reconstructie
                List<Node> path = new ArrayList<>();
                while (current != null) {
                    path.add(current);
                    current = current.parent;
                }
                Collections.reverse(path);
                return path;
            }

            closedSet.add(current);

            // buren verkennen
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    if (dx == 0 && dy == 0) {
                        continue; // huidige node overslaan
                    }

                    int nx = current.x + dx;
                    int ny = current.y + dy;

                    // controleer of buur binnen het grid valt
                    if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length) {
                        // controleer of buur een wand is
//                        if (grid[nx][ny] == 0) {
//                            continue;
//                        }

                        Node neighbor = new Node(nx, ny);
                        if (closedSet.contains(neighbor)) {
                            continue; // buur is al geëvalueerd
                        }

                        int tentativeG = current.g + 1; // kost van huidige node naar buur is altijd 1

                        if (!openSet.contains(neighbor) || tentativeG < neighbor.g) {
                            neighbor.g = tentativeG;
                            neighbor.h = heuristic(neighbor, goal);
                            neighbor.parent = current;

                            if (!openSet.contains(neighbor)) {
                                openSet.add(neighbor);
                            }
                        }
                    }
                }
            }
        }

        return null; // geen pad gevonden
    }

    // eenvoudige Manhattan-heuristiek
    private static int heuristic(Node a, Node b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }

    public void test() {
        // Voorbeeldgebruik
        int[][] grid = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };

        Node start = new Node(0, 0);
        Node goal = new Node(4, 2);

        List<Node> path = findPath(grid, start, goal);

        if (path != null) {
            for (Node node : path) {
                System.out.println("(" + node.x + ", " + node.y + ")");
            }
        } else {
            System.out.println("Geen pad gevonden");
        }
    }
}
