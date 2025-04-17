package bai_tap_them.case_study_furuma.services.employee;

import bai_tap_them.case_study_furuma.models.Employee;
import bai_tap_them.case_study_furuma.repositories.employee.IEmployeeRepository;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EmployeeService implements IEmployeeService {
    private final IEmployeeRepository employeeRepository;
    private final Scanner scanner = new Scanner(System.in);

    public EmployeeService(IEmployeeRepository repository) {
        this.employeeRepository = repository;
    }

    @Override
    public void display() {
        ArrayList<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            System.out.println("No employee found!");
            return;
        }

        String line = "+--------+----------------------+------------+--------+--------------+--------------+----------------------+------------------+------------------+---------------+";
        String headerFormat = "| %-6s | %-20s | %-10s | %-6s | %-12s | %-12s | %-20s | %-16s | %-16s | %-13s |%n";

        System.out.println(line);
        System.out.printf(headerFormat, "ID", "Name", "DOB", "Gender", "ID Card", "Phone", "Email", "Qualification", "Position", "Salary (VND)");
        System.out.println(line);

        for (int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i).getDetails());
        }
        System.out.println(line);
        System.out.println();
    }


    @Override
    public void add() {
        System.out.print("Enter employee ID (format: NV-YYYY): ");
        String id = validateInput("NV-\\d{4}", "Invalid ID format. Please use NV-YYYY.");

        System.out.print("Enter employee name: ");
        String name = validateInput("[A-Z][a-z]*(\\s[A-Z][a-z]*)*", "Name must capitalize the first letter of each word.");

        System.out.print("Enter date of birth (dd/MM/yyyy): ");
        String dateOfBirth = validateDateOfBirth();

        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter ID card number (9 or 12 digits): ");
        String idCard = validateInput("\\d{9}|\\d{12}", "ID card must be 9 or 12 digits.");

        System.out.print("Enter phone number (starts with 0, 10 digits): ");
        String phoneNumber = validateInput("0\\d{9}", "Phone number must start with 0 and have 10 digits.");

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter position: ");
        String position = scanner.nextLine();

        System.out.print("Enter qualification: ");
        String qualification = scanner.nextLine();

        System.out.print("Enter salary: ");
        double salary = validateSalary();

        Employee employee = new Employee(id, name, dateOfBirth, gender, idCard, phoneNumber, email, position, qualification, salary);
        ArrayList<Employee> employees = employeeRepository.findAll();
        employees.add(employee);

        System.out.println("Employee added successfully.");
    }


    @Override
    public void edit() {
        ArrayList<Employee> employees = employeeRepository.findAll();

        System.out.print("Enter the ID of the employee to edit: ");
        String id = scanner.nextLine();

        Employee employeeToEdit = null;
        for (Employee emp : employees) {
            if (emp.getId().equals(id)) {
                employeeToEdit = emp;
                break;
            }
        }

        if (employeeToEdit == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("Enter new name (leave blank to keep current): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            employeeToEdit.setName(name);
        }

        System.out.print("Enter new salary (leave blank to keep current): ");
        String salaryInput = scanner.nextLine();
        if (!salaryInput.isEmpty()) {
            try {
                double newSalary = Double.parseDouble(salaryInput);
                if (newSalary > 0) {
                    employeeToEdit.setSalary(newSalary);
                } else {
                    System.out.println("Invalid salary, keeping current value.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, salary unchanged.");
            }
        }
        System.out.println("Employee updated successfully.");
    }

    private String validateInput(String regex, String errorMessage) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter again:");
                continue;
            }
            if (Pattern.matches(regex, input)) {
                return input;
            }
            System.out.println(errorMessage);
        }
    }

    private String validateDateOfBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                String input = scanner.nextLine();
                LocalDate date = LocalDate.parse(input, formatter);
                if (Period.between(date, LocalDate.now()).getYears() >= 18) {
                    return input;
                } else {
                    System.out.println("Employee must be at least 18 years old!");
                }
            } catch (Exception e) {
                System.out.println("Invalid date format, please use [dd/MM/yyyy]!");
            }
        }
    }

    private double validateSalary() {
        while (true) {
            try {
                double salary = scanner.nextDouble();
                scanner.nextLine(); // clear buffer
                if (salary > 0) {
                    return salary;
                } else {
                    System.out.println("Salary must be greater than 0!");
                }
            } catch (Exception e) {
                System.out.println("Invalid salary, please enter a valid number!");
                scanner.nextLine(); // clear buffer again
            }
        }
    }
}
