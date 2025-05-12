package bai_tap_them.student_teacher_management.views;

import bai_tap_them.student_teacher_management.utils.ValidationUtils;

import java.util.Scanner;

public class CommonView {
    static Scanner scanner = new Scanner(System.in);

    public static String getIdToDelete() {
        System.out.print("Enter ID to delete: ");
        return scanner.nextLine();
    }

    public static String getNameToSearch() {
        System.out.print("Enter Name to search: ");
        return scanner.nextLine();
    }
}
