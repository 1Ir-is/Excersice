package bai_tap_them.case_study_furuma.views;

import bai_tap_them.case_study_furuma.utils.ValidationUtils;

import java.util.Scanner;

public class EmployeeView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputEmployeeId() {
        System.out.print("Enter employee ID (format: NV-YYYY): ");
        return ValidationUtils.validateInput("NV-\\d{4}", "Invalid ID format. Please use [NV-YYYY] format!");
    }

    public static String inputEmployeeName() {
        System.out.print("Enter employee name: ");
        return ValidationUtils.validateInput("[A-Z][a-z]*(\\s[A-Z][a-z]*)*", "Name must capitalize the first letter of each word.");
    }

    public static String inputEmployeeDOB() {
        System.out.print("Enter employee date of birth (dd/MM/yyyy): ");
        return ValidationUtils.validateDateOfBirth();
    }

    public static String inputEmployeeGender() {
        System.out.print("Enter employee gender: ");
        return ValidationUtils.validateGender();
    }

    public static String inputEmployeeIdCard() {
        System.out.print("Enter employee ID card number (9 or 12 digits): ");
        return ValidationUtils.validateInput("^\\d{9}(\\d{3})?", "ID card must be 9 or 12 digits.");
    }

    public static String inputEmployeePhoneNumber() {
        System.out.print("Enter employee phone number (starts with 0, 10 digits): ");
        return ValidationUtils.validateInput("0\\d{9}", "Phone number must start with 0 and have 10 digits.");
    }

    public static String inputEmployeeEmail() {
        System.out.print("Enter employee email: ");
        return ValidationUtils.validateInput("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", "Invalid email. Please try again!");
    }

    public static String inputEmployeeQualification() {
        String[] qualifications = {"Intermediate", "College", "University", "Postgraduate"};
        System.out.println("Select qualification: ");
        for (int i = 0; i < qualifications.length; i++) {
            System.out.println((i + 1) + ". " + qualifications[i]);
        }
        int choice = CommonView.getChoice(qualifications.length);
        return qualifications[choice - 1];
    }

    public static String inputEmployeePosition() {
        String[] positions = {"Receptionist", "Waiter", "Specialist", "Supervisor", "Manager", "Director"};
        System.out.println("Select position: ");
        for (int i = 0; i < positions.length; i++) {
            System.out.println((i + 1) + ". " + positions[i]);
        }
        int choice = CommonView.getChoice(positions.length);
        return positions[choice - 1];
    }

    public static double inputEmployeeSalary() {
        System.out.print("Enter employee salary: ");
        return ValidationUtils.validateSalary();
    }
}

