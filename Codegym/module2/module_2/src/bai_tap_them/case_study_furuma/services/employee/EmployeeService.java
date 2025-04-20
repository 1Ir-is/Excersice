package bai_tap_them.case_study_furuma.services.employee;

import bai_tap_them.case_study_furuma.models.Employee;
import bai_tap_them.case_study_furuma.repositories.employee.IEmployeeRepository;
import bai_tap_them.case_study_furuma.utils.ValidationUtils;

import java.util.ArrayList;
import java.util.Scanner;

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
        ArrayList<Employee> employees = employeeRepository.findAll();

        System.out.print("Enter employee ID (format: NV-YYYY): ");
        String id;
        while (true) {
            id = ValidationUtils.validateInput("NV-\\d{4}", "Invalid ID format. Please use NV-YYYY.");
            boolean isDuplicate = false;
            for (Employee employee : employees) {
                if (employee.getId().equals(id)) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                System.out.println("ID already exists. Please enter a different ID.");
            } else {
                break;
            }
        }

        System.out.print("Enter employee name: ");
        String name = ValidationUtils.validateInput("[A-Z][a-z]*(\\s[A-Z][a-z]*)*", "Name must capitalize the first letter of each word.");

        System.out.print("Enter date of birth (dd/MM/yyyy): ");
        String dateOfBirth = ValidationUtils.validateDateOfBirth();

        System.out.print("Enter gender: ");
        String gender = ValidationUtils.validateGender();

        System.out.print("Enter ID card number (9 or 12 digits): ");
        String idCard = ValidationUtils.validateInput("\\d{9}|\\d{12}", "ID card must be 9 or 12 digits.");

        System.out.print("Enter phone number (starts with 0, 10 digits): ");
        String phoneNumber = ValidationUtils.validateInput("0\\d{9}", "Phone number must start with 0 and have 10 digits.");

        System.out.print("Enter email: ");
        String email = ValidationUtils.validateInput("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", "Invalid email. Please try again!");

        String[] qualifications = {"Intermediate", "College", "University", "Postgraduate"};
        System.out.println("Select qualification: ");
        for (int i = 0; i < qualifications.length; i++) {
            System.out.println((i + 1) + ". " + qualifications[i]);
        }
        System.out.print("Your choice: ");

        int qualificationChoice = ValidationUtils.validateMenuChoice(scanner, qualifications.length);
        String qualification = qualifications[qualificationChoice - 1];

        String[] positions = {"Receptionist", "Waiter", "Specialist", "Supervisor", "Manager", "Director"};
        System.out.println("Select position: ");
        for (int i = 0; i < positions.length; i++) {
            System.out.println((i + 1) + ". " + positions[i]);
        }
        System.out.print("Your choice: ");

        int positionChoice = ValidationUtils.validateMenuChoice(scanner, positions.length);
        String position = positions[positionChoice - 1];

        System.out.print("Enter salary: ");
        double salary = ValidationUtils.validateSalary();

        Employee employee = new Employee(id, name, dateOfBirth, gender, idCard, phoneNumber, email, position, qualification, salary);
        employees.add(employee);

        System.out.println("Employee added successfully.");
    }


    @Override
    public void edit() {
        ArrayList<Employee> employees = employeeRepository.findAll();

        Employee employeeToEdit = null;

        while (employeeToEdit == null) {
            System.out.print("Enter the ID of the employee to edit: ");
            String id = scanner.nextLine();

            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
                if (employee.getId().equals(id)) {
                    employeeToEdit = employee;
                    break;
                }
            }
            if (employeeToEdit == null) {
                System.out.println("Invalid ID. Please try again.");
            }
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
}
