package bai_tap_them.case_study_furuma.views;

import bai_tap_them.case_study_furuma.utils.ValidationUtils;

import java.util.Scanner;

public class CommonView {
    public static int getChoice(int maxOption) {
        System.out.print("Enter you choice: ");
        return ValidationUtils.validateMenuChoice(maxOption);
    }

    public static void goBack(Scanner scanner) {
        System.out.println("Press Enter to return....");
        scanner.nextLine();
    }
}
