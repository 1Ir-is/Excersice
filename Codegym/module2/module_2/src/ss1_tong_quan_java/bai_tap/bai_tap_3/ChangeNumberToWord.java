package ss1_tong_quan_java.bai_tap.bai_tap_3;

import java.util.Scanner;

public class ChangeNumberToWord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so can doc: ");
        int number = scanner.nextInt();
        if (number < 0 || number > 999) {
            System.out.println("Vuot qua gioi han!");
        } else {
            System.out.println(numberToWords(number));
        }
    }

    public static String numberToWords(int number) {
        if (number == 0) {
            return "zero";
        }
        String[] ones = {
                "",
                "one",
                "two",
                "three",
                "four",
                "five",
                "six",
                "seven",
                "eight",
                "nine",
                "ten",
                "eleven",
                "twelve",
                "thirteen",
                "fourteen",
                "fifteen",
                "sixteen",
                "seventeen",
                "eighteen",
                "nineteen"
        };
        String[] tens = {
                "",
                "",
                "twenty",
                "thirty",
                "forty",
                "fifty",
                "sixty",
                "seventy",
                "eighty",
                "ninety"
        };
        String words = "";

        if (number >= 100) {
            words += ones[number / 100] + " hundred";
            number %= 100;
            if (number > 0) {
                words += " and ";
            }
        }

        if (number >= 20) {
            words += tens[number / 10];
            number %= 10;
            if (number > 0) {
                words += " " + ones[number];
            }
        } else if (number > 0) {
            words += ones[number];
        }
        return words;
    }
}
