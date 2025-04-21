package ss11_stack_queue.bai_tap.bai_tap_2;

import java.util.Map;
import java.util.TreeMap;

public class WordCount {
    public static void main(String[] args) {
        String input = "Java is simple and java is powerful";

        String[] words = input.toLowerCase().split("\\s+");

        Map<String, Integer> wordMap = new TreeMap<>();

        for (String word : words) {
            if (wordMap.containsKey(word)) {
                wordMap.put(word, wordMap.get(word) + 1);
            } else {
                wordMap.put(word, 1);
            }
        }
        
        System.out.println("Tần suất xuất hiện các từ:");
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
