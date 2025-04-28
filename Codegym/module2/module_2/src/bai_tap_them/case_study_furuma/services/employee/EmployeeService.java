package bai_tap_them.case_study_furuma.services.employee;

import bai_tap_them.case_study_furuma.models.Customer;
import bai_tap_them.case_study_furuma.models.Employee;
import bai_tap_them.case_study_furuma.repositories.employee.IEmployeeRepository;
import bai_tap_them.case_study_furuma.utils.ValidationUtils;
import bai_tap_them.case_study_furuma.view.CommonView;
import bai_tap_them.case_study_furuma.view.EmployeeView;

import java.text.DecimalFormat;
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

        DecimalFormat df = new DecimalFormat("#,###.##");
        System.out.println("Customer List:");
        for (Employee employee : employees) {
            System.out.println("--------------------------------------------------");
            System.out.println("ID: " + employee.getId());
            System.out.println("Name: " + employee.getName());
            System.out.println("Date of Birth: " + employee.getDateOfBirth());
            System.out.println("Gender: " + employee.getGender());
            System.out.println("ID Card: " + employee.getIdCard());
            System.out.println("Phone: " + employee.getPhoneNumber());
            System.out.println("Email: " + employee.getEmail());
            System.out.println("Position: " + employee.getPosition());
            System.out.println("Qualification: " + employee.getQualification());
            System.out.println("Salary: " + df.format(employee.getSalary()));
        }
        System.out.println("--------------------------------------------------");
    }


    @Override
    public void add() {
        ArrayList<Employee> employees = employeeRepository.findAll();
        String id;
        while (true) {
            id = EmployeeView.inputEmployeeId();
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

        String name = EmployeeView.inputEmployeeName();
        String dateOfBirth = EmployeeView.inputEmployeeDOB();
        String gender = EmployeeView.inputEmployeeGender();
        String idCard = EmployeeView.inputEmployeeIdCard();
        String phoneNumber = EmployeeView.inputEmployeePhoneNumber();
        String email = EmployeeView.inputEmployeeEmail();
        String qualification = EmployeeView.inputEmployeeQualification();
        String position = EmployeeView.inputEmployeePosition();
        double salary = EmployeeView.inputEmployeeSalary();

        Employee employee = new Employee(id, name, dateOfBirth, gender, idCard, phoneNumber, email, qualification, position, salary);
        employeeRepository.add(employee);
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

        System.out.print("Enter new phone number (leave blank to keep current): ");
        String phoneNumber = scanner.nextLine();
        if (!phoneNumber.isEmpty()) {
            if (phoneNumber.matches("0\\d{9}")) {
                employeeToEdit.setPhoneNumber(phoneNumber);
            } else {
                System.out.println("Invalid phone number format. Keeping current value.");
            }

        }

        System.out.print("Enter new email (leave blank to keep current): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                employeeToEdit.setEmail(email);
            } else {
                System.out.println("Invalid email format. Keeping current value. ");
            }
        }

        String[] qualifications = {"Intermediate", "College", "University", "Postgraduate"};
        System.out.println("Select new qualification (leave blank to keep current): ");
        for (int i = 0; i < qualifications.length; i++) {
            System.out.println((i + 1) + ". " + qualifications[i]);
        }

        System.out.print("Your choice: ");
        String qualificationInput = scanner.nextLine();
        if (!qualificationInput.isEmpty()) {
            try {
                int qualificationChoice = Integer.parseInt(qualificationInput);
                if (qualificationChoice >= 1 && qualificationChoice <= qualifications.length) {
                    employeeToEdit.setQualification(qualifications[qualificationChoice - 1]);
                } else {
                    System.out.println("Invalid choice. Keeping current qualification. ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Keeping current qualification. ");
            }
        }

        String[] positions = {"Receptionist", "Waiter", "Specialist", "Supervisor", "Manager", "Director"};
        System.out.println("Select new position (leave blank to keep current): ");
        for (int i = 0; i < positions.length; i++) {
            System.out.println((i + 1) + ". " + positions[i]);
        }

        System.out.print("Your choice: ");
        String positionInput = scanner.nextLine();
        if (!positionInput.isEmpty()) {
            try {
                int positionChoice = Integer.parseInt(positionInput);
                if (positionChoice >= 1 && positionChoice <= positions.length) {
                    employeeToEdit.setPosition(positions[positionChoice - 1]);
                } else {
                    System.out.println("Invalid choice. Keeping current position.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Keeping current position.");
            }
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