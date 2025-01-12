package puzzels.jaar2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

public class Puzzel9 {
    private final int C_JAAR = 2015;
    private final String C_PUZZELNR = "9";

    private final Pattern PATTERN = Pattern.compile("([A-Za-z]+) to ([A-Za-z]+) = ([0-9]+)");
    private Map<String, Map<String, Integer>> distances = new HashMap<>();
    private Set<String> cities = new HashSet<>();

    public void start() throws IOException {
        String dir = System.getProperty("user.dir");
        String fileName = dir + "/input/"+C_JAAR+"/input"+C_PUZZELNR+".txt";

        Graph graph = new Graph();
//        int tel = 0;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] par = line.split(" ");
//                System.out.println(Arrays.stream(par).toList());
                System.out.println(par[0] + ' ' + par[2]+ ' ' + par[4]);

                if(!distances.containsKey(par[0])) {
                    distances.put(par[0], new HashMap<>());
                }
                distances.get(par[0]).put(par[2], Integer.parseInt(par[4]));
                cities.add(par[0]);
                cities.add(par[2]);

//                System.out.println(len);
            }
            Set<Integer> distances = permutations(cities).stream().map(this::distance).collect(Collectors.toSet());

            System.out.println(distances.stream().min(Integer::compare).get());
            System.out.println(distances.stream().max(Integer::compare).get());

        }
    }

    private int distance(List<String> names) {
        int distance = 0;
        for(int i = 0;i < names.size() - 1;i++) {
            String n1 = names.get(i);
            String n2 = names.get(i + 1);

            if(distances.containsKey(n1) && distances.get(n1).containsKey(n2)) {
                distance += distances.get(n1).get(n2);
            }
            else {
                distance += distances.get(n2).get(n1);
            }
        }

        return distance;
    }

    public static List<List<String>> permutations(Collection<String> names) {
        List<List<String>> permutations = new ArrayList<>();

        permutations(new ArrayList<>(), new ArrayList<>(names), permutations);

        return permutations;
    }

    public static void permutations(List<String> head, List<String> tail, List<List<String>> permutations) {
        if(tail.size() == 0) {
            permutations.add(head);
            return;
        }
        for(int i = 0;i < tail.size();i++) {
            List<String> newHead = new ArrayList<>(head.size() + 1);
            List<String> newTail = new ArrayList<>(tail);

            newHead.addAll(head);
            newHead.add(newTail.remove(i));

            permutations(newHead, newTail, permutations);
        }
    }

}
