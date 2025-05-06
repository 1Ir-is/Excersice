package bai_tap_them.student_teacher_management.controller;

import bai_tap_them.student_teacher_management.utils.MenuPrinter;
import bai_tap_them.student_teacher_management.views.CommonView;

import java.util.Scanner;

public class ManagementController {
    private final Scanner scanner = new Scanner(System.in);

    public void displayMainMenu() {
        int choice;
        do {
            MenuPrinter.printMainMenu();
            choice = CommonView.getChoice(3);

            switch (choice) {
                case 1:
                    displayStudentManagementMenu();
                    break;
                case 2:
                    displayTeacherManagementMenu();
                    break;
                case 3:
                    if (confirmExit()) {
                        System.out.println("Exit program!!");
                    } else {
                        choice = -1;
                    }
                    break;
                default:
                    System.out.println("Invalid option! PLease try again.");
            }
        } while (choice != 3);
    }

    private void displayStudentManagementMenu() {
        int choice;
        do {
            MenuPrinter.printStudentMenu();
            choice = CommonView.getChoice(6);

            switch (choice) {
                case 1:
                    System.out.println("Display");
                    break;
                case 2:
                    System.out.println("Add");
                    break;
                case 3:
                    System.out.println("Edit");
                    break;
                case 4:
                    System.out.println("Delete");
                    break;
                case 5:
                    System.out.println("Search");
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        } while (true);
    }

    private void displayTeacherManagementMenu() {
        int choice;
        do {
            MenuPrinter.printTeacherMenu();
            choice = CommonView.getChoice(6);

            switch (choice) {
                case 1:
                    System.out.println("Display");
                    break;
                case 2:
                    System.out.println("Add");
                    break;
                case 3:
                    System.out.println("Edit");
                    break;
                case 4:
                    System.out.println("Delete");
                    break;
                case 5:
                    System.out.println("Search");
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        } while (true);
    }

    private boolean confirmExit() {
        while (true) {
            System.out.print("Are you sure want to exit (Y/N): ");
            String confirmation = scanner.nextLine().trim().toUpperCase();
            if (confirmation.equals("Y")) {
                return true;
            } else if (confirmation.equals("N")) {
                return false;
            } else {
                System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
