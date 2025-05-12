package bai_tap_them.student_teacher_management.views;

import bai_tap_them.student_teacher_management.models.Teacher;
import bai_tap_them.student_teacher_management.utils.ValidationUtils;


import java.util.Scanner;

public class TeacherView extends CommonView{
    static Scanner scanner = new Scanner(System.in);

    public static int showMenu() {
        System.out.println("---- Teacher Management ----");
        System.out.println("1. Add Teacher");
        System.out.println("2. Display All Teachers");
        System.out.println("3. Delete Teacher");
        System.out.println("4. Search Teacher");
        System.out.println("5. Sort Teachers by Name");
        System.out.println("6. Return to Main Menu");
        System.out.print("Choose: ");
        return ValidationUtils.validateMenuChoice(6);
    }

    public static Teacher getTeacherInput() {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Gender (Male/Female): ");
        String gender = ValidationUtils.validateGender();
        System.out.print("Enter Date of Birth [dd/MM/yyyy]: ");
        String dob = ValidationUtils.validateDateOfBirth();
        System.out.print("Enter Subject: ");
        String subject = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = ValidationUtils.validateSalary();
        return new Teacher(id, name, gender, dob, subject, salary);
    }
}
