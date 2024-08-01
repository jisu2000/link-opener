package org.example.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FilesToMap {

    public static HashMap<String, String> readHashMapFromFile(String filePath) throws IOException {
        HashMap<String, String> map = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2);
                if (parts.length >= 2) {
                    String key = parts[0];
                    String value = parts[1];
                    map.put(key, value);
                } else {
                    System.err.println("Ignoring malformed line: " + line);
                }
            }
        }

        return map;
    }

     public static void writeHashMapToFile(HashMap<String, String> map, String filePath) throws IOException {
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
             for (String entry : map.keySet()) {
                 writer.write(entry + "=" + map.get(entry));
                 writer.newLine();
             }
         }
     }

}
