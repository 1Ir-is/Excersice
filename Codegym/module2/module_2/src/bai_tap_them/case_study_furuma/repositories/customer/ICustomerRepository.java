package bai_tap_them.case_study_furuma.repositories.customer;

import bai_tap_them.case_study_furuma.models.Customer;
import bai_tap_them.case_study_furuma.models.Employee;
import bai_tap_them.case_study_furuma.repositories.Repository;

import java.util.List;

public interface ICustomerRepository extends Repository<Customer> {
    Customer findById(String id);
    void saveAll(List<Customer> customers);
    void add(Customer customer);
}