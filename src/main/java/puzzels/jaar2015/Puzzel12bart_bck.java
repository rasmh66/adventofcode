//package puzzels.jaar2015;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class Puzzel12bart_bck {
//    private static final int C_JAAR = 2015;
//    private static final String C_PUZZELNR = "12";
//
//
//    public static int findNumbers(String jsonString) throws ParseException {
//        List<Integer> numbers = new ArrayList<>();
//
//        // Recursively traverse the JSON object and add all numbers to the list
//        traverseJson(jsonString, numbers);
//
//        // Return the sum of all numbers
//        int sum = 0;
//        for (int number : numbers) {
//            sum += number;
//        }
//        return sum;
//    }
//
//    private static void traverseJson(String jsonString, List<Integer> numbers) throws ParseException {
//        // Create a JSON object from the string
//        Object jsonObject = new JSONParser().parse(jsonString);
//
//        // Check if the object is a number
//        if (jsonObject instanceof Number) {
//            numbers.add((Integer) jsonObject);
//        } else if (jsonObject instanceof JSONObject) {
//            // Recursively traverse the object
//            JSONObject jsonObject1 = (JSONObject) jsonObject;
//
//            for (Object key : jsonObject1.keySet()) {
//                Object o = jsonObject1.get(key);
//                if (jsonObject instanceof Number) {
//                    numbers.add((Integer) jsonObject);
//                } else if (jsonObject instanceof JSONObject) {
//                    traverseJson(jsonObject1.get(key).toString(), numbers);
//                } else if (jsonObject instanceof JSONArray) {
//                    // Recursively traverse the array
//                    JSONArray jsonArray = (JSONArray) jsonObject;
//                    for (int i = 0; i < jsonArray.size(); i++) {
//                        traverseJson(jsonArray.get(i).toString(), numbers);
//                    }
//                }
//
//            }
//        } else if (jsonObject instanceof JSONArray) {
//            // Recursively traverse the array
//            JSONArray jsonArray = (JSONArray) jsonObject;
//            for (int i = 0; i < jsonArray.size(); i++) {
//                traverseJson(jsonArray.get(i).toString(), numbers);
//            }
//        }
//    }
//
//    public static void start() throws IOException, ParseException {
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
//            processNode(node);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//    private void processNode(JsonNode n) {
//        if (n.isContainerNode()) {
//            processJsonContainer(n.iterator());
//        } else if (n.isNull()) {
//            System.out.println("Null || :" + n);
//        } else if (n.isNumber()) {
//            System.out.println("Number || :" + n.asDouble());
//        } else if (n.isBoolean()) {
//            System.out.println("Boolean || :" + n.asBoolean());
//        } else if (n.isTextual()) {
//            System.out.println("Text || :" + n.asText());
//        }
//    }
//
//    private void processJsonContainer(Iterator<JsonNode> iterator) {
//        while (iterator.hasNext()) {
//            processNode(iterator.next());
//        }
//    }
///*
//    public static int doParse(String json) {
//        int sum = 0;
//        try {
//            JsonFactory factory = new JsonFactory();
//
//            ObjectMapper mapper = new ObjectMapper(factory);
//            JsonNode rootNode = mapper.readTree(json);
//
//            Iterator<JsonNode> elemIterator = rootNode.elements();
//
//            while (elemIterator.hasNext()) {
//                parseJson();
//            }
//            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
//            while (fieldsIterator.hasNext()) {
//
//                Map.Entry<String, JsonNode> field = fieldsIterator.next();
//                System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
//                JsonNode v = field.getValue();
//                if (v.isInt()) {
//                    sum += v.asInt();
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return sum;
//    }
//*/
//    /*
//    public static int parse2(String json)  {
//        int sum = 0;
//        try {
//            JsonFactory f = new JsonFactory();
//            JsonParser parser = f.createParser(json);
//            JsonToken token = parser.nextToken();
//            while (token != null) {
//                if (token.equals(JsonToken.START_ARRAY)) {
//                    System.out.println("Start Array : " + token.toString());
//                } else if (token.equals(JsonToken.END_ARRAY)) {
//                    System.out.println("End Array : " + token.toString());
//                } else if (token.equals(JsonToken.START_OBJECT)) {
//                    System.out.println("Start Object : " + token.toString());
//                } else if (token.equals(JsonToken.END_OBJECT)) {
//                    System.out.println("End Object : " + token.toString());
//                } else if (token.equals(JsonToken.FIELD_NAME)) {
//                    System.out.println("Field Name : " + token.toString());
//                } else if (token.equals(JsonToken.VALUE_FALSE)) {
//                    System.out.println("Value False : " + token.toString());
//                } else if (token.equals(JsonToken.VALUE_NULL)) {
//                    System.out.println("Value Null : " + token.toString());
//                } else if (token.equals(JsonToken.VALUE_NUMBER_FLOAT)) {
//                    System.out.println("Value Number Float : " + token.toString());
//                } else if (token.equals(JsonToken.VALUE_NUMBER_INT)) {
//                    System.out.println("Value Number Int : " + token.toString());
//                    sum += parser.getIntValue();
//                } else if (token.equals(JsonToken.VALUE_STRING)) {
//                    System.out.println("Value String : " + token.toString());
//                } else if (token.equals(JsonToken.VALUE_TRUE)) {
//                    System.out.println("Value True : " + token.toString());
//                } else {
//                    System.out.println("Something else : " + token.toString());
//                }
//                token = parser.nextToken();
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return sum;
//    }
//
//     */
//}
