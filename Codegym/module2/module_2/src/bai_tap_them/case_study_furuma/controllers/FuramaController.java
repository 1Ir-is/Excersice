package bai_tap_them.case_study_furuma.controllers;

import java.util.InputMismatchException;
import java.util.Scanner;

public class FuramaController {
    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("================= [ MENU ] ==================");
            System.out.println("1.\tEmployee Management");
            System.out.println("2.\tCustomer Management");
            System.out.println("3.\tFacility Management ");
            System.out.println("4.\tBooking Management");
            System.out.println("5.\tPromotion Management");
            System.out.println("6.\tExit");
            System.out.println("=============================================");

            System.out.print("Enter your choice: ");
            choice = validateMenuChoice(scanner, 1, 6);

            switch (choice) {
                case 1:
                    displayEmployeeMenu(scanner);
                    break;
                case 2:
                    displayCustomerMenu(scanner);
                    break;
                case 3:
                    displayFacilityMenu(scanner);
                    break;
                case 4:
                    displayBookingMenu(scanner);
                    break;
                case 5:
                    displayPromotionMenu(scanner);
                    break;
                case 6:
                    if (confirmExit(scanner)) {
                        System.out.println("Exit the program!");
                    } else {
                        choice = -1;
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again");
            }

        } while (choice != 6);
    }

    private void displayEmployeeMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("================= [ Employee Management ] ==================");
            System.out.println("1.\tDisplay list employees");
            System.out.println("2.\tAdd new employee");
            System.out.println("3.\tEdit employee");
            System.out.println("4.\tReturn main menu");
            System.out.println("============================================================");
            System.out.print("Enter you choice: ");
            choice = validateMenuChoice(scanner, 1, 4);

            switch (choice) {
                case 1:
                    System.out.println("Display list employees");
                    break;
                case 2:
                    System.out.println("Add new employee");
                    break;
                case 3:
                    System.out.println("Edit employee");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        } while (true);
    }

    private void displayCustomerMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("================= [ Customer Management ] ==================");
            System.out.println("1.\tDisplay list customers");
            System.out.println("2.\tAdd new customer");
            System.out.println("3.\tEdit customer");
            System.out.println("4.\tReturn main menu");
            System.out.println("============================================================");

            System.out.print("Enter your choice: ");
            choice = validateMenuChoice(scanner, 1, 4);

            switch (choice) {
                case 1:
                    System.out.println("Display list customers");
                    break;
                case 2:
                    System.out.println("Add new customers");
                    break;
                case 3:
                    System.out.println("Edit customer");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        } while (true);
    }

    private void displayFacilityMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("========== Facility Management ==========");
            System.out.println("1. Display list facility");
            System.out.println("2. Add new facility");
            System.out.println("3. Display list facility maintenance");
            System.out.println("4. Return to main menu");
            System.out.println("=========================================");
            System.out.print("Enter your choice: ");

            choice = validateMenuChoice(scanner, 1, 4);

            switch (choice) {
                case 1:
                    System.out.println("Display list facility");
                    break;
                case 2:
                    System.out.println("Add new facility");
                    break;
                case 3:
                    System.out.println("Display list facility maintenance");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        } while (true);
    }

    private void displayBookingMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("========== Booking Management ==========");
            System.out.println("1. Add new booking");
            System.out.println("2. Display list booking");
            System.out.println("3. Create new contracts");
            System.out.println("4. Display list contracts");
            System.out.println("5. Edit contracts");
            System.out.println("6. Return to main menu");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");

            choice = validateMenuChoice(scanner, 1, 6);

            switch (choice) {
                case 1:
                    System.out.println("Adding a new booking...");
                    break;
                case 2:
                    System.out.println("Displaying list of bookings...");
                    break;
                case 3:
                    System.out.println("Creating new contracts...");
                    break;
                case 4:
                    System.out.println("Displaying list of contracts...");
                    break;
                case 5:
                    System.out.println("Editing contracts...");
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

    private void displayPromotionMenu(Scanner scanner) {
        int choice;
        do {
            System.out.println("========== Promotion Management ==========");
            System.out.println("1. Display list customers use service");
            System.out.println("2. Display list customers get voucher");
            System.out.println("3. Return to main menu");
            System.out.println("==========================================");
            System.out.print("Enter your choice: ");

            choice = validateMenuChoice(scanner, 1, 3);

            switch (choice) {
                case 1:
                    System.out.println("Displaying list of customers using services...");
                    break;
                case 2:
                    System.out.println("Displaying list of customers getting vouchers...");
                    break;
                case 3:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    private boolean confirmExit(Scanner scanner) {
        while (true) {
            System.out.print("Are you sure want to exit the program? (Y/N): ");
            String confirmation = scanner.nextLine().trim().toUpperCase();
            if (confirmation.equals("Y")) {
                return true;
            } else if (confirmation.equals("N")) {
                return false;
            } else {
                System.out.println("Invalid option. Please try again!");
            }
        }
    }

    private int validateMenuChoice(Scanner scanner, int min, int max) {
        int choice = -1;
        while (true) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.print("Invalid choice. Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Invalid choice. Please try again!");
                scanner.nextLine();
            }
        }
    }
}
