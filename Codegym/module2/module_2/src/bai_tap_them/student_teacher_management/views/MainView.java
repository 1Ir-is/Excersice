package bai_tap_them.student_teacher_management.views;


import bai_tap_them.student_teacher_management.utils.ValidationUtils;

public class MainView {
    public static int showMainMenu() {
        System.out.println("========= PERSON MANAGEMENT =========");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Teachers");
        System.out.println("3. Exit");
        System.out.print("Choose: ");
        return ValidationUtils.validateMenuChoice(3);
    }
}
