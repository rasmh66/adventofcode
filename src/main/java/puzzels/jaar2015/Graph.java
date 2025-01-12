package puzzels.jaar2015;

import java.util.*;

public class Graph {

    private Map<Vertex, List<Vertex>> adjVertices = new HashMap<>();

    void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<Vertex>());
    }

    void addEdge(String label1, String label2, Integer i2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        v2.setWeight(i2);

//        List<Vertex> v = adjVertices.get(v1);

        adjVertices.get(v1).add(v2);
//        adjVertices.get(v2).add(v1);
    }

    List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }
    Vertex getFirstAdjVertices() {
        Set<Vertex> s = adjVertices.keySet();

        return null;
    }

    void removeVertex(String label) {
        Vertex v = new Vertex(label);
        adjVertices.values().stream().forEach(e -> e.remove(v));
        adjVertices.remove(new Vertex(label));
    }
    void resetVertex() {
        adjVertices.forEach(
                (key, value)
                        -> key.setVisited(false));
        for (Map.Entry<Vertex, List<Vertex>> set :
                adjVertices.entrySet()) {
            set.getKey().setVisited(false);
            for (Vertex v :
                    set.getValue()) {
                v.setVisited(false);
            }
        }
    }

    public int dfsWithoutRecursion(Vertex start) {
        int tel = 0;
        Stack<Vertex> stack = new Stack<Vertex>();
//        boolean[] isVisited = new boolean[adjVertices.size()];
        stack.push(start);
        while (!stack.isEmpty()) {
            Vertex current = stack.pop();
            if(!current.isVisited()){
                current.setVisited(true);
                tel += current.getWeight();
                visit(current);
                for (Vertex dest : adjVertices.get(current)) {
                    if (!dest.isVisited())
                        stack.push(dest);
                }
            }
        }
        return tel;
    }

    private void visit(Vertex current) {
        System.out.println(current);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        Collection<List<Vertex>> children = adjVertices.values();
        str.append (Arrays.toString(children.toArray()));
        return str.toString();
    }

}