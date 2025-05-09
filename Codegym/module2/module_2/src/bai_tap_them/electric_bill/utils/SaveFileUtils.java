package bai_tap_them.electric_bill.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveFileUtils {
    public static void writeToFile(String filePath, List<String> dataLines, boolean append) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, append))){
            for (String line: dataLines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public static List<String> readFromFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return lines;
    }
}
