package bai_tap_them.case_study_furuma.view;

import bai_tap_them.case_study_furuma.utils.ValidationUtils;

import java.util.Scanner;

public class CommonView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getChoice(int maxOption) {
        System.out.print("Enter you choice: ");
        return ValidationUtils.validateMenuChoice(scanner, maxOption);
    }
}
