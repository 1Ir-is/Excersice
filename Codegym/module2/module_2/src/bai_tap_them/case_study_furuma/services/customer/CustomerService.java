package bai_tap_them.case_study_furuma.services.customer;

import bai_tap_them.case_study_furuma.models.Customer;
import bai_tap_them.case_study_furuma.repositories.customer.ICustomerRepository;
import bai_tap_them.case_study_furuma.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerService implements ICustomerService {
    private final ICustomerRepository customerRepository;
    private final Scanner scanner = new Scanner(System.in);

    public CustomerService(ICustomerRepository repository) {
        this.customerRepository = repository;
    }

    @Override
    public void display() {
        ArrayList<Customer> customers = customerRepository.findAll();

        if (customers.isEmpty()) {
            System.out.println("No customer found!");
            return;
        }

        String line = "+--------+----------------------+------------+--------+--------------+--------------+----------------------+------------------+------------------+";
        String headerFormat = "| %-6s | %-20s | %-10s | %-6s | %-12s | %-12s | %-20s | %-16s | %-16s |%n";

        System.out.println(line);
        System.out.printf(headerFormat, "ID", "Name", "DOB", "Gender", "ID Card", "Phone", "Email", "Customer Type", "Address");
        System.out.println(line);

        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i).getDetails());
        }

        System.out.println(line);
        System.out.println();
    }

    @Override
    public void add() {
        ArrayList<Customer> customers = customerRepository.findAll();

        System.out.print("Enter customer ID (format: KH-YYYY): ");
        String id;
        while (true) {
            id = ValidationUtils.validateInput("KH-\\d{4}", "Invalid ID format. Please use [KH-YYYY] format!");
            boolean isDuplicate = false;
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getId().equals(id)) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                System.out.print("ID already exists. Please enter a different ID: ");
            } else {
                break;
            }
        }

        System.out.print("Enter customer name: ");
        String name = ValidationUtils.validateInput("[A-Z][a-z]*(\\s[A-Z][a-z]*)*", "Name must capitalize the first letter of each word.");

        System.out.print("Enter customer date of birth: ");
        String dateOfBirth = ValidationUtils.validateDateOfBirth();

        System.out.print("Enter gender: ");
        String gender = ValidationUtils.validateGender();

        System.out.print("Enter ID Card number (9 or 12 digits): ");
        String idCard = ValidationUtils.validateInput("\\d{9}|\\d{12}", "Id card must be 9 or 12 digits!");

        System.out.print("Enter phone number (starts with 0, 10 digits): ");
        String phoneNumber = ValidationUtils.validateInput("0\\d{9}", "Phone number must start with 0 and have 10 digits!");

        System.out.print("Enter email: ");
        String email = ValidationUtils.validateInput("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", "Invalid email. Please try again!");

        String[] customerTypes = {"Diamond", "Platinum", "Gold", "Silver", "Member"};
        System.out.println("Select customer type:");
        for (int i = 0; i < customerTypes.length; i++) {
            System.out.println((i + 1) + ". " + customerTypes[i]);
        }
        System.out.print("Your choice: ");
        int customerTypeChoice = ValidationUtils.validateMenuChoice(scanner, customerTypes.length);
        String customerType = customerTypes[customerTypeChoice - 1];

        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer(id, name, dateOfBirth, gender, idCard, phoneNumber, email, customerType, address);
        customers.add(customer);

        System.out.println("Customer added successfully.");
    }

    @Override
    public void edit() {
        ArrayList<Customer> customers = customerRepository.findAll();

        Customer customerToEdit = null;

        while (customerToEdit == null) {
            System.out.print("Enter the ID of the customer to edit: ");
            String id = scanner.nextLine();

            for (int i = 0; i < customers.size(); i++) {
                Customer customer = customers.get(i);
                if (customer.getId().equals(id)) {
                    customerToEdit = customer;
                    break;
                }
            }
            if (customerToEdit == null) {
                System.out.println("Invalid ID. Please try again!");
            }
        }

        System.out.print("Enter new name (leave blank to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            customerToEdit.setName(name);
        }
        System.out.print("Enter new address (leave blank to keep current): ");
        String addressInput = scanner.nextLine();
        if (!addressInput.isEmpty()) {
            customerToEdit.setAddress(addressInput);
        }
        System.out.println("Customer updated successfully.");
    }
}
