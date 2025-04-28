package bai_tap_them.case_study_furuma.view;

import bai_tap_them.case_study_furuma.utils.ValidationUtils;

import java.util.Scanner;

public class FacilityView {
    protected static final Scanner scanner = new Scanner(System.in);

    public static String inputId(String regex, String errorMessage) {
        System.out.print("Enter ID (" + regex + "): ");
        return ValidationUtils.validateInput(regex, errorMessage);
    }

    public static String inputName() {
        System.out.print("Enter name: ");
        return ValidationUtils.validateInput("[A-Z][a-z]*(\\s[A-Z][a-z]*)*", "Invalid name format!");
    }

    public static double inputArea() {
        System.out.print("Enter area (>30): ");
        return ValidationUtils.validateDouble(30);
    }

    public static double inputRentalCost() {
        System.out.print("Enter rental cost (>0): ");
        return ValidationUtils.validateDouble(0);
    }

    public static int inputMaxPeople() {
        System.out.print("Enter max people (1-19): ");
        return ValidationUtils.validateInt(1, 19);
    }

    public static String inputRentalType() {
        String rentalType;
        do {
            System.out.print("Enter rental type: ");
            rentalType = scanner.nextLine();
        } while (!ValidationUtils.validateNotEmpty(rentalType, "Rental Type cannot be empty, please re-enter."));
        return rentalType;
    }
}
