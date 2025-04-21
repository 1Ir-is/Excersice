package ss11_stack_queue.bai_tap.optional_bai_tap_1;

import java.util.Stack;
import java.util.Scanner;

public class DecimalToBinary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số thập phân: ");
        int decimal = scanner.nextInt();

        String binary = convertToBinary(decimal);
        System.out.println("Số nhị phân tương ứng: " + binary);
    }

    public static String convertToBinary(int number) {
        Stack<Integer> stack = new Stack<>();

        if (number == 0) return "0";

        while (number > 0) {
            int remainder = number % 2;
            stack.push(remainder);
            number /= 2;
        }

        StringBuilder binary = new StringBuilder();
        while (!stack.isEmpty()) {
            binary.append(stack.pop());
        }

        return binary.toString();
    }
}

