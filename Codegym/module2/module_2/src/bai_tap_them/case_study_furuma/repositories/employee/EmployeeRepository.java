package bai_tap_them.case_study_furuma.repositories.employee;

import bai_tap_them.case_study_furuma.models.Employee;

import java.util.ArrayList;

public class EmployeeRepository implements IEmployeeRepository {
    private static final ArrayList<Employee> employees = new ArrayList<>();

    static {
        employees.add(new Employee("NV-0001", "Nguyen Duc Vinh", "01/01/2003", "Male", "123456789", "0123456789", "vinh@example.com", "Manager", "Bachelor", 15000000));
        employees.add(new Employee("NV-0002", "Tran Chung Chien", "01/01/2002", "Male", "987654321", "0987654321", "chien@example.com", "Staff", "Master", 12000000));
        employees.add(new Employee("NV-0003", "Phan Ta Anh Vuong", "01/01/2004", "Male", "456789123", "0912345678", "vuong@example.com", "Technician", "Diploma", 10000000));
        employees.add(new Employee("NV-0004", "Le Thu Thao", "01/01/2001", "Female", "8912312345", "091212334234", "vuong@example.com", "Staff", "Diploma", 10000000));
    }

    @Override
    public ArrayList<Employee> findAll() {
        return employees;
    }
}