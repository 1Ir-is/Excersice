package bai_tap_them.case_study_furuma.repositories.customer;

import bai_tap_them.case_study_furuma.models.Customer;
import bai_tap_them.case_study_furuma.repositories.Repository;

public interface ICustomerRepository extends Repository<Customer> {
    Customer findById(String id);

    void add(Customer customer);
}