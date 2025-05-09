package bai_tap_them.electric_bill.controllers;

import bai_tap_them.electric_bill.repositories.bill.BillRepository;
import bai_tap_them.electric_bill.repositories.bill.IBillRepository;
import bai_tap_them.electric_bill.repositories.customers.CustomerRepository;
import bai_tap_them.electric_bill.repositories.customers.ICustomerRepository;
import bai_tap_them.electric_bill.services.bill.BillService;
import bai_tap_them.electric_bill.services.bill.IBillService;
import bai_tap_them.electric_bill.services.customers.CustomerService;
import bai_tap_them.electric_bill.services.customers.ICustomerService;

import java.util.Scanner;

public class MainController {
    private final ICustomerRepository customerRepository = new CustomerRepository();
    private final ICustomerService customerService = new CustomerService(customerRepository);

    private final IBillRepository billRepository = new BillRepository();
    private final IBillService billService = new BillService(billRepository, customerRepository);

    private static final Scanner scanner = new Scanner(System.in);

    public void displayMainMenu() {
        int choice;
        do {
            System.out.println("Management Bill System");
            System.out.println("1. Add Customer.");
            System.out.println("2. Display Customers.");
            System.out.println("3. Search Customer.");
            System.out.println("4. Add Bill.");
            System.out.println("5. Edit Bill.");
            System.out.println("6. Display Bills.");
            System.out.println("7. Exit.");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    customerService.addCustomer();
                    break;
                case 2:
                    customerService.displayCustomers();
                    break;
                case 3:
                    customerService.searchCustomerByName();
                    break;
                case 4:
                    billService.addBill();
                    break;
                case 5:
                    billService.editBill();
                    break;
                case 6:
                    billService.displayBill();
                    break;
                case 7:
                    if (confirmExit()) {
                        System.out.println("Exit the program!");
                    } else {
                        choice = -1;
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 7);
    }

    private boolean confirmExit() {
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

}
