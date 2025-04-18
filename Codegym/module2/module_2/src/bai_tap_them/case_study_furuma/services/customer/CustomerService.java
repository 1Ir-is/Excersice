package bai_tap_them.case_study_furuma.services.customer;


import bai_tap_them.case_study_furuma.models.Customer;
import bai_tap_them.case_study_furuma.repositories.customer.ICustomerRepository;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

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
            id = validateInput("KH-\\d{4}", "Invalid ID format. Please use [KH-YYYY] format!");
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
        String name = validateInput("[A-Z][a-z]*(\\s[A-Z][a-z]*)*", "Name must capitalize the first letter of each word.");

        System.out.print("Enter customer date of birth: ");
        String dateOfBirth = validateDateOfBirth();

        System.out.print("Enter gender: ");
        String gender = validateGender();

        System.out.print("Enter ID Card number (9 or 12 digits): ");
        String idCard = validateInput("\\d{9}|\\d{12}", "Id card must be 9 or 12 digits!");

        System.out.print("Enter phone number (starts with 0, 10 digits): ");
        String phoneNumber = validateInput("0\\d{9}", "Phone number must start with 0 and have 10 digits!");

        System.out.print("Enter email: ");
        String email = validateInput("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", "Invalid email. Please try again!");

        System.out.print("Enter customer type: ");
        String customerType = validateNonNumericInput("Customer Type cannot contain number. Please try again!");

        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();

        Customer customer = new Customer(id, name, dateOfBirth, gender, idCard, phoneNumber, email, customerType, address);
        customers.add(customer);

        System.out.println("Customer added successfully.");
    }

    @Override
    public void edit() {

    }

    private String validateDateOfBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            try {
                String input = scanner.nextLine().trim();
                String[] parts = input.split("/");
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);

                if (month == 2) {
                    boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                    int maxDays = isLeapYear ? 29 : 28;
                    if (day > maxDays) {
                        System.out.print("Invalid date for February. Please try again!");
                        continue;
                    }
                }

                LocalDate date = LocalDate.parse(input, formatter);

                if (Period.between(date, LocalDate.now()).getYears() >= 18) {
                    return input;
                } else {
                    System.out.print("Customer must be at least 18 years old!");
                }

            } catch (Exception e) {
                System.out.print("Invalid date format. Please use [dd/MM/yyyy] format!");
            }
        }
    }

    private String validateNonNumericInput(String errorMessage) {
        while (true) {
            String input = scanner.nextLine().trim();
            boolean hasDigit = false;

            for (int i = 0; i < input.length(); i++) {
                char character = input.charAt(i);
                if (Character.isDigit(character)) {
                    hasDigit = true;
                    break;
                }
            }

            if (!hasDigit) {
                return input;
            }
            System.out.print(errorMessage);
        }
    }

    private String validateInput(String regex, String errorMessage) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.print("Input cannot be empty. Please enter again: ");
                continue;
            }
            if (Pattern.matches(regex, input)) {
                return input;
            }

            System.out.println(errorMessage);
        }
    }

    private String validateGender() {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Male") || input.equalsIgnoreCase("Female")) {
                return input;
            }
            System.out.print("Invalid gender. Please enter [Male] or [Female]!");
        }
    }
}
