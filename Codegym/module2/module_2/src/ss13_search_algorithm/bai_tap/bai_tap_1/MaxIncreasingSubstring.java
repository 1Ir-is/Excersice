package ss13_search_algorithm.bai_tap.bai_tap_1;

import java.util.Scanner;

public class MaxIncreasingSubstring {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập chuỗi: ");
        String input = scanner.nextLine();

        String result = "";
        result += input.charAt(0);

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) > result.charAt(result.length() - 1)) {
                result += input.charAt(i);
            }
        }

        System.out.println("Chuỗi tăng dần lớn nhất là: " + result);
    }
}
