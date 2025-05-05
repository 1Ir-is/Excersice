package bai_tap_them.case_study_furuma.repositories.employee;

import bai_tap_them.case_study_furuma.models.Employee;
import bai_tap_them.case_study_furuma.repositories.Repository;

import java.util.List;

public interface IEmployeeRepository extends Repository<Employee> {
    void add(Employee employee);
    void saveAll(List<Employee> employees);
}
