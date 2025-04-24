package bai_tap_them.case_study_furuma.services.customer;

import bai_tap_them.case_study_furuma.models.Customer;
import bai_tap_them.case_study_furuma.repositories.customer.ICustomerRepository;
import bai_tap_them.case_study_furuma.utils.ValidationUtils;
import bai_tap_them.case_study_furuma.view.CommonView;

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

        System.out.println("Customer List:");
        for (Customer customer : customers) {
            System.out.println("--------------------------------------------------");
            System.out.println("ID: " + customer.getId());
            System.out.println("Name: " + customer.getName());
            System.out.println("Date of Birth: " + customer.getDateOfBirth());
            System.out.println("Gender: " + customer.getGender());
            System.out.println("ID Card: " + customer.getIdCard());
            System.out.println("Phone: " + customer.getPhoneNumber());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("Customer Type: " + customer.getCustomerType());
            System.out.println("Address: " + customer.getAddress());
        }
        System.out.println("--------------------------------------------------");
    }

    @Override
    public void add() {
        System.out.print("Enter customer ID (format: KH-YYYY): ");
        String id;
        while (true) {
            id = ValidationUtils.validateInput("KH-\\d{4}", "Invalid ID format. Please use [KH-YYYY] format!");
            if (customerRepository.findById(id) != null) {
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

        String customerType = selectCustomerType();

        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer(id, name, dateOfBirth, gender, idCard, phoneNumber, email, customerType, address);
        customerRepository.add(customer);

        System.out.println("Customer added successfully.");
    }

    @Override
    public void edit() {
        Customer customerToEdit = null;

        while (customerToEdit == null) {
            System.out.print("Enter the ID of the customer to edit: ");
            String id = scanner.nextLine();
            customerToEdit = customerRepository.findById(id);

            if (customerToEdit == null) {
                System.out.println("Invalid ID. Please try again!");
            }
        }

        System.out.print("Enter new phone number (leave blank to keep current): ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isEmpty()) {
            if (phoneNumber.matches("0\\d{9}")) {
                customerToEdit.setPhoneNumber(phoneNumber);
            } else {
                System.out.println("Invalid phone number format. Keeping current value.");
            }
        }

        System.out.print("Enter new email (leave blank to keep current): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                customerToEdit.setEmail(email);
            } else {
                System.out.println("Invalid email format. Keeping current value.");
            }
        }

        String customerType = selectCustomerType();
        if (!customerType.isEmpty()) {
            customerToEdit.setCustomerType(customerType);
        }

        System.out.print("Enter new address (leave blank to keep current): ");
        String addressInput = scanner.nextLine();
        if (!addressInput.isEmpty()) {
            customerToEdit.setAddress(addressInput);
        }

        System.out.println("Customer updated successfully.");
    }

    private String selectCustomerType() {
        String[] customerTypes = {"Diamond", "Platinum", "Gold", "Silver", "Member"};
        System.out.println("Select customer type:");
        for (int i = 0; i < customerTypes.length; i++) {
            System.out.println((i + 1) + ". " + customerTypes[i]);
        }
        int choice = CommonView.getChoice(customerTypes.length);
        return customerTypes[choice - 1];
    }
}