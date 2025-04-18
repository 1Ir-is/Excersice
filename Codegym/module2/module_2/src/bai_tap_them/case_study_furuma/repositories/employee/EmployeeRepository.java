package bai_tap_them.case_study_furuma.repositories.employee;

import bai_tap_them.case_study_furuma.models.Employee;

import java.util.ArrayList;

public class EmployeeRepository implements IEmployeeRepository {
    private static final ArrayList<Employee> employees = new ArrayList<>();

    static {
        employees.add(new Employee("NV-0001", "Nguyen Van A", "15/05/1990", "Nam", "123456789", "0123456789", "a.nguyen@example.com", "Manager", "Bachelor", 15000000));
        employees.add(new Employee("NV-0002", "Tran Thi B", "20/08/1992", "Nu", "987654321", "0987654321", "b.tran@example.com", "Staff", "Master", 12000000));
        employees.add(new Employee("NV-0003", "Le Van C", "10/12/1985", "Nam", "456789123", "0912345678", "c.le@example.com", "Technician", "Diploma", 10000000));
    }

    @Override
    public ArrayList<Employee> findAll() {
        return employees;
    }
}