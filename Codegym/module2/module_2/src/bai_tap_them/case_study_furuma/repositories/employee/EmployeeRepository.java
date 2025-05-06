package bai_tap_them.case_study_furuma.repositories.employee;

import bai_tap_them.case_study_furuma.models.Employee;
import bai_tap_them.case_study_furuma.utils.SaveFileUtils;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements IEmployeeRepository {
    private static final String EMPLOYEE_FILE = "bai_tap_them/case_study_furuma/datas/employees.csv";

    @Override
    public void add(Employee employee) {
        List<String> dataLines = new ArrayList<>();
        dataLines.add(employee.toCSV());
        SaveFileUtils.writeToFile(EMPLOYEE_FILE, dataLines, true);
    }

    @Override
    public ArrayList<Employee> findAll() {
        List<String> lines = SaveFileUtils.readFromFile(EMPLOYEE_FILE);
        ArrayList<Employee> employees = new ArrayList<>();
        for (String line : lines) {
            employees.add(Employee.fromCSV(line));
        }
        return employees;
    }

    @Override
    public void saveAll(List<Employee> employees) {
        List<String> dataLines = new ArrayList<>();
        for (Employee employee : employees) {
            dataLines.add(employee.toCSV());
        }
        SaveFileUtils.writeToFile(EMPLOYEE_FILE, dataLines, false);
    }

}