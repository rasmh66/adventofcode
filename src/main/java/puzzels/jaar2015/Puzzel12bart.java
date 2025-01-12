//package puzzels.jaar2015;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.json.simple.parser.ParseException;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.*;
//
//
//public class Puzzel12bart {
//    private static final int C_JAAR = 2015;
//    private static final String C_PUZZELNR = "12";
//
//    List<Integer> numbers = new ArrayList<>();
//
//    public int findNumbers(String jsonString) throws ParseException {
//
//        // Recursively traverse the JSON object and add all numbers to the list
//        processJson(jsonString);
//
//        // Return the sum of all numbers
//        int sum = 0;
//        for (int number : numbers) {
//            sum += number;
//        }
//        return sum;
//    }
//
//    public void start() throws IOException, ParseException {
//        String dir = System.getProperty("user.dir");
//        String fileName = dir + "/input/" + C_JAAR + "/input" + C_PUZZELNR + ".txt";
//        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//
//                  int sum = findNumbers(line);
////                int sum = doParse(line);
//                System.out.println(sum); // 6
//            }
//        }
//    }
//
//    public void processJson(String jsonStr) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            JsonNode node = objectMapper.readTree(jsonStr);
//            System.out.println(node);
//            processNode(node, false);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    private void processNode(JsonNode n, boolean skip) {
//
//        if (n.isContainerNode()) {
//            processJsonContainer(n.iterator(), skip);
//        } else if (n.isNull()) {
//            System.out.println("Null || :" + n);
//        } else if (n.isNumber()) {
//            System.out.println("Number || :" + n.asDouble());
//            if (!skip) {
//                numbers.add(n.intValue());
//            }
//        } else if (n.isBoolean()) {
//            System.out.println("Boolean || :" + n.asBoolean());
//        } else if (n.isTextual()) {
//            System.out.println("Text || :" + n.asText());
//        }
//    }
//
//    private void processJsonContainer(Iterator<JsonNode> iterator, boolean skip) {
//        while (iterator.hasNext()) {
//            JsonNode node = iterator.next();
//            if (node.isObject()) {
//                Iterator<Map.Entry<String, JsonNode>> iter = node.fields();
//
//                while (iter.hasNext()) {
//                    Map.Entry<String, JsonNode> entry = iter.next();
//                    JsonNode v = entry.getValue();
//                    if (v.isTextual() && Objects.equals(v.textValue(), "red")) {
//                        skip=true;
//                        break;
//                    }
//                }
//                processNode(node, skip);
//                skip=false;
//            } else {
//                processNode(node, skip);
////                System.out.println("Onbekende container");
//            }
//        }
//    }
//}
////45533