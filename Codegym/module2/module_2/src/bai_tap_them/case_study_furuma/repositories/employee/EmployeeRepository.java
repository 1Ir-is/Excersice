package bai_tap_them.case_study_furuma.repositories.employee;

import bai_tap_them.case_study_furuma.models.Employee;

import java.util.ArrayList;

public class EmployeeRepository implements IEmployeeRepository {
    private static final ArrayList<Employee> employees = new ArrayList<>();

    @Override
    public ArrayList<Employee> findAll() {
        return employees;
    }
}