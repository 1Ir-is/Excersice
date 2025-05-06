package bai_tap_them.student_teacher_management.views;

import bai_tap_them.student_teacher_management.utils.ValidationUtils;

import java.util.Scanner;

public class StudentView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputStudentId() {
        System.out.print("Enter student ID (format ST-YYYY): ");
        return ValidationUtils.validateInput("ST-\\d{4}", "Invalid ID format! Please enter again.");
    }

    public static String inputStudentName() {
        System.out.print("Enter student name: ");
        return ValidationUtils.validateInput("[A-Z][a-z]*(\\s[A-Z][a-z]*)*", "Name must be capitalize the first letter of each word.");
    }

    public static String inputStudentDOB() {
        System.out.print("Enter student date of birth (dd/MM/yyyy): ");
        return ValidationUtils.validateDateOfBirth();
    }

    public static String inputStudentGender() {
        System.out.print("Enter student gender: ");
        return ValidationUtils.validateGender();
    }


}
