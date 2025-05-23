package bai_tap_them.student_teacher_management.views;

import bai_tap_them.student_teacher_management.models.Student;
import bai_tap_them.student_teacher_management.utils.ValidationUtils;

import java.util.Scanner;

public class StudentView extends CommonView {
    static Scanner scanner = new Scanner(System.in);

    public static int showMenu() {
        System.out.println("---- Student Management ----");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Delete Student");
        System.out.println("4. Search Student");
        System.out.println("5. Sort Students by Name");
        System.out.println("6. Return to Main Menu");
        System.out.print("Choose: ");
        return ValidationUtils.validateMenuChoice(6);
    }

    public static Student getStudentInput() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Gender (Male/Female): ");
        String gender = ValidationUtils.validateGender();
        System.out.print("Enter Date of Birth [dd/MM/yyyy]: ");
        String dob = ValidationUtils.validateDateOfBirth();
        System.out.print("Enter Class Name: ");
        String className = scanner.nextLine();
        System.out.print("Enter Grade: ");
        double grade = ValidationUtils.validateDouble(0);
        return new Student(id, name, gender, dob, className, grade);
    }
}