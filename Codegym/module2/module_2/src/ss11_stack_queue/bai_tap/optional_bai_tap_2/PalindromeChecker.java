package ss11_stack_queue.bai_tap.optional_bai_tap_2;

import java.util.*;

public class PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập chuỗi cần kiểm tra: ");
        String input = scanner.nextLine();

        String processed = input.replaceAll("[^a-zA-Z]", "").toLowerCase();

        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();


        for (char c : processed.toCharArray()) {
            stack.push(c);
            queue.offer(c);
        }

        boolean isPalindrome = true;
        while (!stack.isEmpty()) {
            if (!stack.pop().equals(queue.poll())) {
                isPalindrome = false;
                break;
            }
        }

        if (isPalindrome) {
            System.out.println("==> Đây là chuỗi Palindrome.");
        } else {
            System.out.println("==> Đây KHÔNG phải là chuỗi Palindrome.");
        }
    }
}

