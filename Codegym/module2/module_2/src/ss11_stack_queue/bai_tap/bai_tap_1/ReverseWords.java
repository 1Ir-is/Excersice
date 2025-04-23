package ss11_stack_queue.bai_tap.bai_tap_1;

import java.util.Stack;

public class ReverseWords {
    public static void main(String[] args) {
        String sentence = "Hoc lap trinh Java";
        String[] words = sentence.split(" ");

        Stack<String> wordStack = new Stack<>();

        for (String word : words) {
            wordStack.push(word);
        }
        
        String reversed = "";
        while (!wordStack.isEmpty()) {
            reversed += wordStack.pop() + " ";
        }

        System.out.println("Chuỗi đảo ngược: " + reversed.trim());
    }
}

