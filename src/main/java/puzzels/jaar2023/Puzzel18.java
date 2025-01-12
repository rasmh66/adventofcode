package puzzels.jaar2023;

import puzzels.abstractPuzzel;
import puzzels.common.Vector2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Puzzel18 extends abstractPuzzel {

    static class Instructie {
        protected static final Pattern DIG_PATTERN = Pattern.compile("(R|L|D|U) (\\d*) \\(([#]\\w*)\\)");
        Vector2 direction = null;
        int distance = 0;

        Instructie(Vector2 v, int l) {
            this.direction = v;
            this.distance = l;
        }
        Instructie(String input) {
            Vector2 direction = null;
            int distance = 0;

            Matcher matcher = DIG_PATTERN.matcher(input);
            if (matcher.find()) {
                // Direction
                switch (matcher.group(1)) {
                    case "U" -> direction = Vector2.up();
                    case "D" -> direction = Vector2.down();
                    case "R" -> direction = Vector2.right();
                    case "L" -> direction = Vector2.left();
                    default -> throw new UnsupportedOperationException("Invalid direction");
                }
                // Distance
                distance = Integer.parseInt(matcher.group(2));
            }
            this.distance=distance;
            this.direction=direction;
        }

        public Vector2 getDirection() {
            return direction;
        }

        public void setDirection(Vector2 direction) {
            this.direction = direction;
        }

        public long getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Instructie{" + direction +
                    "," + distance +
                    '}';
        }
    }

    public Puzzel18(int jaar, int puzzelNr) {
        super(jaar, puzzelNr);
    }

    private final List<Instructie> instructies = new ArrayList<>();

    ArrayList<Integer> array = new ArrayList<>();

    public void start() throws IOException {

        List<String> inputs = Files.lines(Paths.get(fileName)).collect(Collectors.toList());
        for (String s :
                inputs) {
            instructies.add(new Instructie(s));
        }

        System.out.println("instructies = " + instructies);

        for (Instructie i: instructies) {

        }
    }


}

