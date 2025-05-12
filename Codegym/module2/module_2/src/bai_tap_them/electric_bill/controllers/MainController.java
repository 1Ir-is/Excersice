package bai_tap_them.electric_bill.controllers;

import bai_tap_them.electric_bill.models.ForeignCustomer;
import bai_tap_them.electric_bill.models.VietnameseCustomer;
import bai_tap_them.electric_bill.utils.ValidationUtils;

import java.util.List;
import java.util.Scanner;

public class MainController {
    private final VietnamCustomerController vietnamController = new VietnamCustomerController();
    private final ForeignCustomerController foreignController = new ForeignCustomerController();
    private final BillController billController;

    public MainController(BillController billController) {
        this.billController = billController;
    }

    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("ELECTRIC BILL MANAGEMENT");
            System.out.println("1. Add new customer.");
            System.out.println("2. Display list of customer.");
            System.out.println("3. Search customer.");
            System.out.println("4. Add new invoice.");
            System.out.println("5. Edit invoice.");
            System.out.println("6. Display invoice.");
            System.out.println("7. Exit");
            System.out.print("Your option: ");
            int choice = ValidationUtils.validateMenuChoice(7);

            switch (choice) {
                case 1:
                    displayAddCustomerMenu();
                    break;
                case 2:
                    displayAllCustomers();
                    break;
                case 3:
                    searchCustomer();
                    break;
                case 4:
                    billController.addBill();
                    break;
                case 5:
                    billController.editBill();
                    break;
                case 6:
                    billController.displayBillDetails();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayAddCustomerMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Add new Vietnamese customer.");
        System.out.println("2. Add new Foreign customer.");
        System.out.print("Your option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                vietnamController.addCustomer();
                break;
            case 2:
                foreignController.addCustomer();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void displayAllCustomers() {
        vietnamController.displayCustomers();
        foreignController.displayCustomers();
    }

    private void searchCustomer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name to search: ");
        String keyword = scanner.nextLine();

        List<VietnameseCustomer> vietnamResults = vietnamController.searchCustomer(keyword);
        List<ForeignCustomer> foreignResults = foreignController.searchCustomer(keyword);

        System.out.println("Result:");
        for (VietnameseCustomer customer : vietnamResults) {
            System.out.println(customer);
        }
        for (ForeignCustomer customer : foreignResults) {
            System.out.println(customer);
        }

        if (vietnamResults.isEmpty() && foreignResults.isEmpty()) {
            System.out.println("Not found.");
        }
    }
}