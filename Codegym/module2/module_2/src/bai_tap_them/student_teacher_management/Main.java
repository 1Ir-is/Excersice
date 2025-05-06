package bai_tap_them.student_teacher_management;

import bai_tap_them.student_teacher_management.controller.ManagementController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManagementController managementController = new ManagementController();
        managementController.displayMainMenu();
        scanner.close();
    }
}
