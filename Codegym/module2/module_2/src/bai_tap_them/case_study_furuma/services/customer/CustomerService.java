package bai_tap_them.case_study_furuma.services.customer;

import bai_tap_them.case_study_furuma.models.Customer;
import bai_tap_them.case_study_furuma.repositories.customer.ICustomerRepository;
import bai_tap_them.case_study_furuma.views.CommonView;
import bai_tap_them.case_study_furuma.views.CustomerView;

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
        String id;
        while (true) {
            id = CustomerView.inputCustomerId();
            if (customerRepository.findById(id) != null) {
                System.out.print("ID already exists. Please enter a different ID: ");
            } else {
                break;
            }
        }

        String name = CustomerView.inputCustomerName();
        String dateOfBirth = CustomerView.inputCustomerDOB();
        String gender = CustomerView.inputCustomerGender();
        String idCard = CustomerView.inputCustomerIdCard();
        String phoneNumber = CustomerView.inputCustomerPhoneNumber();
        String email = CustomerView.inputCustomerEmail();
        String customerType = CustomerView.selectCustomerType();
        String address = CustomerView.inputAddress();

        Customer customer = new Customer(id, name, dateOfBirth, gender, idCard, phoneNumber, email, customerType, address);
        customerRepository.add(customer);

        System.out.println("Customer added successfully.");
    }

    @Override
    public void edit() {
        ArrayList<Customer> customers = customerRepository.findAll();
        Customer customerToEdit = null;

        while (customerToEdit == null) {
            System.out.print("Enter the ID of the customer to edit: ");
            String id = scanner.nextLine();

            for (Customer customer : customers) {
                if (customer.getId().equals(id)) {
                    customerToEdit = customer;
                    break;
                }
            }

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

        String[] customerTypes = {"Diamond", "Platinum", "Gold", "Silver", "Member"};
        System.out.println("Select customer type (leave blank to keep current): ");
        for (int i = 0; i < customerTypes.length; i++) {
            System.out.println((i + 1) + ". " + customerTypes[i]);
        }

        System.out.print("Your choice: ");
        String customerTypeInput = scanner.nextLine();
        if (!customerTypeInput.isEmpty()) {
            try {
                int customerTypeChoice = Integer.parseInt(customerTypeInput);
                if (customerTypeChoice >= 1 && customerTypeChoice <= customerTypes.length) {
                    customerToEdit.setCustomerType(customerTypes[customerTypeChoice - 1]);
                } else {
                    System.out.println("Invalid choice. Keeping current customer type.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Keeping current customer type.");
            }
        }

        System.out.print("Enter new address (leave blank to keep current): ");
        String addressInput = scanner.nextLine();
        if (!addressInput.isEmpty()) {
            customerToEdit.setAddress(addressInput);
        }
        customerRepository.saveAll(customers);
        System.out.println("Customer updated successfully.");
    }
}