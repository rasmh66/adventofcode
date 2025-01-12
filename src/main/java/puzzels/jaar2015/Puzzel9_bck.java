package puzzels.jaar2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Puzzel9_bck {
    private final int C_JAAR = 2015;
    private final String C_PUZZELNR = "9a";

    public void start() throws IOException {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input/"+C_JAAR+"/input"+C_PUZZELNR+".txt";

        Graph graph = new Graph();
//        int tel = 0;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] par = line.split(" ");
                System.out.println(Arrays.stream(par).toList());

                graph.addVertex(par[0]);
                graph.addVertex(par[2]);
                int dist = Integer.parseInt(par[4]);

                graph.addEdge(par[0],par[2], dist);

//                System.out.println(graph);
            }
            System.out.println(graph);

            Vertex v = new Vertex("London");
            int len = graph.dfsWithoutRecursion(v);
            System.out.println(len);

            graph.resetVertex();
            v = new Vertex("Dublin");
            len = graph.dfsWithoutRecursion(v);
            System.out.println(len);

        }
    }
}
