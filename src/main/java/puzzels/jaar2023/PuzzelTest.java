package puzzels.jaar2023;

public class PuzzelTest {

    public void start() {
        String puzzel = "..##..";
        String sonderPunten = puzzel.replaceAll("^\\.+", "");

        System.out.println("sonderPunten = " + sonderPunten);
    }
//    public void start() {
//        Map<Integer, Point> oplossing = new Hashtable<>();
//        oplossing.put(755, new Point(8, 5));
//        oplossing.put(467, new Point(1, 3));
//        oplossing.put(598, new Point(8, 5));
//        oplossing.put(35, new Point(1, 3));
//        oplossing.put(617, new Point(4, 3));
//
//        // Groepeer de waarden op basis van de Point
//        Map<Point, Long> countingMap = oplossing.entrySet().stream()
//                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.counting()));
//
//        // Filter op basis van count == 2 en verzamel in een nieuwe map
//        Map<Point, Integer> resultaat = countingMap.entrySet().stream()
//                .filter(entry -> entry.getValue() == 2)
//                .collect(Collectors.toMap(Map.Entry::getKey, entry -> oplossing.entrySet().stream()
//                        .filter(e -> e.getValue().equals(entry.getKey()))
//                        .map(Map.Entry::getKey)
//                        .reduce(0, Integer::sum)));
//
//        // Resultaten afdrukken
//        resultaat.forEach((punt, som) -> System.out.println("Punt: " + punt + ", Som: " + som));
//    }
}

