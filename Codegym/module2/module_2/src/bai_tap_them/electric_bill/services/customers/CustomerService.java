package bai_tap_them.electric_bill.services.customers;

import bai_tap_them.electric_bill.models.Customer;
import bai_tap_them.electric_bill.models.ForeignCustomer;
import bai_tap_them.electric_bill.models.VietnameseCustomer;
import bai_tap_them.electric_bill.repositories.customers.ICustomerRepository;
import bai_tap_them.electric_bill.utils.ValidationUtils;

import java.util.Scanner;

public class CustomerService implements ICustomerService {
    private final ICustomerRepository customerRepository;
    private static final Scanner scanner = new Scanner(System.in);

    public CustomerService(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void addCustomer() {
        int choice;
        while (true) {
            System.out.println("1. Add Vietnamese Customer");
            System.out.println("2. Add Foreign Customer");
            System.out.print("Your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1 || choice == 2) break;
                System.out.println("Invalid choice! Please enter 1 or 2.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }

        String id;
        while (true) {
            System.out.print("Enter ID: ");
            id = scanner.nextLine();
            boolean isVietnamese = (choice == 1);
            if (ValidationUtils.isValidCustomerId(id, isVietnamese)) break;
            System.out.println("Invalid ID format! Please try again.");
        }

        String name;
        while (true) {
            System.out.print("Enter Name: ");
            name = scanner.nextLine();
            if (ValidationUtils.isValidName(name)) break;
            System.out.println("Invalid name! Name must contain only letters and spaces.");
        }

        if (choice == 1) {
            String type;
            while (true) {
                System.out.print("Enter Customer Type: ");
                type = scanner.nextLine();
                if (ValidationUtils.isValidCustomerType(type)) break;
                System.out.println("Invalid customer type! It cannot be empty.");
            }

            int limit;
            while (true) {
                System.out.print("Enter Consumption Limit: ");
                String limitInput = scanner.nextLine();
                if (ValidationUtils.isValidPositiveInteger(limitInput)) {
                    limit = Integer.parseInt(limitInput);
                    break;
                }
                System.out.println("Invalid consumption limit! It must be a positive integer.");
            }

            customerRepository.add(new VietnameseCustomer(id, name, type, limit));
            System.out.println("Vietnamese customer added successfully!");
        } else {
            String nationality;
            while (true) {
                System.out.print("Enter Nationality: ");
                nationality = scanner.nextLine();
                if (ValidationUtils.isValidNationality(nationality)) break;
                System.out.println("Invalid nationality! It cannot be empty.");
            }

            customerRepository.add(new ForeignCustomer(id, name, nationality));
            System.out.println("Foreign customer added successfully!");
        }
    }

    @Override
    public void displayCustomers() {
        System.out.println("--------------------------------------------------");
        System.out.printf("%-10s %-20s %-15s\n", "ID", "Name", "Details");
        System.out.println("--------------------------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            if (customer instanceof VietnameseCustomer) {
                VietnameseCustomer vietnameseCustomer = (VietnameseCustomer) customer;
                System.out.printf("%-10s %-20s %-15s\n",
                        vietnameseCustomer.getId(),
                        vietnameseCustomer.getName(),
                        "Type: " + vietnameseCustomer.getCustomerType() + ", Limit: " + vietnameseCustomer.getConsumptionLimit());
            } else if (customer instanceof ForeignCustomer) {
                ForeignCustomer foreignCustomer = (ForeignCustomer) customer;
                System.out.printf("%-10s %-20s %-15s\n",
                        foreignCustomer.getId(),
                        foreignCustomer.getName(),
                        "Nationality: " + foreignCustomer.getNationality());
            }
        }
        System.out.println("--------------------------------------------------");
    }

    @Override
    public void searchCustomerByName() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (Customer customer : customerRepository.findAll()) {
            if (customer.getName().toLowerCase().contains(name.toLowerCase())) {
                System.out.println(customer.toCSV());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No customer found!");
        }
    }
}