//package puzzels.jaar2015;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//
//public class Puzzel12 {
//    private static final int C_JAAR = 2015;
//    private static final String C_PUZZELNR = "12";
//
//    public static void start() throws IOException {
//        String dir = System.getProperty("user.dir");
//        String fileName = dir + "/input/" + C_JAAR + "/input" + C_PUZZELNR + ".txt";
//
//        byte[] content = Files.readAllBytes(Paths.get(fileName));
////        System.out.println("File content:\n" + content);
//
////        Pattern pattern = Pattern.compile("-?\\d+");
////        Matcher matcher = pattern.matcher(content);
//        HashMap myMap = new HashMap<String, String>();
//
//        try {
//            // Create an ObjectMapper instance
//            ObjectMapper objectMapper = new ObjectMapper();
//
//            // Read the JSON file and parse its contents
//            JsonNode jsonNode = objectMapper.readTree(new File(fileName));
//
////            JsonParser parser = objectMapper.createParser(new File(fileName));
//
//            myMap = objectMapper.readValue(content, HashMap.class);
//            System.out.println("Map is: "+myMap);
//
////            Map<String, Object> map
////                    = objectMapper.readValue(content, new TypeReference<Map<String,Object>>(){});
//
////            System.out.println("json:"+objectMapper.);
//            // Access specific values from the JSON object
////            String name = jsonNode.get("blue").asText();
////            int age = jsonNode.get("age").asInt();
////            String email = jsonNode.get("email").asText();
//
//            // Print the values
////            System.out.println("Name: " + name);
////            System.out.println("Age: " + age);
////            System.out.println("Email: " + email);
//        } catch (IOException e) {
//            System.out.println("An error occurred while reading the JSON file: " + e.getMessage());
//        }
//
//
////        int aantal = 0;
////        while (matcher.find()) {
////            String matchedNumberString = matcher.group();
////            int number = Integer.parseInt(matchedNumberString);
////            aantal += number;
////            System.out.print(" " + number);
////        }
////        System.out.println("\nAantal:"+aantal);
//    }
//}
