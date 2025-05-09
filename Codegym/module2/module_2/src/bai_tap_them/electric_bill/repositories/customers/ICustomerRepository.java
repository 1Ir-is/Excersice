package bai_tap_them.electric_bill.repositories.customers;

import bai_tap_them.electric_bill.models.Customer;

import java.util.List;

public interface ICustomerRepository {
    void add(Customer customer);
    List<Customer> findAll();
    Customer findById(String id);
}
