package bai_tap_them.case_study_furuma.view;

import bai_tap_them.case_study_furuma.utils.ValidationUtils;

import java.util.Scanner;

public class CustomerView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputCustomerId() {
        System.out.print("Enter customer ID (format: KH-YYYY): ");
        return ValidationUtils.validateInput("KH-\\d{4}", "Invalid ID format. Please use [KH-YYYY] format!");
    }

    public static String inputCustomerName() {
        System.out.print("Enter customer name: ");
        return ValidationUtils.validateInput("[A-Z][a-z]*(\\s[A-Z][a-z]*)*", "Name must be capitalize the first letter of each word.");
    }

    public static String inputCustomerDOB() {
        System.out.print("Enter customer date of birth: ");
        return ValidationUtils.validateDateOfBirth();
    }

    public static String inputCustomerGender() {
        System.out.print("Enter customer gender: ");
        return ValidationUtils.validateGender();
    }

    public static String inputCustomerIdCard() {
        System.out.print("Enter ID Card number (9 or 12 digits): ");
        return ValidationUtils.validateInput("\\d{9}|\\d{12}", "ID Card must be 9 or 12 digits!");
    }

    public static String inputCustomerPhoneNumber() {
        System.out.print("Enter customer phone number: ");
        return ValidationUtils.validateInput("0\\d{9}", "Phone number must start with 0 and have 10 digits!");
    }

    public static String inputCustomerEmail() {
        System.out.print("Enter customer email: ");
        return ValidationUtils.validateInput("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", "Invalid email. Please try again!");
    }

    public static String inputAddress() {
        String address;
        do {
            System.out.print("Enter customer address: ");
            address = scanner.nextLine();
        } while (!ValidationUtils.validateNotEmpty(address, "Address cannot be empty, please re-enter."));
        return address;
    }

}
