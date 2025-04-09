package ss3_mang_va_phuong_thuc.bai_tap.menu;

import java.util.Scanner;

public class MenuApp {
    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println();
            System.out.println("================= [ MENU ] ==================");
            System.out.println("1. Display");
            System.out.println("2. Add");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Search");
            System.out.println("6. Exit");
            System.out.println("=============================================");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    display();
                    System.out.println("Press Enter to go back...");
                    scanner.nextLine();
                    break;
                case 2:
                    add();
                    System.out.println("Press Enter to go back...");
                    scanner.nextLine();
                    break;
                case 3:
                    update();
                    System.out.println("Press Enter to go back...");
                    scanner.nextLine();
                    break;
                case 4:
                    delete();
                    System.out.println("Press Enter to go back...");
                    scanner.nextLine();
                    break;
                case 5:
                    search();
                    System.out.println("Press Enter to go back...");
                    scanner.nextLine();
                    break;
                case 6:
                    if (confirmExit(scanner)) {
                        System.out.println("Exiting the program. Goodbye!");
                    } else {
                        choice = -1;
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    public static boolean confirmExit(Scanner scanner) {
        System.out.print("Are you sure you want to exit? (Y/N): ");
        String confirmation = scanner.next().trim().toUpperCase();
        return confirmation.equals("Y");
    }

    public static void delete() {
        System.out.println("You selected Delete.");
    }

    public static void add() {
        System.out.println("You selected Add.");
    }

    public static void display() {
        System.out.println("You selected Display.");
    }

    public static void update() {
        System.out.println("You selected Update.");
    }

    public static void search() {
        System.out.println("You selected Search.");
    }
}