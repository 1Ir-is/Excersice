package bai_tap_them.case_study_furuma.services.employee;

import bai_tap_them.case_study_furuma.models.Employee;
import bai_tap_them.case_study_furuma.repositories.employee.EmployeeRepository;
import bai_tap_them.case_study_furuma.repositories.employee.IEmployeeRepository;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EmployeeService implements IEmployeeService {
    private final IEmployeeRepository employeeRepository = new EmployeeRepository();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display() {
        ArrayList<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            System.out.println("No employee found!");
        } else {
            for (int i = 0; i < employees.size(); i++) {
                System.out.println(employees.get(i).getDetails());
            }
        }
    }

    @Override
    public void add() {

    }

    @Override
    public void edit() {

    }

    private String validateInput(String regex, String errorMessage) {
        while (true) {
            String input = scanner.nextLine();
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
                if (salary > 0) {
                    return salary;
                } else {
                    System.out.println("Salary must be greater than 0!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary, please enter a valid salary!");
            }
        }
    }
}
